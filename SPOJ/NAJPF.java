import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.spoj.com/problems/NAJPF/
public class NAJPF {
	static class KMP {
		static ArrayList<Integer> buildTable(String w) {
			int i = 2, j = 0;
			ArrayList<Integer> t = new ArrayList<>(w.length());
			for(int q = 0; q < w.length();q++) {
				t.add(0);
			}
			t.set(0, -1);
			t.set(1, 0);
			while (i < w.length()) {
				if (w.charAt(i - 1) == w.charAt(j)) {
					t.set(i, j + 1);
					i++;
					j++;
				} else if (j > 0)
					j = t.get(j);
				else {
					t.set(i, 0);
					i++;
				}
			}
			return t;
		}

		static Pair<Integer, ArrayList<Integer>> KMP(String s, String w) {
			int m = 0, i = 0;
			ArrayList<Integer> resArr = new ArrayList<>();
			int count = 0;
			ArrayList<Integer> t = buildTable(w);
			while (m + i < s.length()) {
				if (w.charAt(i) == s.charAt(m + i)) {
					i++;
					if (i == w.length()) {
						count++;
						resArr.add(m);
            // Could be imporved
            // Should be slow for s = (a)+ and w = aa
						m = m+i;
						i = 0;
					}
				} else {
					m += i - t.get(i);
					if (i > 0)
						i = t.get(i);
				}
			}
			return new Pair<Integer, ArrayList<Integer>>(count, resArr);
		}
	}

	static class Pair<L, R> {
		L left;

		public Pair(L left, R right) {
			super();
			this.left = left;
			this.right = right;
		}

		R right;

	}

	static Reader reader = new Reader();

	public static void main(String[] args) {
		try {
			int T = reader.nextInt();
			for (int t = 0; t < T; t++) {
				Pair<Integer, ArrayList<Integer>> res = KMP.KMP(reader.next(),reader.next());
				if(res.left>0) {
					System.out.println(res.left);
					for(int m : res.right) {
						System.out.print(m+1);
						System.out.print(" ");
					}
					System.out.println();
					System.out.println();

				} else {
					System.out.println("Not Found");
					System.out.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  
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
