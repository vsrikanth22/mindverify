package verify.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TryResourceExample {

	public static void main(String[] args) throws FileNotFoundException,
			IOException {

		try (BufferedReader br = new BufferedReader(new FileReader(
				"data/test.txt"))) {
			System.out.println(br.readLine());
		}

	}

}
