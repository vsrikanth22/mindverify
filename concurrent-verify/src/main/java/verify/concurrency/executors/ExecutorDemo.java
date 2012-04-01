package verify.concurrency.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.submit(new Runnable() {

			@Override
			public void run() {
				Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
				System.out.println(Thread.currentThread().getName());
			}
		});

		for (int i = 0; i < 4; i++)
			executor.submit(new Runnable() {

				@Override
				public void run() {
					while (true) {
						Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
						System.out.println(Thread.currentThread().getName());
					}
				}
			});
	}
}
