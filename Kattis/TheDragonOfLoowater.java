import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;


// @author: yungene
// @date: 2015/05/15
// problem id=loowater, Kattis
public class TheDragonOfLoowater {
	private static Kattio reader = new Kattio(System.in);

	public static void main(String[] args) {

		int n = reader.getInt(); // # of heads
		int m = reader.getInt(); // # of knights

		// 1 knight per 1 head
		// in order to chop Height[i] >= Diameter[j]
		// wage = Height[i]

		while (n != 0 && m != 0) {
			boolean isPossible = true;
			int wage = 0;
			if (m >= n) {
				// int[] diameters = new int[n];
				// int[] knights = new int[m];
				// keep 2 priority queues. Poll min head and see if appropriate
				// min knight is available
				// PriorityQueues are said to work in log(n) time
				// for smaller expenses always pick the smallest possible knight
				PriorityQueue<Integer> queueHeads = new PriorityQueue<>(n);
				PriorityQueue<Integer> queueKnights = new PriorityQueue<>(m);
				for (int i = 0; i < n; i++) {
					// diameters[i] = reader.getInt();
					queueHeads.add(reader.getInt());
				}
				for (int i = 0; i < m; i++) {
					// knights[i] = reader.getInt();
					queueKnights.add(reader.getInt());
				}

				while (!queueHeads.isEmpty() && isPossible) {
					int diameter = queueHeads.poll();
					int height = queueKnights.poll();
					while (height < diameter && !queueKnights.isEmpty()) {
						height = queueKnights.poll();
					}
					
					if ((height < diameter && queueKnights.isEmpty() )|| queueKnights.size() < queueHeads.size()) {
						isPossible = false;
					} else {
						wage += height;
					}
				}
			} else {
				isPossible = false;
				//discard all the inputs
				for (int i = 0; i < n; i++) {
					reader.getInt();
				}
				for (int i = 0; i < m; i++) {
					reader.getInt();
				}
			}
			
			if(isPossible) {
				reader.printf("%d%n", wage);
			} else {
				reader.println("Loowater is doomed!");
			}
			reader.flush();

			n = reader.getInt();
			m = reader.getInt();
		}

	}

}


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
