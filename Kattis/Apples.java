import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Apples {

	static Reader reader = new Reader();
	static char APPLE = 'a';
	static char EMPTY = '.';
	static char OBSTACLE = '#';

	public static void main(String[] args) {
		try {
			int R = reader.nextInt();
			int C = reader.nextInt();
			char[][] arr = new char[R][C];

    //read in the input arrays
			for (int r = 0; r < R; r++) {
				String line = reader.nextLine();
				for (int c = 0; c < C; c++) {
					arr[r][c] = line.charAt(c);
				}
			}
			
			// fix each columns
			for(int c = 0; c < C; c++) {
				moveCol(arr, c);
			}
			
      //print out the array
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					System.out.print(arr[r][c]);
				}
				System.out.println();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void  moveCol(char[][] arr ,int col) {
  //writer pointer
		int pw = arr.length -1;
    //reader pointer
		int pr = pw;
		while(pr >= 0 && pw >= 0) {
			while(pr >= 0 && arr[pr][col] == EMPTY) {
				pr--;
			}
			
			if(pr >= 0 && arr[pr][col] == OBSTACLE) {
				while(pw >= 0 && arr[pw][col] != OBSTACLE) {
					arr[pw][col] = EMPTY;
					pw--;
				}
				pw = pr - 1;
				pr--;
			} else if (pr >= 0){
				arr[pw][col] = arr[pr][col];
				pw--;
				pr--;
			}
			
		}
		
		while(pw >= 0 && arr[pw][col] != OBSTACLE) {
			arr[pw][col] = EMPTY;
			pw--;
		}
		
	}
  
  
  //auxillary class
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
