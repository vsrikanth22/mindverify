package verify.network.nio;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class TimeClient {

	private Charset charset = Charset.defaultCharset();
	private CharsetEncoder encoder = charset.newEncoder();

	// private ByteBuffer buffer = ByteBuffer.allocate(1024);

	private SocketChannel sc = null;

	private Selector selector = null;

	private InetSocketAddress address = null;

	public void connect() throws Exception {
		this.sc = SocketChannel.open();
		//this.sc.configureBlocking(false);
		address = new InetSocketAddress(InetAddress.getLocalHost(), 12345);
		this.sc.connect(address);
		this.selector = Selector.open();
		// this.sc.register(selector, SelectionKey.OP_CONNECT);
		// if (selector.select() > 0) {
		// for (Iterator<SelectionKey> i = selector.selectedKeys().iterator(); i.hasNext();) {
		System.out.println("Sending");
		// Retrieve the next key and remove it from the set
		/*
		 * SelectionKey sk = (SelectionKey) i.next(); i.remove();
		 */

		if (sc.isConnectionPending()) {
			System.out.println("Test Connecting");
		}
		// Retrieve the target and the channel
		// SocketChannel sc = (SocketChannel) sk.channel();
		if (sc.isConnected()) {
			System.out.println("Test Connected");
			ByteBuffer buffer = encoder.encode(CharBuffer.wrap("Hello Server!"));
			buffer.flip();
			sc.write(buffer);
		}

		// }

	}

	public static void main(String[] args) throws Exception {
		TimeClient timeClient = new TimeClient();
		timeClient.connect();
	}

}
