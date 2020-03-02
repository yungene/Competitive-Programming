import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
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

public class Fenwick {
	static Kattio reader = new Kattio(System.in, System.out);

	public static void main(String[] args) {
			int N = reader.getInt();
			int Q = reader.getInt();
			FenwickTree ft = new FenwickTree(N+5);
			for(int q = 0; q < Q; q++) {
				char op = reader.getWord().charAt(0);
				int i = reader.getInt();
				switch(op) {
				case '+':
					long d = reader.getLong();
					ft.update(i+1, d);
					break;
				case '?':
					if(i == 0) {
						reader.println(0);
					}else {
						reader.println(ft.query(i));
					}
					break;
				}
			}
			reader.flush();
	}

}

//// Fenwick tree to store cummualtive sum
//class FenwickTree{
//	// bit[i] stores the cumulative sum from i to i-(1<<r)+1 (both inclusive)
//	// where r is the last set bit in the index i
//	long[] bit;
//	int n;
//	FenwickTree(int n){
//		bit = new long[n];
//		this.n = n;
//	}
//	
//	// at val at index x
//	void update(int x, long val) {
//		// x&-x gets the lowest power of two in the number x
//		for(; x <= n; x += x&-x) {
//			bit[x] += val;
//		}
//	}
//	
//	long query(int x) {
//		long sum = 0;
//		for(; x >0; x -= x&-x) {
//			sum+= bit[x];
//		}
//		return sum;
//	}
//}















