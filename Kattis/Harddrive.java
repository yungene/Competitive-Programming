import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Harddrive {
	static class Reader {
		BufferedReader br;
		StringTokenizer st;

		public Reader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() {

			while (st == null || !st.hasMoreTokens()) {

				st = new StringTokenizer(nextLine());
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return str;
		}

	}

	static Reader reader = new Reader();
	public static void main(String[] args) {
		int n = reader.nextInt();
		int c = reader.nextInt();
		int b = reader.nextInt();
		int[][] zeros = new int[b+1][2];
		zeros[0][0]=-1;
		zeros[1][0] = reader.nextInt()-1;
		zeros[0][1]=zeros[1][0];
		zeros[b-1][1] = -1;
		for(int i = 2; i < b+1 ;i++) {
			zeros[i][0] =reader.nextInt()-1;
			// find distance to next zero
			zeros[i-1][1] = zeros[i][0]-zeros[i-1][0]-1;
		}
		BitSet bs = new BitSet(n);
		int curr = 0;
		int set = 0;
		boolean prevSet = false;
		if(c%2==1) {
			bs.set(0);
			curr+=2;
			prevSet = true;
			set++;
			zeros[0][0] = 1;
			zeros[0][1] = zeros[1][0]-zeros[0][0]-1;
		}else {
			zeros[0][0]=0;
			zeros[0][1] = zeros[1][0]-zeros[0][0]-1;
		}
		for(int j = 0; j < b+1 && set<c;j++) {
			int distToNext = zeros[j][1];
			int max = 0;
			if(distToNext%2==1) {
				max = ((distToNext/2)+1)*2;
			} else {
				max = distToNext;
			}
			int delta = c - set;
			if(max>delta) {
				max = delta%2==1?delta-1:delta;
			}
			int ptr = zeros[j][0]+1;
			for(int i = 0; i < max; i+=2) {
				bs.set(ptr);
				ptr+=2;
				set+=2;
			}
		}
		for(int i = 0 ; i< n;i++) {
			if(bs.get(i)) {
				System.out.print(1);
			} else {
				System.out.print(0);
			}
		}
		//IntStream.range(0, n).mapToObj(i -> bs.get(i) ? '1' : '0').forEach(System.out::append);
		//System.out.println(toString(bs,n));
	}

	static String toString(BitSet bs, int nbits) {
        final StringBuilder buffer = new StringBuilder(nbits);
        IntStream.range(0, nbits).mapToObj(i -> bs.get(i) ? '1' : '0').forEach(buffer::append);
        return buffer.toString();
    }
}
