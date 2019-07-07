package ComputationalGeometry;

import ComputationalGeometry.VectorOperations;

class Polygon {
	public static double area(int[][] points) {
		//point -- new int[2]
		if (points==null || points.length<3) {
			throw new IllegalArgumentException();
		}
		int n = points.length;
		double area=0;
		for (int i=1; i<n-1; i++) {
			//0i X i(i+1)
			int[] u = new int[] {points[i][0]-points[0][0], points[i][1]-points[0][1]};
			int[] v = new int[] {points[i+1][0]-points[0][0], points[i+1][1]-points[0][1]};
			area += 0.5*VectorOperations.cross(u, v);	
		}
		return Math.abs(area);
	}
}

public class PolygonAreaTest {

	public static void main(String[] args) {
		int[][] points = {
				{0, 0},
				{2, 3},
				{0, 2},
				{2, 0}
		};
		System.out.println(Polygon.area(points));
	}

}
