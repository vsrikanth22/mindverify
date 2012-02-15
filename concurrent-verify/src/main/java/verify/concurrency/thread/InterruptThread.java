package verify.concurrency.thread;

public class InterruptThread {

	public static void main(String[] args) {

		Thread thread = Thread.currentThread();
		thread.interrupt();

		if (thread.isInterrupted()) {
			System.out.println("Interrupted1");
		}

		if (Thread.interrupted()) {
			System.out.println("Interrupted2");
		}
	}

}
