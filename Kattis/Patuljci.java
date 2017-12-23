import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.PrintWriter;

class FastReader {
	static InputStreamReader stream = new InputStreamReader(System.in);
	static BufferedReader reader = new BufferedReader(stream);
	static StringTokenizer tokenizer = new StringTokenizer("");
	static PrintWriter out = new PrintWriter(System.out);
	static String token = null;
	static String line = "";

	public static String next() {
		/*
		 * while(! tokenizer.hasMoreTokens() || tokenizer == null) { tokenizer = new
		 * StringTokenizer(reader.readLine()); } return tokenizer.nextToken();
		 */
		String toPrint = nextToken();
		token = null;
		return toPrint;
	}

	public static boolean hasNext() {
		return nextToken() != null;
	}

	public static String nextToken() {
		if (token == null) {
			while (!tokenizer.hasMoreTokens() || tokenizer == null) {
				try {
					tokenizer = new StringTokenizer(reader.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			token = tokenizer.nextToken();
		}
		return token;
	}

	public static int nextInt() {
		return Integer.parseInt(next());
	}

}

public class Patuljci {

	public static void main(String[] args) {
		int sum = 0;
		int[] gnomes = new int[9];
		for (int i = 0; i < gnomes.length; i++) {
			int gnomeNumber = FastReader.nextInt();
			gnomes[i] = gnomeNumber;
			sum += gnomeNumber;
			//FastReader.out.println(gnomeNumber);
			//FastReader.out.flush();
		}
		int excessive = sum - 100;
		toBreak:
		for (int i = 0; i < gnomes.length; i++) {
			for (int k = 0; k < gnomes.length; k++) {
				if (k != i && (gnomes[i] + gnomes[k] == excessive)) {
					gnomes[i] = -1;
					gnomes[k] = -1;
					break toBreak;
				}
			}
		}
		
		for (int i = 0 ; i < gnomes.length; i++) {
			if(gnomes[i] != -1) {
				FastReader.out.println(gnomes[i]);
				FastReader.out.flush();
			}
		}

	}

}
