package verify.concurrency.lock.intrisic;

public class TestObject implements Runnable {
	
	public void test() throws InterruptedException {
		synchronized (new Object()) {
			System.out.println(Thread.currentThread().getName() + ":" + "Running");
			Thread.sleep(1000);
		}
	}

	@Override
	public void run() {
		try {
			test();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
