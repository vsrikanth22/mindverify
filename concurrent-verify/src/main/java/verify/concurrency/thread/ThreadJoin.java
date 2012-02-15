package verify.concurrency.thread;

import java.util.concurrent.TimeUnit;

public class ThreadJoin {
	
	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread1.start();
		//waiting thread 1 terminated.
		thread1.join();
		
		while(thread1.getState() != Thread.State.TERMINATED) {
		}
		
		
	}

}
