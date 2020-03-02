import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class RobotProtection {
	static Scanner reader = new Scanner(System.in);
	
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
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
			Point other = (Point) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
		
	}
	
	static class PComp implements Comparator<Point> {
		Point p0;
		
		public PComp(Point p0) {
			super();
			this.p0 = p0;
		}

		@Override
		public int compare(Point a1, Point a2) {
			int o = orientation(p0, a1, a2);
			if( o == 0) {
				return distSqr(p0, a1) - distSqr(p0, a2);
			}
			return o;
		}
		
		private Double polar(Point p) {
			double dx = p0.x-p.x;
			double dy = p0.y-p.y;
			return Math.atan2(dy,dx);
		}
	}
	
	static int distSqr(Point p1, Point p2) {
		return (p1.x-p2.x)*(p1.x-p2.x)+
				(p1.y-p2.y)*(p1.y-p2.y);
	}
	
	// 0  = colinear
	// 1  = clockwise 
	// -1 = counter-clockwise
	public static int orientation(Point p1, Point p2,
			Point p3) {
		int val = (p2.y - p1.y)*(p3.x-p2.x)-
				(p3.y-p2.y)*(p2.x-p1.x);
		if(val == 0) return 0; 	// collinear
		return val > 0? 1:-1;
	}
	
	public static Stack<Point> convexHull(List<Point> points){
		// find P0, point with minimum y coordinate
		Point p0 = points.get(0);
		for(Point p: points) {
			if(p.y < p0.y) {
				p0 = p;
			} else if (p.y == p0.y && p.x < p0.x) {
				p0 = p;
			}
		}
		// remove p0
		points.remove(p0);
		// sort all the remaining points in order of their polar angle from p0
		Collections.sort(points, new PComp(p0));
		int i = 0;
		Stack<Point> stack = new Stack();
		stack.push(p0);
		if(points.size() < 1) {
			return stack;
		}
		stack.push(points.get(0));
		if(points.size() < 2) {
			return stack;
		}
		stack.push(points.get(1));
		for(i=2;i<points.size();i++) {
			Point top = stack.pop();
			Point topNext = stack.pop();
			Point p = points.get(i);
			while(orientation(topNext, top, p)>=0) {
				top = topNext;
				topNext = stack.pop();
			}
			stack.push(topNext);
			stack.push(top);
			stack.push(p);
		}
		return stack;
	}
	
	public static double area(List<Point> points) {
		double area = 0.0;
		for(int i = 0; i < points.size(); i++) {
			int j = (i+1) % points.size();
			area += points.get(i).x*points.get(j).y;
			area -= points.get(i).y*points.get(j).x;
		}
		return Math.abs(area)/2;
	}
	public static void main(String[] args) {
		int n = reader.nextInt();
		while(n!=0) {
			ArrayList<Point> points = new ArrayList<>();
			for(int i = 0 ; i < n ; i ++) {
				int x = reader.nextInt();
				int y = reader.nextInt();
				points.add(new Point(x,y));
			}
			Stack<Point> ch = convexHull(points);
			List<Point> ps = new ArrayList();
			while(!ch.isEmpty()) {
				ps.add(ch.pop());
			}
			System.out.println(area(ps));
			n = reader.nextInt();
		}
	}
	
	
}
