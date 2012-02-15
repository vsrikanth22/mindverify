package verify.concurrency.lock;

import java.util.concurrent.TimeUnit;


public class LockInterruptiblyDemo {
	
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new Blocked());

		t.start();
		System.out.println(t.getState());
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Issuing t.interrupt()");
		t.interrupt();
		System.out.println(t.getState());
	}

}
