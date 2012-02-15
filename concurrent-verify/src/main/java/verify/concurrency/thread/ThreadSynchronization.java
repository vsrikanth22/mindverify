package verify.concurrency.thread;

public class ThreadSynchronization {

	private volatile int i = 0;

	public synchronized int getI() {
		return i;
	}

	public synchronized void setI(int i) {
		this.i = i;
		System.out.println(Thread.currentThread().getName() + " internal" + i);
	}

	public static void main(String[] args) throws InterruptedException {

		final ThreadSynchronization t1 = new ThreadSynchronization();
		Thread thread1 = new Thread(new B(t1, 8));

		Thread thread2 = new Thread(new B(t1, 7));

		thread1.start();
		thread2.start();

		thread1.join();
		thread2.join();

		//System.out.println(t1.getI());
	}

	static class B implements Runnable {

		private ThreadSynchronization t1;
		private int i;

		public B(ThreadSynchronization t1, int i) {
			this.t1 = t1;
			this.i = i;
		}

		@Override
		public void run() {
			
			t1.setI(i);
			
		}

	}

}
