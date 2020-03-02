import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

// Optimality here means that she will minimize the number 
// of times an object is read into the cache.
public class Caching {
	static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) {
		int sz = reader.nextInt();
		int n = reader.nextInt();
		int a = reader.nextInt();
		int[] arr = new int[a];
		// list of accesses to cache
		for (int i = 0; i < a; i++) {
			int v = reader.nextInt();
			arr[i] = v;
		}
		Map<Integer, Integer> dist = new HashMap<>();
		for (int i = 0; i < n; i++) {
			dist.put(i, Integer.MAX_VALUE);
		}
		int[] da = new int[a];
		for (int i = a - 1; i >= 0; i--) {
			int v = arr[i];
			da[i] = dist.get(v);
			dist.put(v, i);
		}

		PriorityQueue<Line> pq = new PriorityQueue<>();
		Set<Integer> cached = new HashSet<>();
		int res = 0;
		int cz = 0;
		for (int i = 0; i < a; i++) {
			int v = arr[i];
			if (!cached.contains(v)) {
				if (cz >= sz) {
					Line o = null;
					while(true) {
						o = pq.poll();
						if(o.dist > i) {
							break;
						}
					}
					cached.remove(o.val);
					cz--;
				}
				res++;
				cached.add(v);
				cz++;
			} 
			pq.add(new Line(v, da[i]));
		}
		System.out.println(res);

	}

}

class Line implements Comparable<Line> {
	int val;
	int dist;

	public Line(int val, int dist) {
		super();
		this.val = val;
		this.dist = dist;
	}

	@Override
	public int compareTo(Line arg0) {
		return arg0.dist - this.dist;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + val;
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
		Line other = (Line) obj;
		if (val != other.val)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Line [val=" + val + ", dist=" + dist + "]";
	}

}
