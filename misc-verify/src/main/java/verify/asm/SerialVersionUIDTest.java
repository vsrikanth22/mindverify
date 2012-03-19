package verify.asm;

import java.io.IOException;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.SerialVersionUIDAdder;

public class SerialVersionUIDTest {

	public static void main(String[] args) throws IOException {
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
		ClassVisitor sv = new SerialVersionUIDAdder(cw);
		ClassReader cr = new ClassReader(JavaClass.class.getCanonicalName());
		cr.accept(sv, 0);
		cr = new ClassReader(cw.toByteArray());
		cr.accept(new ClassPrinter(), 0);
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
