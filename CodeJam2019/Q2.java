import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2 {
	static Reader reader = new Reader();

	public static void main(String[] args) {
		try {
			int T = reader.nextInt();
			for (int t = 0; t < T; t++) {
				int N = reader.nextInt();
				int sz = 2 * N - 2;
				String P = reader.nextLine();
				
				StringBuilder sb = new StringBuilder();
				
				for(char c:P.toCharArray()) {
					if(c == 'S') {
						sb.append("E");
					}else {
						sb.append("S");
					}
				}
				
				System.out.printf("Case #%d: %s\n", t + 1, sb.toString());
			}
		} catch (Exception e) {
			
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
