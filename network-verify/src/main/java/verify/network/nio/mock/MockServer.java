package verify.network.nio.mock;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import com.cordinc.util.network.AbstractServer;

public class MockServer extends AbstractServer {
	

	private CharsetEncoder encoder = Charset.defaultCharset().newEncoder();
	private CharsetDecoder decoder = Charset.defaultCharset().newDecoder();

	protected MockServer(int port) {
		super(port);
	}

	@Override
	protected void messageReceived(ByteBuffer message, SelectionKey key) {
		try {
			System.out.println(decoder.decode(message).toString());
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	protected void connection(SelectionKey key) {

	}

	@Override
	protected void disconnected(SelectionKey key) {

	}

	@Override
	protected void started(boolean alreadyStarted) {

	}

	@Override
	protected void stopped() {

	}
	
	public static void main(String[] args) {
		MockServer mockServer = new MockServer(8013);
		new Thread(mockServer).start();	 
	}

}
