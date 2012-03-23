package verify.asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ClassPrinter extends ClassVisitor {

	public ClassPrinter() {
		super(Opcodes.ASM4);
	}

	public ClassPrinter(int api) {
		super(api);
	}

	public void visit(int version, int access, String name,
			String signature, String superName, String[] interfaces) {

		StringBuilder sb = new StringBuilder();
		System.out.println(version);
		System.out.println(access);
		System.out.println(signature);
		if (name != null) {
			sb.append(name.replace("/", "."));
		}

		if (superName != null) {
			sb.append(" extends ").append(superName);
		}

		if (interfaces != null && interfaces.length > 0) {
			sb.append(" implements ");
			if (interfaces.length == 1) {
				sb.append(interfaces[0]);
			} else {
				for (int i = 0; i < interfaces.length - 1; i++) {
					sb.append(interfaces[i]).append(", ");
				}
				sb.append(interfaces[interfaces.length - 1]);
			}
		}

		System.out.println(sb.toString() + " {");
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
