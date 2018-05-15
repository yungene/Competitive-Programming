package Kattis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class wheresmyinternet {
	private static Kattio reader = new Kattio(System.in, System.out);

	public static void main(String[] args) {

		int N = reader.getInt(); // # of houses
		int M = reader.getInt(); // # of connections

		ArrayList<ArrayList<Integer>> houses = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < N; i++) {
			houses.add(new ArrayList<Integer>());
		}

		int[] connections = new int[N];
		for (int i = 0; i < M; i++) {
			int a = reader.getInt();
			int b = reader.getInt();
			houses.get(a - 1).add(b - 1);
			houses.get(b - 1).add(a - 1);

		}

		boolean connected = true;
		int firstHouse = 0;
		connect(houses, connections, 0);
		connections[0] = 1;
		for (int i = 0; i < N; i++) {
			if (connections[i] == 0) {
				firstHouse = i;
				connected = false;
				break;
			}
		}

		if (connected) {
			reader.println("Connected");
		} else {
			for (int i = firstHouse; i < N; i++) {
				if (connections[i] == 0) {
					reader.println(i + 1);
				}
			}
		}
		reader.flush();
	}
	public static void connect(ArrayList<ArrayList<Integer>> houses, int[] connections, int index) {
		if (connections[index] == 1) {
			return;

		}
		connections[index] = 1;
		ArrayList<Integer> set = houses.get(index);
		for (int i : set) {
			if (connections[i] != 1) {
				connect(houses, connections, i);
			}
		}
		connections[0] = 1;
		return;
	}

}

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

