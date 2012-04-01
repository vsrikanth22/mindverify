package verify.network.jgroups.lock;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import org.jgroups.JChannel;
import org.jgroups.blocks.locking.LockService;
import org.jgroups.util.Util;

public class LockDemo {

	private JChannel channel;

	private LockService lockService;

	public void start() throws Exception {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("locking.xml");
		channel = new JChannel(in);
		channel.connect("locking_service");
		lockService = new LockService(channel);
		in.close();
		try {
			loop();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Util.close(channel);
		}
	}

	public static void main(String[] args) throws Exception {
		LockDemo demo = new LockDemo();
		demo.start();
	}

	protected void loop() throws Exception {
		List<String> lock_names;
		while (channel.isConnected()) {
			String line = Util.readStringFromStdin(": ");
			if (line.startsWith("quit") || line.startsWith("exit"))
				break;

			if (line.startsWith("lock")) {
				lock_names = parseLockNames(line.substring("lock".length()).trim());
				for (String lock_name : lock_names) {
					Lock lock = lockService.getLock(lock_name);
					lock.lock();
				}
			}
			else if (line.startsWith("trylock")) {
				lock_names = parseLockNames(line.substring("trylock".length()).trim());

				String tmp = lock_names.get(lock_names.size() - 1);
				Long timeout = new Long(-1);
				try {
					timeout = Long.parseLong(tmp);
					lock_names.remove(lock_names.size() - 1);
				} catch (NumberFormatException e) {
				}

				for (String lock_name : lock_names) {
					Lock lock = lockService.getLock(lock_name);
					boolean rc;
					if (timeout.longValue() < 0)
						rc = lock.tryLock();
					else
						rc = lock.tryLock(timeout.longValue(), TimeUnit.MILLISECONDS);
					if (!rc)
						System.err.println("Failed locking \"" + lock_name + "\"");
				}
			}
			else if (line.startsWith("unlock")) {
				lock_names = parseLockNames(line.substring("unlock".length()).trim());
				for (String lock_name : lock_names) {
					if (lock_name.equalsIgnoreCase("all")) {
						lockService.unlockAll();
						break;
					}
					else {
						Lock lock = lockService.getLock(lock_name);
						if (lock != null)
							lock.unlock();
					}
				}
			}
			else if (line.startsWith("view"))
				System.out.println("View: " + channel.getView());

			printLocks();
		}
	}

	protected static List<String> parseLockNames(String line) {
		List<String> lock_names = new ArrayList<String>();
		if (line == null || line.length() == 0)
			return lock_names;
		StringTokenizer tokenizer = new StringTokenizer(line);
		while (tokenizer.hasMoreTokens())
			lock_names.add(tokenizer.nextToken());
		return lock_names;
	}

	protected void printLocks() {
		System.out.println("\n" + lockService.printLocks());
	}

}
