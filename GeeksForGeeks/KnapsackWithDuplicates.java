import java.util.*;
import java.lang.*;
import java.io.*;
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

/* Problem: Knapsack with Duplicate Items from GeeksForGeeks
* 
*/
public class KnapsackWithDuplicates
 {
  static Kattio reader = new Kattio(System.in, System.out);
	public static void main (String[] args)
	 {
	    int T = reader.getInt();
	    for(int t = 0; t < T; t++){
	        int N = reader.getInt();
	        int W = reader.getInt();
	        int[] vals = new int[N];
	        int[] wt = new int[N];
	        for(int i = 0 ;i < N; i++){
	            vals[i] = reader.getInt();
	        }
	        for(int i = 0 ;i < N; i++){
	            wt[i] = reader.getInt();
	        }
	        // can be soved with DP, since we have a 
	        // common subproblem = max value for a weight
	        // 
	        // stores the max value for a specific weight
	        int[] arr = new int[W+1];
	        arr[0] = 0;// no item with wt = 0
	        //for each partial weight
	        for(int w = 0; w < W; w++){
	            // for each item
	            for(int i = 0; i < N; i++){
	                int weight = wt[i];
                  /* max value with "weight" = w + weight is etiher
                  * the previous max value for w + weight or max value for
                  * just w + value of the item with weight weight=wt[i]
                  */
	                if(w + weight <= W){
	                    arr[w+weight] = Math.max(arr[w+weight],
	                            arr[w] + vals[i]);
	                }
	            }
	        }
	        reader.println(arr[W]);
	        reader.flush();
	    }
	    
	 }
}

//Helper class for faster reading of input
// @author Kattis
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
