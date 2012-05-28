package verify.clazz;

public class ClazzTest {

	private static Object object = null;

	static {
		if (object == null) {
			throw new RuntimeException("initialize error");
		}
	}

	public static void main(String[] args) throws Throwable {
		Class.forName("verify.clazz.ClazzTest");
	}

}
