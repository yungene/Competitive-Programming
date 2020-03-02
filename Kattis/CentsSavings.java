import java.util.Scanner;

public class CentsSavings {
	static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) {
		int N = reader.nextInt();
		int D = reader.nextInt();

		int[] nums = new int[N];
		for (int n = 0; n < N; n++) {
			nums[n] = reader.nextInt();
		}

		int res = 0;
		int partial = 0;

		int[][] sub = new int[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				int sum = 0;
				for (int k = i; k < j; k++) {
					sum += nums[k];
				}
				sub[i][j] = f(sum);
			}
		}

		int[][] dp = new int[N + 1][D + 1];
		for (int i = 0; i <= N; i++) {
			for (int d = 0; d <= D; d++) {
				dp[i][d] = Integer.MAX_VALUE/3;
			}
		}
		
		for (int i = 0; i <= N; i++) {
			dp[i][0] = sub[0][i];
		}

		for (int i = 0; i <= N; i++) {
			for (int d = 1; d <= D; d++) {
				for (int j = 0; j < i; j++) {
					dp[i][d] = Math.min(dp[i][d], dp[j][d-1] + sub[j][i]);
				}
			}
		}

		int min = Integer.MAX_VALUE;
		for (int d = 0; d <= D; d++) {
			min = Math.min(min, dp[N][d]);
		}
		res += min;

		System.out.println(res);
	}

	static int f(int a) {
		if (a % 10 < 5) {
			return (a / 10) * 10;
		} else {
			return (a / 10 + 1) * 10;
		}
	}

}
