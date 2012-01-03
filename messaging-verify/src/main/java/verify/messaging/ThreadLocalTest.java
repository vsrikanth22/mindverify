package verify.messaging;

import java.util.Formatter;

public class ThreadLocalTest {
	
	private static ThreadLocal<Formatter> formatter = new ThreadLocal<Formatter>() {
		@Override
		protected Formatter initialValue() {
			return new Formatter();
		}
	};
	
	public void testThreadLocal() {
		
	}

}
