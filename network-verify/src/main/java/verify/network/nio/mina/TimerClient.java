package verify.network.nio.mina;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class TimerClient {
	
	public static void main(String[] args) throws Exception {
		
		final AtomicInteger i = new AtomicInteger(0);
		
		NioSocketConnector nioSocketConnector = new NioSocketConnector();
		nioSocketConnector.setHandler(new IoHandlerAdapter(){
			
			

			@Override
			public void sessionCreated(IoSession session) throws Exception {
				super.sessionCreated(session);
				
				i.set(0);
			}
			
			

			@Override
			public void sessionOpened(IoSession session) throws Exception {
				// TODO Auto-generated method stub
				super.sessionOpened(session);
				IoBuffer ioBuffer =  IoBuffer.allocate(1024);
				ioBuffer.putString("Hello World", Charset.defaultCharset().newEncoder());
				ioBuffer.flip();
				session.write(ioBuffer);
			}



			@Override
			public void messageReceived(IoSession session, Object message) throws Exception {
				super.messageReceived(session, message);
				IoBuffer buffer = (IoBuffer)message;
				System.out.println(Charset.defaultCharset().newDecoder().decode(buffer.buf()).toString());
				if(i.get() <  2) {
				IoBuffer ioBuffer = IoBuffer.allocate(1024);
				ioBuffer.putString("Hello World Again", Charset.defaultCharset().newEncoder());
				ioBuffer.flip();
				session.write(ioBuffer);
				i.getAndIncrement();
				} else {
					 session.close(true);
				}
				
			}

			@Override
			public void messageSent(IoSession session, Object message) throws Exception {
				super.messageSent(session, message);
				//System.out.println(message.toString());
			}
			
		});
		
		ConnectFuture future = nioSocketConnector.connect(new InetSocketAddress(InetAddress.getLocalHost(), 8013));
		future.awaitUninterruptibly();
		
		IoSession  session = future.getSession();
		
		CloseFuture closeFuture = session.getCloseFuture();
		closeFuture.awaitUninterruptibly();
		
		//session.getCloseFuture().awaitUninterruptibly();
		nioSocketConnector.dispose();
	}

}
