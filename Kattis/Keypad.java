import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Keypad {
	static Reader reader = new Reader();

	public static void main(String[] args) {

		try {
			int T = reader.nextInt();
			for (int t = 0; t < T; t++) {
				// input
				int r = reader.nextInt();
				int c = reader.nextInt();
				// reader.nextLine();
				boolean[][] arr = new boolean[r][c];
				char[][] res = new char[r][c];
				for (int i = 0; i < r; i++) {
					String line = reader.nextLine();
					for (int j = 0; j < c; j++) {
						arr[i][j] = line.charAt(j) == '1';
					}
				}
				// solution
				int[] rows = new int[r];
				int[] cols = new int[c];

				// count cols
				for (int i = 0; i < r; i++) {
					int cnt = 0;
					for (int j = 0; j < c; j++) {
						cnt += arr[i][j] ? 1 : 0;
					}
					rows[i] = cnt;
				}
				// count rows
				for (int j = 0; j < c; j++) {
					int cnt = 0;
					for (int i = 0; i < r; i++) {
						cnt += arr[i][j] ? 1 : 0;
					}
					cols[j] = cnt;
				}

				//get the result
				for (int i = 0; i < r; i++) {
					for (int j = 0; j < c; j++) {
						if ((cols[j] == 1 && rows[i] > 0) || 
								(rows[i] == 1 && cols[j] > 0)) {
							res[i][j] = 'P';
						} else if (cols[j] > 0 && rows[i] > 0) {
							res[i][j] = 'I';
						} else {
							res[i][j] = 'N';
						}
					}
				}

				//check if valid
				boolean isValid = true;
				for (int i = 0; i < r; i++) {
					for (int j = 0; j < c; j++) {
						if (res[i][j] != 'N') {
							if(!arr[i][j]) {
								isValid = false;
								break;
							}
						}
					}
				}
				if(!isValid) {
					System.out.println("impossible");
					System.out.println("----------");
					continue;
				}
				// output
				for (int i = 0; i < r; i++) {
					for (int j = 0; j < c; j++) {
						System.out.print(res[i][j]);
					}
					System.out.println();
				}
				System.out.println("----------");

			}

		} catch (IOException e) {
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
