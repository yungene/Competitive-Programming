import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Kratki {
	static Kattio reader = new Kattio(System.in, System.out);

	public static void main(String[] args) {
		try {
			int N = reader.getInt();
			int K = reader.getInt();
			long k2 = (long) K * (long) K;
			int[] res = new int[N];
			if (k2 < N) {
				System.out.println(-1);
				System.exit(0);
			}
			if (K == 1 && N == 2) {
				System.out.println(-1);
				System.exit(0);
			}
			int maxGroupSize = K;
			int maxNoOfGroups = K;
			ArrayList<Integer> perm = new ArrayList<>();
			int len = N;
			int sz = maxGroupSize;
			for (int g = 0; g < maxNoOfGroups && len > 0; g++) {
				if (maxGroupSize > len) {
					maxGroupSize = len;
				}
				perm.add(maxGroupSize);
				len -= maxGroupSize;

			}
			int curr = 1;
			for (Integer s : perm) {
				for (int i = s - 1; i >= 0; i--) {
					reader.print(curr + i);
					reader.print(" ");
				}
				curr += s;
			}
			reader.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class Kattio extends PrintWriter {
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

}
