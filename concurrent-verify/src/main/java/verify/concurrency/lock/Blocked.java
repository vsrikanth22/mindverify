package verify.concurrency.lock;

public class Blocked implements Runnable {

	LockInterruptibly interruptibly = new LockInterruptibly();

	@Override
	public void run() {
		System.out.println("Waiting for f() in LockInterruptibly");
		interruptibly.f();
		System.out.println("Broken out of blocked call");
	}
}
