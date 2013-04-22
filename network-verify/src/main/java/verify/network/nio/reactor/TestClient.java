package verify.network.nio.reactor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TestClient {

	public static void main(String[] args) throws Exception {
		Socket clientSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		try {
			clientSocket = new Socket("127.0.0.1", 9527);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String userInput;

		System.out.println("Client connected to host.");
		System.out.println("Type (\"Bye\" to quit)");
		System.out.println("Tell what your name is to the Server.....");

		while ((userInput = stdIn.readLine()) != null) {

			out.println(userInput);

			// Break when client says Bye.
			if (userInput.equalsIgnoreCase("Bye"))
				break;

			System.out.println("Server says: " + in.readLine());
		}

		out.close();
		in.close();
		stdIn.close();
		clientSocket.close();
		System.out.println("Client shutdown");
	}

}
