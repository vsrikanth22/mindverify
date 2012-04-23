package verify.network.nio;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class TimeClient {

	private Charset charset = Charset.defaultCharset();
	private CharsetEncoder encoder = charset.newEncoder();
	private CharsetDecoder decoder = charset.newDecoder();
	// private ByteBuffer buffer = ByteBuffer.allocate(1024);

	private SocketChannel sc = null;

	private Selector selector = null;

	private InetSocketAddress address = null;

	public void connect() throws Exception {
		this.sc = SocketChannel.open();
		this.sc.configureBlocking(false);
		address = new InetSocketAddress(InetAddress.getLocalHost(), 8013);
		this.sc.connect(address);
		// this.selector = Selector.open();
		// this.sc.register(selector, SelectionKey.OP_CONNECT);
		// if (selector.select() > 0) {
		// for (Iterator<SelectionKey> i = selector.selectedKeys().iterator();
		// i.hasNext();) {

		// Retrieve the next key and remove it from the set
		/*
		 * SelectionKey sk = (SelectionKey) i.next(); i.remove();
		 */
		while (!sc.finishConnect()) {
			System.out.println("Test Connecting");
		}

		// Retrieve the target and the channel
		// SocketChannel sc = (SocketChannel) sk.channel();
		if (sc.isConnected()) {
			sc.finishConnect();
			System.out.println("Test Connected");
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			// buffer.flip();

			buffer.put(encoder.encode(CharBuffer.wrap("Hello Server!")));
			buffer.flip();
			sc.write(buffer);
			
			/*//Thread.sleep(10000);
			buffer.reset();
			//buffer.rewind();
			buffer.put(encoder.encode(CharBuffer.wrap("Hello Server again!")));
			buffer.flip();
			sc.write(buffer);*/
			Thread.sleep(10000);
			buffer.clear();
			//ByteBuffer buffer1 = ByteBuffer.allocate(1024);
			buffer.put(encoder.encode(CharBuffer.wrap("Hello Server! Again")));
			buffer.flip();
			sc.write(buffer);
			buffer.clear();
			sc.read(buffer);
			
			System.out.print(decoder.decode(buffer).toString());
			
			System.out.print(buffer.remaining());
			//sc.close();
			/*while (true) {

				
			}*/
		}

		// }

	}

	public static void main(String[] args) throws Exception {
		TimeClient timeClient = new TimeClient();
		timeClient.connect();
	}

}
