package verify.network.nio;

import java.io.IOException;
import java.net.StandardSocketOptions;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class SocketChannelApiTest {
	
	public static void main(String[] args) throws IOException {
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false);
		SocketChannel sc =  SocketChannel.open();
		sc.configureBlocking(false);
		boolean keepAlive =  sc.getOption(StandardSocketOptions.SO_KEEPALIVE);
		boolean tcpNoDelay =  sc.getOption(StandardSocketOptions.TCP_NODELAY);
		boolean loopback = ssc.getOption(StandardSocketOptions.IP_MULTICAST_LOOP);
		System.out.println(keepAlive);
		System.out.println(tcpNoDelay);
		System.out.println(loopback);
	}
	

}
