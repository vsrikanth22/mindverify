package verify.network.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class TimeQuery {

	// The standard daytime port
	private static int DAYTIME_PORT = 8013;

	// The port we'll actually use
	private static int port = DAYTIME_PORT;

	// Charset and decoder for US-ASCII
	private static Charset charset = Charset.forName("US-ASCII");
	private static CharsetDecoder decoder = charset.newDecoder();

	// Direct byte buffer for reading
	private static ByteBuffer dbuf = ByteBuffer.allocateDirect(1024);

	// Ask the given host what time it is
	//
	private static void query() throws IOException {
		InetSocketAddress isa = new InetSocketAddress(InetAddress.getLoopbackAddress(), port);
		SocketChannel sc = null;

		try {

			// Connect
			sc = SocketChannel.open();
			sc.connect(isa);

			// Read the time from the remote host. For simplicity we assume
			// that the time comes back to us in a single packet, so that we
			// only need to read once.
			dbuf.clear();
			sc.read(dbuf);

			// Print the remote address and the received time
			dbuf.flip();
			CharBuffer cb = decoder.decode(dbuf);
			System.out.print(isa + " : " + cb);

		} finally {
			// Make sure we close the channel (and hence the socket)
			if (sc != null)
				sc.close();
		}
	}

	public static void main(String[] args) throws IOException {
		query();
	}

}
