import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.PrintWriter;

public class Billiard {

	public static void main(String[] args) {
		while (true) {
			int a = FastReader.nextInt();
			int b = FastReader.nextInt();
			int s = FastReader.nextInt();
			int m = FastReader.nextInt();
			int n = FastReader.nextInt();
			if (a == 0 && b == 0 && s == 0 && m == 0 && n == 0) {
				break;
			}
			double bn2 = Math.pow(b * n, 2);
			double am2 = Math.pow(a * m, 2);
			double velocity = Math.sqrt(am2 + bn2) / s;
			// velocity *= 100;
			// velocity = (double)(((int) velocity )/ 100.0);
			// FastReader.out.println("Velocity: " + velocity);
			// FastReader.out.flush();
			double cosine = (a * m) / (velocity * s);
			double angle = Math.acos(cosine);
			FastReader.out.printf("%.2f %.2f%n", Math.toDegrees(angle), velocity);
			FastReader.out.flush();

		}

	}

}

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

	public static double nextDouble() {
		return Double.parseDouble(next());
	}

}
