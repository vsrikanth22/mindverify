package verify.network.nio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class Reactor implements Runnable {

	final static private int DEFAULT_PORT = 9527;

	private Selector selector;
	private ServerSocketChannel serverSocket;

	public Reactor() {
		super();
		try {
			selector = Selector.open();
			serverSocket = ServerSocketChannel.open();
			serverSocket.socket().bind(new InetSocketAddress(DEFAULT_PORT));
			serverSocket.configureBlocking(false); // non-blocing mode.
			SelectionKey acceptKey = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
			// register the accept key to server socket channel.
			acceptKey.attach(new Acceptor());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				selector.select();
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				for (SelectionKey selectedKey : selectedKeys) {
					dispatch(selectedKey);
				}
				selectedKeys.clear();
			}
		} catch (IOException ex) { /* ... */
			ex.printStackTrace();
		}
	}

	private void dispatch(SelectionKey k) {
		Runnable r = (Runnable) (k.attachment());
		if (r != null)
			r.run();
	}

	class Acceptor implements Runnable {

		@Override
		public void run() {
			try {
				SocketChannel c = serverSocket.accept();
				if (c != null)
					new Handler(selector, c);
			} catch (IOException ex) { /* ... */
			}
		}

	}
	
	public static void main(String[] args) {
		Reactor reactor  = new Reactor();
	    new Thread(reactor).start();
	}

}
