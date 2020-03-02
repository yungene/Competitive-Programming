import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Judging {
	static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) {
		int n = reader.nextInt();
		Map<String, Integer> map = new HashMap();
		for(int i = 0 ;i  < n; i++) {
			String res = reader.next();
			if(!map.containsKey(res)) {
				map.put(res, 0);
			}
			map.put(res, map.get(res)+1);
		}
		
		int c = 0;
		for(int i = 0 ;i  < n; i++) {
			String res = reader.next();
			if(!map.containsKey(res)) {
				continue;
			} else {
				int prev=  map.get(res);
				if(prev==0) {
					map.remove(res);
					continue;
				}
				map.put(res, prev-1);
				c++;
			}
		}
		System.out.println(c);
	}

}
