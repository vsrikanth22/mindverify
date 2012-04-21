package verify.network.nio;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetworkInterfaceTest {

	public static void main(String[] args) throws SocketException {
		Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		while (interfaces.hasMoreElements()) {
			NetworkInterface _interface = interfaces.nextElement();
			Enumeration<InetAddress> addresses = _interface.getInetAddresses();
			while (addresses.hasMoreElements()) {
				InetAddress address = addresses.nextElement();
				System.out.println(address.getHostAddress() + ":" + address.isLoopbackAddress() + ":"
						+ address.toString());
			}
		}
	}

}
