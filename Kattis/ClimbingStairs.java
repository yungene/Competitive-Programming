import java.util.Scanner;

public class ClimbingStairs {
	static Scanner reader = new Scanner(System.in);
	public static void main(String[] args) {
		int n = reader.nextInt();
		int r = reader.nextInt();
		int k = reader.nextInt();
		int n2 = n;
		if(n%2 == 1) {
			n2 = n + 1;
		}
		int res = 0;
		if(r > k){
			if(n > r) {
				res = r+n;
			} else {
				res = 2*r;
			}
		} else {
			if( n > 2*k-r) {
				res = r+n;
			} else {
				res = 2*k;
			}
		}
		if(res % 2 == 1) {
			res += 1;
			
		}
		System.out.println(res);

	}

}
//
//20 10 5
//30
//
//20 0 5
//20
//
//20 20 0
//40
//
//20 20 20
//40
//
//20 10 10
//30
//
//20 10 30
//60
//
//1 0 0
//2
//
//3 2 2
//6
//
//7 3 5
//10
//
//7 4 5
//12
//
//7 2 5
//10
//
//7 5 5
//5+2+5=12
