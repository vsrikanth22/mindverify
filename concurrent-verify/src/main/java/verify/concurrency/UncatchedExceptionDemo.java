package verify.concurrency;

import java.lang.Thread.UncaughtExceptionHandler;

public class UncatchedExceptionDemo {

	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println(e.getMessage());
			}
		});

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					Thread.sleep(10000);
					System.out.println("running");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				throw new RuntimeException("I am a runtime exception");
			}
		});
		
		thread.start();

	}

}
