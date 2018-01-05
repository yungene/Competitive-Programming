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

public class RestaurantRating {
	public static void main(String[] args) {
		int noOfCritics;
		int maxSum = 30;
		noOfCritics = FastReader.nextInt();
		do {
			int[] grades = new int[noOfCritics];
			int sum = 0;
			long restaurants = 0L;
			for (int c = 0; c < noOfCritics; c++) {
				int grade = FastReader.nextInt();
				sum += grade;
				grades[c] = grade;
			}
			for (int i = 0; i < sum; i++) {
				restaurants += weakComposition(noOfCritics, i);
			} // correct up to this
			int used = 0;
			for (int k = 0; k < grades.length - 1; k++) {
				for (int l = 0; l < grades[k]; l++) {
					restaurants += weakComposition( grades.length - k - 1, sum - used - l);
				}
				used += grades[k];
			}
			restaurants++;
			FastReader.out.println(restaurants);
			FastReader.out.flush();
			noOfCritics = FastReader.nextInt();
		} while (noOfCritics != 0);

	}
	public static long weakComposition(int integers, int sum) {
		return combination(sum + integers - 1, integers - 1);
	}

	public static long combination(int n, int k) {
		long nCk = 1L;
		if (k <= n) {
			for (int i = 0; i < k; i++) {
				nCk = nCk * (n - i) / (i + 1);
			}
			return nCk;
		} else {	//unnecessary
			return combination(n + k - 1, k);
		}
	}
}
