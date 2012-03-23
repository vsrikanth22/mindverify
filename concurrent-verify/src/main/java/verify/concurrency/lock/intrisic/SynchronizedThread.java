package verify.concurrency.lock.intrisic;

public class SynchronizedThread {
	
	public static void main(String[] args) {
		TestObject testObject = new TestObject();
		Thread thread1 = new Thread(testObject);
		Thread thread2 = new Thread(testObject);
		thread1.start();
		thread2.start();
	}
	


}
