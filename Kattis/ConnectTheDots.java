import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
class Kattio2 extends PrintWriter {
    public Kattio2(InputStream i) {
	super(new BufferedOutputStream(System.out));
	r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio2(InputStream i, OutputStream o) {
	super(new BufferedOutputStream(o));
	r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
	return peekToken() != null;
    }

    public int nextInt() {
	return Integer.parseInt(nextToken());
    }

    public double getDouble() { 
	return Double.parseDouble(nextToken());
    }

    public long getLong() {
	return Long.parseLong(nextToken());
    }

    public String next() {
    	return nextToken();
    }
    String nextLine() {
        String str = "";
        try {
			str = r.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return str;
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
		    if (line == null) return null;
		    st = new StringTokenizer(line);
		}
		token = st.nextToken();
	    } catch (IOException e) { }
	return token;
    }

    private String nextToken() {
	String ans = peekToken();
	token = null;
	return ans;
    }
}
public class ConnectTheDots {
	static Kattio2 reader = new Kattio2(System.in,System.out);

	static class Point implements Comparable<Point> {
		int x, y;
		char c;

		public Point(int x, int y, char c) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
		}

		@Override
		public int compareTo(Point arg0) {
			return toIndex(c) - toIndex(arg0.c);
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", c=" + c + "]";
		}

	}

	static int toIndex(char c) {
		if (c >= '0' && c <= '9') {
			return c - '0';
		}
		if (c >= 'A' && c <= 'Z') {
			return 26 * 5 + c - 'A';
		}
		return 30 + c - 'a';
	}

	static int toInt(char c) {
		if (c >= '0' && c <= '9') {
			return c - '0';
		}
		if (c >= 'A' && c <= 'Z') {
			return c - 'A';
		}
		return c - 'a';
	}

	static boolean isAlphNum(char c) {
		if (c >= '0' && c <= '9') {
			return true;
		}
		if (c >= 'A' && c <= 'Z') {
			return true;
		}
		if (c >= 'a' && c <= 'z') {
			return true;
		}
		return false;
	}

	static void connect(char[][] matrix, Point fst, Point snd) {
		if (fst.x == snd.x) {
			// vertical case
			if (fst.y > snd.y) {
				Point t = fst;
				fst = snd;
				snd = t;
			}
			for (int c = fst.y + 1; c < snd.y; c++) {
				if (matrix[fst.x][c] == '|' || matrix[fst.x][c] == '+') {
					matrix[fst.x][c] = '+';
				} else if (!isAlphNum(matrix[fst.x][c])) {
					matrix[fst.x][c] = '-';
				}
			}
		} else {
			if (fst.x > snd.x) {
				Point t = fst;
				fst = snd;
				snd = t;
			}
			for (int r = fst.x + 1; r < snd.x; r++) {
				if (matrix[r][fst.y] == '-' || matrix[r][fst.y] == '+') {
					matrix[r][fst.y] = '+';
				} else if (!isAlphNum(matrix[r][fst.y])) {
					matrix[r][fst.y] = '|';
				}
			}
		}
	}

	public static void main(String[] args) {
		try {
			String line = reader.nextLine();
			boolean done = false;
			while (true) {
				ArrayList<String> lines = new ArrayList();
				try {
					while (!line.equals("")) {
						lines.add(line);
						line = reader.nextLine();
					}
				} catch (Exception e) {
					done = true;
				}
				int R = lines.size();
				int C = lines.get(0).length();
				char[][] matrix = new char[R][C];
				for (int i = 0; i < R; i++) {
					matrix[i] = lines.get(i).toCharArray();
				}

				// first pass extract the numbers
				ArrayList<Point> points = new ArrayList();
				for (int r = 0; r < R; r++) {
					for (int c = 0; c < C; c++) {
						char ch = matrix[r][c];
						if (isAlphNum(ch)) {
							points.add(new Point(r, c, ch));
						}
					}
				}

				Collections.sort(points);
				for (int i = 0; i + 1 < points.size(); i++) {
					Point fst = points.get(i);
					Point snd = points.get(i + 1);
					connect(matrix, fst, snd);
				}

				// print out the result
				for (int r = 0; r < R; r++) {
					for (int c = 0; c < C; c++) {
						reader.print(matrix[r][c]);
					}
					reader.println();
				}
				line = reader.nextLine();
				if(done) {
					break;
				}
				reader.println();
			}
		} catch (Exception e) {
		}
		reader.flush();
	}

}
