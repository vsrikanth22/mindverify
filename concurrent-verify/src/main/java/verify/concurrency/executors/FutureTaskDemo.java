package verify.concurrency.executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTaskDemo {
	
	public static void main(String[] args) throws Exception {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<Long> future = executorService.submit(new Callable<Long>() {

			@Override
			public Long call() throws Exception {
				Thread.sleep(60000);
				return 1L;
			}
		});
		
		Long i = future.get();
		System.out.println(i);
	}

}
