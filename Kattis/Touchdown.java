import java.util.Scanner;

public class Touchdown {
	static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) {
		int N = reader.nextInt();
		int counter = 0;
		int advance = 0;
		int limit = 4;
		int downc = 0;
		int pos = 20;
		int i;
		for (i = 0; i < Math.min(N, limit); i++) {
			int play = reader.nextInt();
			advance += play;
			if (advance >= 10) {
				advance = 0;
				limit = i+4+1;
				downc += 1;
			}
			pos += play;
			if (pos <= 0) {
				System.out.println("Safety");
				return;
			} else if (pos >= 100) {
				System.out.println("Touchdown");
				return;
			}
		}
		System.out.println("Nothing");

	}
}
