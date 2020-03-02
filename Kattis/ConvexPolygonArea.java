import java.util.Scanner;

public class ConvexPolygonArea {
	static Scanner reader = new Scanner(System.in);

	static class Point{
		int x,y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	public static double area(Point[] points) {
		double area = 0.0;
		for(int i = 0; i < points.length; i++) {
			int j =( i + 1 )% points.length;
			area += points[i].x * points[j].y;
			area -= points[i].y * points[j].x;
		}
		return area/2;
	}
	
	public static void main(String[] args) {
		int T = reader.nextInt();
		for(int t= 0; t < T; t++) {
			int M = reader.nextInt();
			Point[] points = new Point[M];
			for(int m = 0; m < M; m++) {
				int x = reader.nextInt();
				int y = reader.nextInt();
				points[m] = new Point(x,y);
			}
			System.out.println(area(points));
		}
	}

}
