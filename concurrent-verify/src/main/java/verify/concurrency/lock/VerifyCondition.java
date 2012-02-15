package verify.concurrency.lock;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VerifyCondition {

	final private Lock lock = new ReentrantLock();
	final private Condition ready = lock.newCondition();
	final private Condition notEmpty = lock.newCondition();

	private Queue<Object> objects = new ArrayDeque<Object>(200);

	public void put() throws InterruptedException {
		lock.lock();

		System.out.println("put get the lock");
		try {
			for (int i = 0; i < 200; i++) {
				objects.add(new Object());
				 Thread.sleep(10000);
			}

			ready.signal();
			System.out.println("send signal");
		} finally {
			lock.unlock();
			System.out.println("put release the lock");
		}
	}

	public void take() throws InterruptedException {
		lock.lock();
		System.out.println("take get the lock");
		try {
			while (objects.size() <= 100) {
				
				//ready.await();
				// Thread.sleep(5000);
			}

			System.out.println("rececive signal");

			for (Object object : objects) {
				System.out.println(object);
			}

		}

		finally {
			lock.unlock();
			System.out.println("take release the lock");
		}
	}

	public static void main(String[] args) throws InterruptedException {

		final VerifyCondition condition = new VerifyCondition();
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println(new Date());
					// condition.take();
					condition.put();

					System.out.println(new Date());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		thread1.setName("put");

		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					System.out.println(new Date());
					condition.take();

					System.out.println(new Date());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		thread2.setName("take");

	
		System.out.println(thread1.getName() + thread1.getState());
		System.out.println(thread2.getName() + thread2.getState());
		
		
		thread1.start();
		Thread.sleep(100);
		thread2.start();

		System.out.println(thread1.getName() + thread1.getState());
		System.out.println(thread2.getName() + thread2.getState());
		// Thread.currentThread().join();
	}

}
