package verify.network.nio.buffer;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class BufferTest {
	
	public static void main(String[] args) {
		
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		byteBuffer.putChar('H').putChar('e');
		byteBuffer.put((byte)1);
		System.out.println(byteBuffer.position());
		System.out.println(byteBuffer.limit());
		System.out.println(byteBuffer.capacity());
		System.out.println(byteBuffer.remaining());
		int count = byteBuffer.remaining();
		byte[] array = new byte[1024];
		for(int i = 0; i < byteBuffer.remaining();i++) {
			array[i] = byteBuffer.get();
		}
		System.out.println(array);
		
		
	}

}
