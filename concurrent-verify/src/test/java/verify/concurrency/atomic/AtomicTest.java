package verify.concurrency.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Assert;
import org.junit.Test;

public class AtomicTest {
	
	@Test
	public void testAtomicBoolean() throws Exception {
		
		AtomicBoolean boolean1 = new AtomicBoolean(false);
		boolean1.compareAndSet(false, true);
		Assert.assertTrue(boolean1.get());
		
	}

}
