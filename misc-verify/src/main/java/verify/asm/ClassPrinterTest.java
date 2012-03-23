package verify.asm;

import java.io.IOException;

import org.objectweb.asm.ClassReader;

public class ClassPrinterTest {
	public static void main(String[] args) throws IOException {
		ClassReader cr = new ClassReader(Object.class.getCanonicalName());
		cr.accept(new ClassPrinter(), 0);
	}
}
