import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Xentopia {
	static Scanner reader = new Scanner(System.in);

	static class Edge {
		int u, v, w, c;

		boolean isRed() {
			return c == 1;
		}

		boolean isBlue() {
			return c == 2;
		}

		public Edge(int u, int v, int w, int c) {
			super();
			this.u = u;
			this.v = v;
			this.w = w;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Edge [u=" + u + ", v=" + v + ", w=" + w + ", c=" + c + "]";
		}
		
	}

	static class AdjPtr {
		int v, r, b, w;

		public AdjPtr(int v, int r, int b, int w) {
			super();
			this.v = v;
			this.r = r;
			this.b = b;
			this.w = w;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + b;
			result = prime * result + r;
			result = prime * result + v;
			result = prime * result + w;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			AdjPtr other = (AdjPtr) obj;
			if (b != other.b)
				return false;
			if (r != other.r)
				return false;
			if (v != other.v)
				return false;
			if (w != other.w)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "AdjPtr [v=" + v + ", r=" + r + ", b=" + b + ", w=" + w + "]";
		}

	}

	static class Vertex {
		int u, r, b;

		public Vertex(int u, int r, int b) {
			super();
			this.u = u;
			this.r = r;
			this.b = b;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + b;
			result = prime * result + r;
			result = prime * result + u;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Vertex other = (Vertex) obj;
			if (b != other.b)
				return false;
			if (r != other.r)
				return false;
			if (u != other.u)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Vertex [u=" + u + ", r=" + r + ", b=" + b + "]";
		}

	}
	
	static class V {
		Vertex v;
		long id;
		long d;
		public V(Vertex v, long id, long d) {
			super();
			this.v = v;
			this.id = id;
			this.d = d;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (d ^ (d >>> 32));
			result = prime * result + (int) (id ^ (id >>> 32));
			result = prime * result + ((v == null) ? 0 : v.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			V other = (V) obj;
			if (d != other.d)
				return false;
			if (id != other.id)
				return false;
			if (v == null) {
				if (other.v != null)
					return false;
			} else if (!v.equals(other.v))
				return false;
			return true;
		}


	}

	static class MyComp2 implements Comparator<V> {

		@Override
		public int compare(V o1, V o2) {
			return Long.compare(o1.d, o2.d);
		}

	}
	
	static class MyComp implements Comparator<Vertex> {

		@Override
		public int compare(Vertex o1, Vertex o2) {
			return Long.compare(dist.get(o1), dist.get(o2));
		}

	}

	static Map<Vertex, Long> dist;
	static ArrayList<AdjPtr>[][][] adj;
	static long id = 0;

	public static void main(String[] args) {

		int V = reader.nextInt(); // N
		int E = reader.nextInt(); // M
		int R = reader.nextInt(); // k1
		int B = reader.nextInt(); // k2
		adj = new ArrayList[V][R + 1][B + 1];
		for (int i = 0; i < V; i++) {
			for (int r = 0; r <= R; r++) {
				for (int b = 0; b <= B; b++) {
					adj[i][r][b] = new ArrayList<>();
				}
			}
		}

		Edge[] edges = new Edge[2*E];
		for (int i = 0; i < E; i++) {
			// get the edge
			int u = reader.nextInt() - 1;
			int v = reader.nextInt() - 1;
			int w = reader.nextInt();
			int c = reader.nextInt();
			edges[i] = new Edge(u, v, w, c);
			edges[E+i] = new Edge(v, u, w, c);
		}
		int S = reader.nextInt() - 1;
		int T = reader.nextInt() - 1;

		for (int i = 0; i < 2*E; i++) {
			Edge e = edges[i];
			for (int r = 0; r <= R; r++) {
				for (int b = 0; b <= B; b++) {
					if (e.isBlue() && b + 1 <= B) {
						adj[e.u][r][b].add(new AdjPtr(e.v, r, b + 1, e.w));
					} else if (e.isRed() && r + 1 <= R) {
						adj[e.u][r][b].add(new AdjPtr(e.v, r + 1, b, e.w));
					} else if (!e.isBlue() && !e.isRed()) {
						adj[e.u][r][b].add(new AdjPtr(e.v, r, b, e.w));
					}
				}

			}
		}

		// runa djikstra over the new graph from [s][0][0] to [t][R][B]

		PriorityQueue<V> pq = new PriorityQueue(new MyComp2());
		dist = new HashMap();
		for (int i = 0; i < V; i++) {
			for (int r = 0; r <= R; r++) {
				for (int b = 0; b <= B; b++) {
					Vertex v = new Vertex(i, r, b);
					dist.put(v, Long.MAX_VALUE);
					//pq.add(new V(v,id++));
				}
			}
		}
		//pq.remove(new V(new Vertex(S, 0, 0),id));
		dist.put(new Vertex(S, 0, 0), (long) 0);
		pq.add(new V(new Vertex(S, 0, 0),id++,0));
		Set<Vertex> visited = new HashSet();
		Vertex goal = new Vertex(T, R, B);
		while (!pq.isEmpty()) {
			V vu = pq.poll();
			Vertex u = vu.v;
			if (visited.contains(u) || u.r > R || u.b > B) {
				continue;
			}
			visited.add(u);
			if (u.equals(goal)) {
				System.out.println(dist.get(goal));
				return;
			}
			ArrayList<AdjPtr> li = adj[u.u][u.r][u.b];
			long d = dist.get(u);
			if(d == Long.MAX_VALUE) {
				break;
			}
			for (AdjPtr e : li) {
				// relax the edje
				Vertex v = new Vertex(e.v, e.r, e.b);
				if (!visited.contains(v) && d + e.w < dist.get(v)) {
					//pq.remove(v);
					dist.put(v, d + e.w);
					pq.add(new V(v,id++,d + e.w));
				}
			}
		}
		System.out.println(-1);
//		if (!visited.contains(goal) || dist.get(goal) == Integer.MAX_VALUE) {
//			System.out.println(-1);
//		} else {
//			System.out.println(dist.get(goal));
//		}
	}

}
