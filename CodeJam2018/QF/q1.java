import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;


//Saving The Universe Again - Codejam 2018 QF question 1 - passed both test cases

public class q1 {

	public static void main(String[] args) {
		Kattio reader = new Kattio(System.in, System.out);
		int noOfCases = reader.getInt();
		for (int t = 0; t < noOfCases; t++) {
			int hp = reader.getInt();
			String p = reader.getWord();
			char[] seq = p.toCharArray();
			if (isValid(seq, hp)) {
				reader.print(String.format("Case #%d: %d%n", t + 1, 0));
			} else if (countS(seq, hp) > hp) {
				reader.print(String.format("Case #%d: %s%n", t + 1, "IMPOSSIBLE"));
			} else {
				// we need inDamage <= hp
				int noC = countC(seq, hp);
				int maxDamage = (int) Math.pow(2, noC);
				int inDamage = countDamage(seq, hp);
				int conseqS = 0;
				int conseqC = 0;
				int count = 0;
				boolean toTest = true;
				cont: for (int i = seq.length - 1; i >= 0; i--) {
					if (seq[i] == 'S') {
						// process end of c's
						int cMoved = 0;
						if (conseqC > 0 && conseqS > 0) {
							for (int c = 0; c < conseqC; c++) {
								if (inDamage - (conseqS * maxDamage / 2) > hp) {
									count += conseqS;
									inDamage -= (conseqS * maxDamage) / 2;
									maxDamage = maxDamage / 2;
									cMoved++;
								} else {
									for (int s = 0; s < conseqS; s++) {
										if (inDamage - (s + 1) * maxDamage / 2 <= hp) {
											count += s + 1;
											toTest = false;
											break;
										}
									}
									break cont;
								}
							}
						}
						// start again
						if (conseqC != 0) {
							if (cMoved == 0) {
								maxDamage = (int) Math.pow(2, noC - conseqC);
							}
						}
						conseqC = 0;
						conseqS += 1;
					} else {
						conseqC += 1;
					}
				}
				if (toTest && conseqC > 0 && conseqS > 0) {
					for (int c = 0; c < conseqC; c++) {
						if (inDamage - conseqS * maxDamage / 2 > hp) {
							count += conseqS;
							inDamage -= (conseqS * maxDamage) / 2;
							maxDamage = maxDamage / 2;
						} else {
							for (int s = 0; s < conseqS; s++) {
								if (inDamage - (s + 1) * maxDamage / 2 <= hp) {
									count += s + 1;
									break;
								}
							}
							break;
						}
					}
				}
				reader.print(String.format("Case #%d: %d%n", t + 1, count));
			}
			reader.flush();
		}

	}

	public static boolean isValid(char[] seq, int hp) {
		int power = 1;
		int damage = 0;
		for (int i = 0; i < seq.length; i++) {
			if (seq[i] == 'C') {
				power *= 2;
			} else {
				damage += power;
			}
		}
		return hp >= damage;
	}

	public static int countDamage(char[] seq, int hp) {
		int power = 1;
		int damage = 0;
		for (int i = 0; i < seq.length; i++) {
			if (seq[i] == 'C') {
				power *= 2;
			} else {
				damage += power;
			}
		}
		return damage;
	}

	public static int countS(char[] seq, int hp) {
		int count = 0;
		for (int i = 0; i < seq.length; i++) {
			if (seq[i] == 'S') {
				count++;
			}
		}
		return count;
	}

	public static int countC(char[] seq, int hp) {
		int count = 0;
		for (int i = 0; i < seq.length; i++) {
			if (seq[i] == 'C') {
				count++;
			}
		}
		return count;
	}

	public static void shift(char[] seq, int hp) {
		for (int i = seq.length - 1; i > 0; i--) {
			if (seq[i] == 'S' && seq[i - 1] == 'C') {
				seq[i] = 'C';
				seq[i - 1] = 'S';
				return;
			}
		}
	}

}


//Helper class for faster IO, author: Kattis
class Kattio extends PrintWriter {
	public Kattio(InputStream i) {
		super(new BufferedOutputStream(System.out));
		r = new BufferedReader(new InputStreamReader(i));
	}

	public Kattio(InputStream i, OutputStream o) {
		super(new BufferedOutputStream(o));
		r = new BufferedReader(new InputStreamReader(i));
	}

	public boolean hasMoreTokens() {
		return peekToken() != null;
	}

	public int getInt() {
		return Integer.parseInt(nextToken());
	}

	public double getDouble() {
		return Double.parseDouble(nextToken());
	}

	public long getLong() {
		return Long.parseLong(nextToken());
	}

	public String getWord() {
		return nextToken();
	}

	private BufferedReader r;
	private String line;
	private StringTokenizer st;
	private String token;

	private String peekToken() {
		if (token == null)
			try {
				while (st == null || !st.hasMoreTokens()) {
					line = r.readLine();
					if (line == null)
						return null;
					st = new StringTokenizer(line);
				}
				token = st.nextToken();
			} catch (IOException e) {
			}
		return token;
	}

	private String nextToken() {
		String ans = peekToken();
		token = null;
		return ans;
	}
}
