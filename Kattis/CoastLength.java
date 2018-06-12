import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;
// Kattis problem id:coast, difficulty: 2.9
public class CoastLength {
	static Kattio reader = new Kattio(System.in);
	public static void main(String[] args) {
		int n = reader.getInt();		//# rows
		int m = reader.getInt();			//# cols
		int[][] map = new int[n][m];		// map of the area, where 0 - water, 1- land
		boolean[][] used = new boolean[n][m];	
		for (int i = 0; i < n; i++) {
			char[] line = reader.getWord().toCharArray();
			for (int j = 0; j < m; j++) {
				map[i][j] = line[j] - '0';
			}
		}
		boolean[][] hasExit = new boolean[n][m]; //indicates whether there is a path to the
												// outer see from the cell
		// mark all paths dynamically starting with every border cell
		for (int r = 0; r < n; r++) {
			solve(map, used, r, 0, hasExit);
			solve(map, used, r, m-1, hasExit);
		}
		for (int c = 0; c < m; c++) {
			solve(map, used, 0, c, hasExit);
			solve(map, used, n-1, c, hasExit);
		}
		// count the number of border
		int border = 0;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				// for each piece of land
				if (map[r][c] == 1) {
					// count outer edges
					if (r - 1 < 0)
						border += 1;
					if (r + 1 == n)
						border += 1;
					if ( c - 1 < 0)
						border += 1;
					if (c+ 1 == m)
						border += 1;
					// is coast iff there is a path to the outer see
					if (r - 1 >= 0 && hasExit[r - 1][c])
						border++;
					if (r + 1 < map.length && hasExit[r + 1][c])
						border++;
					if (c - 1 >= 0 && hasExit[r][c - 1])
						border++;
					if (c + 1 < map[0].length && hasExit[r][c + 1])
						border++;
				}
			}
		}
		reader.println(border);
		reader.flush();

	}
	
	static void solve(int[][] map, boolean[][] used, int i, int j, boolean[][] hasExit) {
		if(used[i][j]) {
			return;
		}
		used[i][j] = true;
		if(map[i][j] == 1) {
			return;
		}
		hasExit[i][j] = true;
		if (i - 1 >= 0)
			solve(map, used, i - 1, j, hasExit);
		if (i + 1 < map.length)
			solve(map, used, i + 1, j, hasExit);
		if (j - 1 >= 0)
			solve(map, used, i, j - 1, hasExit);
		if (j + 1 < map[0].length)
			solve(map, used, i, j + 1, hasExit);
	}

}


//Auxilary class for fast IO provided by Kattis
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
