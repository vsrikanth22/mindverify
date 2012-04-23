package verify.network.nio;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class TimeServer {

	private Charset charset = Charset.forName("ISO-8859-1");
	private CharsetDecoder decoder = charset.newDecoder();
	private CharsetEncoder encoder = charset.newEncoder();

	private ByteBuffer buffer = ByteBuffer.allocate(1024);
	private ServerSocketChannel ssc = null;
	private InetSocketAddress address = null;

	private Selector selector = null;

	private SelectionKey acceptKey = null;

	public void setUp() throws Exception {
		selector = SelectorProvider.provider().openSelector();
		this.ssc = ServerSocketChannel.open();
		this.ssc.configureBlocking(false);
		address = new InetSocketAddress(InetAddress.getLocalHost(), 8013);
		this.ssc.bind(address);
		this.acceptKey = ssc.register(selector, SelectionKey.OP_ACCEPT);
	}

	public void teatDown() throws Exception {

	}

	public static void main(String[] args) throws Exception {
		TimeServer server = new TimeServer();
		server.setUp();
		server.accept();
	}

	public void accept() throws Exception {

		for (;;) {
			// block current thread until at least one of the registered events
			// occurs.
			if (selector.select(100) > 0) {
				Set<SelectionKey> keys = selector.selectedKeys();
				for (Iterator<SelectionKey> iterator = keys.iterator(); iterator.hasNext();) {
					SelectionKey key = iterator.next();
					iterator.remove();

					if (key.isConnectable()) {
						continue;
					}

					if (key == acceptKey) {
						if (key.isAcceptable()) {
							ServerSocketChannel _ssc = (ServerSocketChannel) key.channel();
							SocketChannel _sc = _ssc.accept();
							_sc.configureBlocking(false);
							_sc.register(selector, SelectionKey.OP_READ);

							System.out.println(_sc);
							System.out.println("Accept the connection.");
						}
					} else if (key.isWritable()) {
						SocketChannel _sc = (SocketChannel) key.channel();
						_sc.write(encoder.encode(CharBuffer.wrap(new Date().toString())));
					} else if (key.isReadable() && key.isValid()) {
						try {
							SocketChannel _sc = (SocketChannel) key.channel();

							System.out.println(_sc);
							if (_sc.isOpen()) {
								int size = _sc.read(buffer);
								// System.out.println(size);

								if (size == -1) {
									key.cancel();
									_sc.close();
									break;
								}

								buffer.flip();
								String request = decoder.decode(buffer).toString();
								System.out.println(request);
								buffer.clear();
							}
						} catch (Exception e) {

							key.cancel();
							e.printStackTrace();
						} finally {

						}
					}
				}
			}
		}

	}

}
