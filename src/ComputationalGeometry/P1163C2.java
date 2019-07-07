package ComputationalGeometry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Scanner;

public class P1163C2 {

	private static class Line {
		private int a, b, c;
		private boolean parallelDetection;
		
		public Line(int x1, int y1, int x2, int y2) {
			a = y2-y1;
			b = x1-x2;
			c = a*x1+b*y1;
			int g=gcd(a, b);
			a/=g;
			b/=g;
			c/=g;
//			if (a<0) {
//				a*=-1;
//				b*=-1;
//				c*=-1;
//			} else if (a==0) {
//				if (b<0) {
//					b*=-1;
//					c*=-1;
//				}
//			}
			parallelDetection = false;
		}
		
		@Override
		public int hashCode() {
			if (!parallelDetection)
				return 31+31*a+31*31*b+31*31*31*c;
			else {
				return 31+31*a+31*31*b;
			}
		}
		
		@Override
		public boolean equals(Object o) {
			Line line = (Line)o;
			if (!parallelDetection)
				return a==line.a && b==line.b && c==line.c;
			else {
				return a==line.a && b==line.b;
			}
		}
	}
	
	private static int n;
	private static int[][] points;
	private static HashSet<Line> lines;
	
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		points = new int[n][2];
		lines = new HashSet<Line>();
		for(int i=0; i<n; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			points[i] = new int[] {x, y};
			
		}
		
		for(int i=0; i<n; i++) {
			for(int j=i+1; j<n; j++) {
				Line line = new Line(points[i][0], points[i][1], points[j][0], points[j][1]);
				lines.add(line);
			}
		}
//		System.out.println(lines.size());
		HashMap<Line, Integer> map = new HashMap<Line, Integer>();
		for(Line line: lines) {
			line.parallelDetection=true;
			map.put(line, map.getOrDefault(line, 0)+1);
		}
		
		long ans=lines.size()*1L*(lines.size()-1)/2;
		
		for(Entry<Line, Integer> e: map.entrySet()) {
			if (e.getValue()==1) continue;
			else ans-=e.getValue()*1L*(e.getValue()-1)/2;
		}
		System.out.println(ans);
	}
	
	private static int gcd(int a, int b) {
		
		if (b==0) return a;
		else return gcd(b, a%b);
	}

}
