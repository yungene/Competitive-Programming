import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class SixDegrees {
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
	static int nodeCount;
	static int N = 3002;
	public static void main(String[] args) {
		int T = reader.nextInt();
		for(int t = 0; t < T; t++) {
			 nodeCount = 0;
			Map<String, Integer> id = new HashMap<>();
			int M = reader.nextInt();
			List<Integer> adj[] = new ArrayList[N];
			for(int i = 0; i < adj.length; i++) {
				adj[i] = new ArrayList<>();
			}
			for(int m = 0; m < M; m++) {
				String d1 = reader.next();
				String d2 = reader.next();
				int ui = 0, vi = 0;
				if(!id.containsKey(d1)) {
					ui = nodeCount;
					id.put(d1, nodeCount++);
				}
				if(!id.containsKey(d2)) {
					vi = nodeCount;
					id.put(d2, nodeCount++);
				}
				ui = id.get(d1);
				vi = id.get(d2);
				adj[ui].add(vi);
				adj[vi].add(ui);
			}
			
			HashSet<Integer> res = new HashSet();
			// run a bfs for 5 levels
			for(int i =0;i  < nodeCount; i++) {
				boolean[] visited = new boolean[N];
				bfs(adj,visited, i, 6);
				for(int j =0; j < nodeCount ; j++) {
					if(!visited[j]) {
						res.add(j);
					}
				}
			}
//			Map<String, Set<String>> sets = new HashMap();
//			for(String u: adj.keySet()) {
//				Set<String> visited = new HashSet();
//				bfs(adj,visited, u, 3);
//				sets.put(u,visited);
//			}
//			
//			
//			for(String u: adj.keySet()) {
//				for(String v: adj.keySet()) {
//					Set<String> s1 = sets.get(u);
//					Set<String> s2= sets.get(v);
//					Set<String> intersection = new HashSet<String>(s1); // use the copy constructor
//					intersection.retainAll(s2);
//					if(intersection.isEmpty()) {
//						res.add(u);
//						res.add(v);
//					}
//				}
//			}
			
			
			if(res.size()*20 > nodeCount) {
				System.out.println("NO");
			} else {
				System.out.println("YES");
			}
		}
	}
	
	private static void bfs(List<Integer>[] adj,
			boolean[] visited,
			int start, int limit) {
		int lvl = 0;
		Queue<Integer> q = new ArrayDeque();
		Queue<Integer> next;
		q.add(start);
		while(!q.isEmpty() && lvl < limit) {
			next = new LinkedList();
			for(Integer u: q) {
				List<Integer> li = adj[u];
				for(Integer v: li) {
					if(!visited[v]) {
						visited[v] =true;
						next.add(v);
					}
				}
			}
			q = next;
			lvl++;
		}
	}

	private static void add(List<Integer>[] adj,
			String u, String v, Map<String, Integer> id) {
		int ui = 0, vi = 0;
		if(!id.containsKey(u)) {
			ui = nodeCount;
			id.put(u, nodeCount++);
		}
		if(!id.containsKey(u)) {
			vi = nodeCount;
			id.put(v, nodeCount++);
		}
		adj[ui].add(vi);
		adj[vi].add(ui);
		
	}
}
