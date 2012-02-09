package verify.concurrency.lock;

import java.util.concurrent.locks.Lock;

public class ConditionTask implements Runnable {

	private Lock startUpLock = null;

	private ConditionTask(Lock startUpLock) {
		this.startUpLock = startUpLock;
	}

	@Override
	public void run() {

	}

}
