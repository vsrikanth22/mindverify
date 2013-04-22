package verify.network.nio.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Handler implements Runnable {

	final SocketChannel socket;
	final SelectionKey sk;
	ByteBuffer input = ByteBuffer.allocate(1024);
	ByteBuffer output = ByteBuffer.allocate(1024);
	static final int READING = 0, SENDING = 1;
	int state = READING;

	String clientName = "";

	public Handler(Selector selector, SocketChannel c) throws IOException {
		socket = c;
		c.configureBlocking(false);
		sk = socket.register(selector, 0); // nothing interest.
		sk.attach(this);
		sk.interestOps(SelectionKey.OP_READ);
		selector.wakeup();
	}

	@Override
	public void run() {
		try {
			if (state == READING) {
				read();
			} else if (state == SENDING) {
				send();
			}
		} catch (IOException ex) {
			sk.cancel();
			ex.printStackTrace();
		}
	}

	void read() throws IOException {
		int count = 0;
		while ((count = socket.read(input)) > 0) {
			readProcess(count);
		}

		if (clientName.equals("Bye")) {
			sk.channel().close();
		} else {

			state = SENDING;
			// Normally also do first write now
			sk.interestOps(SelectionKey.OP_WRITE);
		}
	}

	void send() throws IOException {
		System.out.println("Saying hello to " + clientName);
		ByteBuffer output = ByteBuffer.wrap(("Hello " + clientName + "\n").getBytes());
		socket.write(output);
		sk.interestOps(SelectionKey.OP_READ);
		state = READING;
	}

	synchronized void readProcess(int readCount) {
		StringBuilder sb = new StringBuilder();
		input.flip();
		byte[] subStringBytes = new byte[readCount];
		byte[] array = input.array();
		System.arraycopy(array, 0, subStringBytes, 0, readCount);
		// Assuming ASCII (bad assumption but simplifies the example)
		sb.append(new String(subStringBytes));
		input.clear();
		clientName = sb.toString().trim();
		System.out.println("Receive the word: " + clientName);

	}

}
