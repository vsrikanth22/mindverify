package verify.concurrency.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomicDemo {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newFixedThreadPool(5);

		AtomicHolder holder = new AtomicHolder();

		for (int i = 0; i < 5; i++) {
			executorService.submit(new AtomicTask(holder));
		}
		
		executorService.shutdown();

	}

	static class AtomicTask implements Runnable {

		private AtomicHolder holder;

		public AtomicTask(AtomicHolder holder) {
			this.holder = holder;
		}

		@Override
		public void run() {
			holder.setTrue();
			holder.lazySetFalse();
			
		}

	}

}
