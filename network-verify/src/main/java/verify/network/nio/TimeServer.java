package verify.network.nio;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;

public class TimeServer {

	private ServerSocketChannel ssc = null;
	private InetSocketAddress address = null;

	private Selector acceptSelector = null;

	public void setUp() throws Exception {
		acceptSelector = SelectorProvider.provider().openSelector();
		this.ssc = ServerSocketChannel.open();
		this.ssc.configureBlocking(false);
		address = new InetSocketAddress(InetAddress.getLocalHost(), 8999);
		this.ssc.bind(address);
		ssc.register(acceptSelector, SelectionKey.OP_ACCEPT);
	}

	public void teatDown() throws Exception {

	}

	public static void main(String[] args) throws Exception {
		TimeServer server = new TimeServer();
		server.setUp();
	}

}
