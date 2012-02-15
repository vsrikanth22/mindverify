package verify.concurrency.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ThreadDemo {

	final static Object[] LOCKS = new Object[] { new Object() };
	final static List<Object> objects = new ArrayList<Object>();

	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {

				synchronized (LOCKS[0]) {
					while (objects.size() < 50) {
						System.out.println("waiting");
						/*try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}*/
						try {
						
							LOCKS[0].wait(15000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					System.out.println("resume from notify");
					
					for (Object object : objects) {
						System.out.println(object);
					}

				}

			}
		});

		thread1.start();
		
		System.out.println(thread1.getState());
		TimeUnit.SECONDS.sleep(10);
		
		
		
		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {

				synchronized (LOCKS[0]) {
					
					for (int i = 0; i <= 50; i ++) {
						objects.add(new Object());
					}
					
					LOCKS[0].notify();
					System.out.println("notify");

				}

			}
		});
		
		 thread2.start();
		 System.out.println(thread2.getState());
		 System.out.println(thread1.getState());

	}
	
	

}
