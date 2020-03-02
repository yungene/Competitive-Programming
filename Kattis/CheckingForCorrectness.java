import java.util.Scanner;

public class CheckingForCorrectness {
	static Scanner reader = new Scanner(System.in);
	static final long MOD = 10_000;

	public static void main(String[] args) {
		try {
			while (true) {
				long a = reader.nextLong();
				char op = reader.next().charAt(0);
				long b = reader.nextLong();
				long res = 0;
				long am = a % MOD;
				long bm = b % MOD;
				switch (op) {
				case '+':
					res = am + bm;
					break;
				case '*':
					res = am * bm;
					break;
				case '^':
					res = exp(am, b);
					break;
				}
				System.out.println(res % MOD);
			}
		} catch (Exception e) {

		}
	}

	private static long exp(long a, long b) {
		long res = 1;

		while (b > 0) {
			if ((b & 1) == 1) {
				res = (res * a) % MOD;
			}
			b = b >> 1; // b = b / 2
			a = (a * a) % MOD;
		}
		return res;

	}

}
