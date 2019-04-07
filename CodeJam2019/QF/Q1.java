import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

//Foregone Solution
public class Q1 {
	
	static Kattio reader = new Kattio(System.in, System.out);

	public static void main(String[] args) {
		
		int T = reader.getInt();
		for(int t = 0; t < T ; t++) {
			String N = reader.nextToken();//get the big integer
			int A = 0, B = 0;
			BigInteger n = new BigInteger(N);
			BigInteger a=null, b = null;
				StringBuilder sb = new StringBuilder();
				for(int i = N.length() -1; i >= 0; i--) {
					char d = N.charAt(i);
					char ins = '0';
					if(d == '4') {
						ins = (char) (d - 1);
					}
					sb.append(ins);
				}
				sb = sb.reverse();
				a = new BigInteger(sb.toString());
				b = n.subtract(a);
			if(!n.equals(a.add(b))) {
				System.out.println("fail");
				throw new RuntimeException();
			}
			reader.printf("Case #%d: %s %s\n", t+1,a.toString(), b.toString());
			reader.flush();
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
