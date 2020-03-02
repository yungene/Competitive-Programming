import java.util.Scanner;

// Problem ID: teacherevaluation
public class TeacherEvaluation {
	static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) {
		int N = reader.nextInt(); // class size
		int P = reader.nextInt(); // goal score
		int[] scores = new int[N];
		int sum = 0;
		for(int i =0 ; i < N; i++) {
			int score = reader.nextInt();
			scores[i] = score;
			sum += score;
		}
		
		int res = 0;
		
		int target = P * N;
		while(target != sum) {
			
			target += P;
			int delta =  Math.min(100, target-sum);
			if(P >= delta) {
				System.out.println("impossible");
				return;
			}
			sum += delta;
			res++;
		}
		
		System.out.println(res);
	}

}
