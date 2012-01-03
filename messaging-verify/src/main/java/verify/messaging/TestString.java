package verify.messaging;

public class TestString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String test = "   777    ";
		test.trim();
		String value = test.intern();
		System.out.println(test == value);
		System.out.println(test);

	}

}
