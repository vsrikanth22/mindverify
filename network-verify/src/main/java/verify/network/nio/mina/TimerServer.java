package verify.network.nio.mina;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class TimerServer {

	public static void main(String[] args) throws Exception {
		
		final CharsetDecoder decoder = Charset.defaultCharset().newDecoder();

		SocketAcceptor acceptor = new NioSocketAcceptor();
		acceptor.setReuseAddress(true);
		acceptor.setHandler(new IoHandlerAdapter() {
			
			

			@Override
			public void messageSent(IoSession session, Object message) throws Exception {
				super.messageSent(session, message);
			}

			@Override
			public void messageReceived(IoSession session, Object message) throws Exception {
				
				IoBuffer buffer = (IoBuffer)message;
				System.out.println(decoder.decode(buffer.buf()).toString());
				
			}

		});
		acceptor.bind(new InetSocketAddress(InetAddress.getLocalHost(), 8013));

	}

}
