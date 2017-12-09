// https://open.kattis.com/problems/polymul1
public class PolynomialMultiplication {

	public static void main(String[] args) {
		//Scanner reader = new Scanner(System.in);
		Kattio io = new Kattio(System.in, System.out);
		int noOfCases = io.getInt();
		for (int c = 0; c < noOfCases; c++) {
			// first polynomial
			int degree = io.getInt();
			int[] polynomial = new int[degree + 1];
			for (int index = 0; index < polynomial.length; index++) {
				polynomial[index] = io.getInt();
			}
			// second polynomial
			int degree2 = io.getInt();
			int[] polynomial2 = new int[degree2 + 1];
			for (int i = 0; i < polynomial2.length; i++) {
				polynomial2[i] = io.getInt();
			}
			// print out final degree
			int resultDegree = degree + degree2;
			io.println(resultDegree);
			// printing out the coefficients of the resulting polynomial
			io.print(polynomial[0] * polynomial2[0] + " ");
			for (int k = 1; k < resultDegree + 1; k++) {
				int coefficient = 0;
				for (int q = 0; q < polynomial.length; q++) {
					// System.out.println("q = " + q);
					int w = k - q;
					if (w < polynomial2.length && w >= 0) {
						coefficient += polynomial[q] * polynomial2[w];
					}
				}
				io.print(coefficient + " ");
			}
			if (c != noOfCases) {
				io.println();
			}
			io.flush();
		}
		io.close();
	}

}

// kattis utility for faster i/o
/** Simple yet moderately fast I/O routines.
 *
 * Example usage:
 *
 * Kattio io = new Kattio(System.in, System.out);
 *
 * while (io.hasMoreTokens()) {
 *    int n = io.getInt();
 *    double d = io.getDouble();
 *    double ans = d*n;
 *
 *    io.println("Answer: " + ans);
 * }
 *
 * io.close();
 *
 *
 * Some notes:
 *
 * - When done, you should always do io.close() or io.flush() on the
 *   Kattio-instance, otherwise, you may lose output.
 *
 * - The getInt(), getDouble(), and getLong() methods will throw an
 *   exception if there is no more data in the input, so it is generally
 *   a good idea to use hasMoreTokens() to check for end-of-file.
 *
 * @author: Kattis
 */

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

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
