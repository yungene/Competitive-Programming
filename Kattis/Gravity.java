import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

class Kattio1 extends PrintWriter {
    public Kattio1(InputStream i) {
	super(new BufferedOutputStream(System.out));
	r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio1(InputStream i, OutputStream o) {
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
public class Gravity {
	static Kattio1 reader = new Kattio1(System.in,System.out);
	static int setn;
	static int R;
	static int C;

	public static void main(String[] args) {
		R = reader.nextInt();
		C = reader.nextInt();
		//reader.nextLine();
		while (R != 0 && C != 0) {
			char[][] matrix = new char[R][C];
			for (int r = 0; r < R; r++) {
				String line = reader.nextLine();
				for (int c = 0; c < C; c++) {
					matrix[r][c] = line.charAt(c);
				}
			}
			boolean changed = true;
			while (changed) {
				changed = false;
				// first pass assign sets to each block
				UF union = new UF(R * C);
				for (int r = 0; r < R; r++) {
					for (int c = 0; c < C; c++) {
						if (matrix[r][c] == ' ') {
							continue;
						}
						for (int i = r - 1; i <= r + 1; i++) {
							if (i != r) {
								if (i < 0 || i >= R) {
									continue;
								}
								if (matrix[i][c] != ' ') {
									union.union(C * r + c, C * i + c);
								}
							}
						}
						for (int j = c - 1; j <= c + 1; j++) {
							if (j != c) {
								if (j < 0 || j >= C) {
									continue;
								}
								if (matrix[r][j] != ' ') {
									union.union(C * r + c, C * r + j);
								}
							}
						}
					}
				}

				// second pass - find out whether the set must fall down or not
				Set<Integer> noFall = new HashSet();
				for (int c = 0; c < C; c++) {
					noFall.add(union.find((R - 1) * C + c));
				}
				char[][] newMatrix = new char[R][C];
				for (int r = 0; r < R; r++) {
					for (int c = 0; c < C; c++) {
						newMatrix[r][c] = ' ';
					}
				}

				// third pass - update the pane
				for (int r = 0; r < R; r++) {
					for (int c = 0; c < C; c++) {
						if(matrix[r][c] == ' ') {
							continue;
						}
						if (noFall.contains(union.find(r * C + c))) {
							newMatrix[r][c] = matrix[r][c];
						} else if (r+1 < R){
							changed = true;
							newMatrix[r + 1][c] = matrix[r][c];
						}
					}
				}
				matrix = newMatrix;
			}
			// print out the pane
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					System.out.print(matrix[r][c]);
				}
				System.out.println();
			}

			System.out.println();
			R = reader.nextInt();
			C = reader.nextInt();
			//reader.nextLine();

		}
	}

	static int getSet(int[][] sets, int r, int c) {
		if (r < 0 || r >= R || c < 0 || c >= C) {
			return 0;
		}
		return sets[r][c];
	}

	static int find(int[] C, int x) {
		if ((C[x] == x)) {
			return x;
		} else {
			C[x] = find(C, C[x]);
			return C[x];
		}
	}

	static void merge(int[] C, int x, int y) {
		C[find(C, x)] = find(C, y);
	}

	static void init(int[] C) {
		for (int i = 0; i < C.length; i++) {
			C[i] = i;
		}
	}
}

class UF {

	private int[] parent; // parent[i] = parent of i
	private byte[] rank; // rank[i] = rank of subtree rooted at i (never more than 31)
	private int count; // number of components

	/**
	 * Initializes an empty union-find data structure with {@code n} elements
	 * {@code 0} through {@code n-1}. Initially, each elements is in its own set.
	 *
	 * @param n the number of elements
	 * @throws IllegalArgumentException if {@code n < 0}
	 */
	public UF(int n) {
		if (n < 0)
			throw new IllegalArgumentException();
		count = n;
		parent = new int[n];
		rank = new byte[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}

	/**
	 * Returns the canonical element of the set containing element {@code p}.
	 *
	 * @param p an element
	 * @return the canonical element of the set containing {@code p}
	 * @throws IllegalArgumentException unless {@code 0 <= p < n}
	 */
	public int find(int p) {
		while (p != parent[p]) {
			parent[p] = parent[parent[p]]; // path compression by halving
			p = parent[p];
		}
		return p;
	}

	/**
	 * Returns the number of sets.
	 *
	 * @return the number of sets (between {@code 1} and {@code n})
	 */
	public int count() {
		return count;
	}

	/**
	 * Returns true if the two elements are in the same set.
	 *
	 * @param p one element
	 * @param q the other element
	 * @return {@code true} if {@code p} and {@code q} are in the same set;
	 *         {@code false} otherwise
	 * @throws IllegalArgumentException unless both {@code 0 <= p < n} and
	 *                                  {@code 0 <= q < n}
	 * @deprecated Replace with two calls to {@link #find(int)}.
	 */
	@Deprecated
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	/**
	 * Merges the set containing element {@code p} with the the set containing
	 * element {@code q}.
	 *
	 * @param p one element
	 * @param q the other element
	 * @throws IllegalArgumentException unless both {@code 0 <= p < n} and
	 *                                  {@code 0 <= q < n}
	 */
	public void union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
		if (rootP == rootQ)
			return;

		// make root of smaller rank point to root of larger rank
		if (rank[rootP] < rank[rootQ])
			parent[rootP] = rootQ;
		else if (rank[rootP] > rank[rootQ])
			parent[rootQ] = rootP;
		else {
			parent[rootQ] = rootP;
			rank[rootP]++;
		}
		count--;
	}

	// validate that p is a valid index
	private void validate(int p) {
		int n = parent.length;
		if (p < 0 || p >= n) {
			throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
		}
	}
}
