package verify.jboss.drools.mvel;

import static org.junit.Assert.*;

import java.util.Collection;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.builder.impl.KnowledgeBuilderImpl;
import org.drools.compiler.PackageBuilderConfiguration;
import org.drools.definition.KnowledgePackage;
import org.drools.definition.type.FactType;
import org.drools.definitions.impl.KnowledgePackageImp;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.rule.DialectRuntimeData;
import org.drools.rule.JavaDialectRuntimeData;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.util.ClassLoaderUtil;
import org.drools.util.CompositeClassLoader;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mvel2.optimizers.OptimizerFactory;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class EvaluationTests {
	
	@BeforeClass
	public static void beforeClass() {

		//OptimizerFactory.setDefaultOptimizer("ASM");
		
	}
	// @Ignore
	@Test
	public void test() throws Exception {
		PackageBuilderConfiguration conf = new PackageBuilderConfiguration(Thread.currentThread().getContextClassLoader());

		Resource resource = ResourceFactory.newClassPathResource("rules/domain.drl");
		KnowledgeBuilder kbb = KnowledgeBuilderFactory.newKnowledgeBuilder(conf);
		kbb.add(resource, ResourceType.DRL);
		if (kbb.hasErrors()) {
			for (KnowledgeBuilderError error : kbb.getErrors()) {
				System.out.println(error.getMessage());
			}

			throw new RuntimeException();
		}
		KnowledgeBase kb = KnowledgeBaseFactory.newKnowledgeBase();
		kb.addKnowledgePackages(kbb.getKnowledgePackages());
		Collection<KnowledgePackage> pkgs = kb.getKnowledgePackages();
		
		ClassLoader classLoader2 = null;//= new CompositeClassLoader();
		
		for(KnowledgePackage pkg : pkgs) {
			KnowledgePackageImp imp = (KnowledgePackageImp)pkg;
			JavaDialectRuntimeData data = (JavaDialectRuntimeData)imp.pkg.getDialectRuntimeRegistry().getDialectData("java");
			System.out.println(data.getClassLoader().getParent().toString());
			classLoader2 = data.getClassLoader();
			//classLoader2.addClassLoader(data.getClassLoader());
			
		}

		FactType ft = kb.getFactType("error", "Domain");
		Object fbn = ft.newInstance();
		
		ClassLoader classLoader = fbn.getClass().getClassLoader();
		//System.out.println(fbn.getClass().getClassLoader().toString());
		ft.set(fbn, "id", 0L);
		
		System.out.println(classLoader.getParent().toString());
		System.out.println(classLoader2.getParent().toString());
		StatefulKnowledgeSession session = kb.newStatefulKnowledgeSession();
		session.insert(fbn);

		ClassLoader classLoader1 = ((KnowledgeBuilderImpl)kbb).getPackageBuilder().getRootClassLoader();
		Thread.currentThread().setContextClassLoader(classLoader2);

	/*	ClassReader cr = new ClassReader(fbn.getClass().getName());
		ClassPrinter classPrinter = new ClassPrinter();
		cr.accept(classPrinter, 0);*/
		OptimizerFactory.setDefaultOptimizer(OptimizerFactory.DYNAMIC);
		//OptimizerFactory.setDefaultOptimizer("ASM");
		//System.out.println(OptimizerFactory.getDefaultAccessorCompiler());
		//Thread.sleep(10000);
		//assertEquals(new Long(-1L), ft.get(fbn, "id"));
		try {
			
			session.fireAllRules();
			// assertEquals(new Long(12586269025L), ft.get(fbn, "value"));
		} catch (NoClassDefFoundError e) {
			e.printStackTrace();
			fail();
		} finally {
			session.dispose();
		}
	}
	
	public  static class ClassPrinter extends ClassVisitor {
		public ClassPrinter() {
			super(Opcodes.ASM4);
		}

		public void visit(int version, int access, String name,
				String signature, String superName, String[] interfaces) {
			System.out.println(name + " extends " + superName + " {");
		}

		public void visitSource(String source, String debug) {}

		public void visitOuterClass(String owner, String name, String desc) {}

		public AnnotationVisitor visitAnnotation(String desc,
				boolean visible) {
			return null;
		}

		public void visitAttribute(Attribute attr) {}

		public void visitInnerClass(String name, String outerName,
				String innerName, int access) {}

		public FieldVisitor visitField(int access, String name, String desc,
				String signature, Object value) {
			System.out.println(" " + desc + " " + name + " " + value);
			return null;
		}

		public MethodVisitor visitMethod(int access, String name,
				String desc, String signature, String[] exceptions) {
			System.out.println(" " + name + desc);
			return null;
		}

		public void visitEnd() {
			System.out.println("}");
		}
	}
}
