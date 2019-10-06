import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class RomanHolidays {
	static Reader reader = new Reader();
	
	static ArrayList<Pair>  intToRoman = new ArrayList<Pair>();
	static ArrayList<String> romanNums = new ArrayList<>(1000);
	static Map<Integer,String> intToRomanMap = new HashMap();
	static int mPos = -1;
	static {
		intToRoman.add(new Pair(1000,"M"));
		intToRoman.add(new Pair(900,"CM"));
		intToRoman.add(new Pair(800,"DCCC"));
		intToRoman.add(new Pair(700,"DCC"));
		intToRoman.add(new Pair(600,"DC"));
		intToRoman.add(new Pair(500,"D"));
		intToRoman.add(new Pair(400,"CD"));
		intToRoman.add(new Pair(300,"CCC"));
		intToRoman.add(new Pair(200,"CC"));
		intToRoman.add(new Pair(100,"C"));
		intToRoman.add(new Pair(90,"XC"));
		intToRoman.add(new Pair(80,"LXXX"));
		intToRoman.add(new Pair(70,"LXX"));
		intToRoman.add(new Pair(60,"LX"));
		intToRoman.add(new Pair(50,"L"));
		intToRoman.add(new Pair(40,"XL"));
		intToRoman.add(new Pair(30,"XXX"));
		intToRoman.add(new Pair(20,"XX"));
		intToRoman.add(new Pair(10,"X"));
		intToRoman.add(new Pair(9,"IX"));
		intToRoman.add(new Pair(8,"VIII"));
		intToRoman.add(new Pair(7,"VII"));
		intToRoman.add(new Pair(6,"VI"));
		intToRoman.add(new Pair(5,"V"));
		intToRoman.add(new Pair(4,"IV"));
		intToRoman.add(new Pair(3,"III"));
		intToRoman.add(new Pair(2,"II"));
		intToRoman.add(new Pair(1,"I"));
		
		for(int i = 1; i <= 1000; i++) {
			// convert to roman
			String res = intToRoman(i);
			romanNums.add(res);
			intToRomanMap.put(i, res);
		}
		
		Collections.sort(romanNums);
		for(int i = 1 ; i < romanNums.size(); i++) {
			if(romanNums.get(i).equals("M")) {
				mPos = i;
				break;
			}
		}
	}

	public static void main(String[] args) {
		
		try {
			int n;
			n = reader.nextInt();
			
			ArrayList<String> romans = new ArrayList<>();
			for(int t = 0; t < n; t++) {
				int val = reader.nextInt();
				int res = -1;
				int q = val / 1000;
				int r = val % 1000; 
				if( val <= 1000) {
					int i = romanNums.indexOf(intToRomanMap.get(val));
					if(i>mPos) {
						res = -1* (romanNums.size()-i);
					} else {
						res = i+1;
					}
				} else {
					int i = romanNums.indexOf(intToRomanMap.get(r));
					if(i>mPos) {
						res = -1*(romanNums.size()-i) - (1000-(mPos+1))*(q);
					} else {
						res = q*(mPos+1) + i+1;
					}
				}
				System.out.println(res);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	static String intToRoman(int n) {
		StringBuilder sb = new StringBuilder("");
		
		while(n!=0) {
			for(Pair p: intToRoman) {
				if(n-p.left >=0) {
					n -= p.left;
					sb.append(p.right);
				}
			}
		}
		
		return sb.toString();
	}
	
	static class Pair{
		int left;
		String right;
		public Pair(int left, String right) {
			super();
			this.left = left;
			this.right = right;
		}
	}
	
	static class Reader {
		BufferedReader br;
		StringTokenizer st;

		public Reader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException{

			while (st == null || !st.hasMoreTokens()) {

				st = new StringTokenizer(nextLine());
			}
			return st.nextToken();
		}

		int nextInt() throws IOException{
			return Integer.parseInt(next());
		}

		double nextDouble() throws IOException{
			return Double.parseDouble(next());
		}

		long nextLong() throws IOException{
			return Long.parseLong(next());
		}

		String nextLine() throws IOException {
			String str = "";
			str = br.readLine();
			return str;
		}

	}

}
