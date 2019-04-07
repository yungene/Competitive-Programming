import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Cryptopangrams {

	static Reader reader = new Reader();

	public static void main(String[] args) {
		try {
			int T = reader.nextInt();
			for (int t = 0; t < T; t++) {
				String N = reader.next();
				int L = reader.nextInt();
				int len = L + 1;
				BigInteger n = new BigInteger(N);
				BigInteger[] X = new BigInteger[L];
				BigInteger[] g = new BigInteger[len]; // function g maps characters to prime numbers
				for (int l = 0; l < L; l++) {
					X[l] = new BigInteger(reader.next());
				}

				for (int x = 1; x < L; x++) {
					BigInteger temp = X[x - 1].gcd(X[x]);
					if (temp.equals(X[x])) {
						if (g[x - 1] != null) {
							g[x] = X[x - 1].divide(g[x - 1]);
						}
					} else {
						g[x] = temp;
					}
				}
				
				for (int x = 1; x < L; x++) {
					if(g[x] == null) {
						if (g[x - 1] != null) {
							g[x] = X[x - 1].divide(g[x - 1]);
						}
					}
				}
				
				for (int x = L-2; x >=1; x--) {
					if(g[x] == null) {
						if (g[x + 1] != null) {
							g[x] = X[x].divide(g[x + 1]);
						}
					}
				}
				g[0] = X[0].divide(g[1]);
				g[L] = X[L - 1].divide(g[L - 1]);

				BigInteger[] alphabet = new BigInteger[26];
				HashSet<BigInteger> set = new HashSet<>();
				int i = 0;
				for (BigInteger p : g) {
					set.add(p);
				}

				for (BigInteger p : set) {
					alphabet[i++] = p;
					if (i == 26) {
						break;
					}

				}
				assert (i == 26);
				// sort in ascending order
				Arrays.sort(alphabet);

				HashMap<BigInteger, Character> gInverse = new HashMap<>();  //inverse function of g, i.e. maps primes to chracters
				for (i = 0; i < 26; i++) {
					gInverse.put(alphabet[i], (char) ('A' + i));
				}

				StringBuilder sb = new StringBuilder();
				for (BigInteger g_in : g) {
					sb.append(gInverse.get(g_in));
				}

				System.out.printf("Case #%d: %s\n", t + 1, sb.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
  
  //helperl class fro fast IO
  static class Reader {
		BufferedReader br;
		StringTokenizer st;

		public Reader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {

			while (st == null || !st.hasMoreTokens()) {

				st = new StringTokenizer(nextLine());
			}
			return st.nextToken();
		}

		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		double nextDouble() throws IOException {
			return Double.parseDouble(next());
		}

		long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		String nextLine() throws IOException {
			String str = "";
			str = br.readLine();
			return str;
		}

	}

}
