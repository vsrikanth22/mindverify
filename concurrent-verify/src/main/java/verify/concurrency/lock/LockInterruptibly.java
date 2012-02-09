package verify.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptibly {

	Lock lock = new ReentrantLock();

	public LockInterruptibly() {
		lock.lock();
	}

	public void f() {
		
		/*if(lock.tryLock())
			System.out.println("acquired Interruptibly Lock");
		*/
		
		
		try {
			lock.lockInterruptibly();
			System.out.println("acquired Interruptibly Lock");
		} catch (InterruptedException e) {
			System.out.println("Interrupted from lock acquisition in f()");
		} 

	}



}
