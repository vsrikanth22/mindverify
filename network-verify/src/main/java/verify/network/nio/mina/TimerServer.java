package verify.network.nio.mina;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.concurrent.atomic.AtomicInteger;

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
				IoBuffer ioBuffer = IoBuffer.allocate(1024);
				ioBuffer.putString("nice to meet u", Charset.defaultCharset().newEncoder());
				ioBuffer.flip();
				session.write(ioBuffer);
			
			}

		});
		acceptor.bind(new InetSocketAddress(InetAddress.getLocalHost(), 8013));

	}

}
