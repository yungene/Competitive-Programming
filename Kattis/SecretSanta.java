import java.util.Scanner;

public class SecretSanta {
	static Scanner reader = new Scanner(System.in);
	public static void main(String[] args) {
		long n = reader.nextLong();
		double sum = 1;
		double fac = 1;
		double e1 = (1/Math.E);
		for(long i = 1; i <= n; i++) {
			fac *= i;
			if( i%2==0) {
				sum += 1/fac;
			} else {
				sum -= 1/fac;
			}
			//System.out.println(e1 - sum );
			if(Math.abs(e1 - sum) <= 0.000001) {
				//System.out.println(i);
				sum = e1;
				break;
			}
		}
		
		System.out.printf("%.7f\n", 1-sum);
	}

}
