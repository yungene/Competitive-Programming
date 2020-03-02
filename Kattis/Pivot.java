import java.util.Arrays;
import java.util.Scanner;
//Problem ID: pivot
public class Pivot {
	static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) {

		int n = reader.nextInt();
		int arr[] = new int[n];
		int sorted[] = new int[n];
		for (int i = 0; i < n; i++) {
			int el = reader.nextInt();
			arr[i] = el;
			sorted[i] = el;
		}
		int[] minr = new int[n + 1];
		int[] maxl = new int[n + 1];
		maxl[0] = Integer.MIN_VALUE;
		for (int i = 1; i <= n; i++) {
			int el = arr[i - 1];
			maxl[i] = Math.max(maxl[i - 1], el);
		}
		minr[n-1] = Integer.MAX_VALUE;
		for (int i = n - 2; i >= 0; i--) {
			int el = arr[i + 1];
			minr[i] = Math.min(minr[i + 1], el);
		}
		Arrays.sort(sorted);
		int c = 0;
		for (int i = 0; i < n; i++) {
			int el = arr[i];
			if (arr[i] == sorted[i]) {
				// if el > max of the left
				// anf if el < min of the right
				if (el >= maxl[i] && el <= minr[i]) {
					c++;
				}
			}
		}
		System.out.println(c);
	}

}
