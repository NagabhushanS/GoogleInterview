package ComputationalGeometry;

class VectorOperations {
	
	public static int dot(int[] A, int[] B, int[] C) {
		//AB.BC
		int[] AB = new int[] {B[0]-A[0], B[1]-A[1]};
		int[] BC = new int[] {C[0]-B[0], C[1]-B[1]};
		return AB[0]*BC[0]+AB[1]*BC[1];
	}
	
	public static int dot(int[] u, int[] v) {
		return u[0]*v[0]+u[1]*v[1];
	}
	
	public static int cross(int[] u, int[] v) {
		return Math.abs(u[0]*v[1]-v[0]*u[1]);
	}
	
	public static double distance(int[] a, int[] b) {
		return Math.sqrt((a[0]-b[0])*(a[0]-b[0])+(a[1]-b[1])*(a[1]-b[1]));
	}
	
	public static double shortestDistanceLine(int[] a, int[] b, int[] c) {
		//return shortest distance from c to the line ab
		int[] ab = {b[0]-a[0], b[1]-a[1]};
		int[] ac = {c[0]-a[0], c[1]-a[1]};
		double abCrossac = Math.abs(ab[0]*ac[1]-ac[0]*ab[1]);
		double abMod = Math.sqrt(ab[0]*ab[0]+ab[1]*ab[1]);
		return abCrossac/abMod;
	}
	
	public static double shortestDistanceLineSegment(int[] a, int[] b, int[] c) {
		//return shortest distance from c to the line segment ab
		//ab, ba, ac, bc
		int[] ab = {b[0]-a[0], b[1]-a[1]};
		int[] ba = {a[0]-b[0], a[1]-b[1]};
		int[] ac = {c[0]-a[0], c[1]-a[1]};
		int[] bc = {c[0]-b[0], c[1]-b[1]};
		if (dot(ab, bc)>0) {
			return distance(b, c);
		} else if (dot(ba, ac)>0) {
			return distance(a, c);
		} else {
			return shortestDistanceLine(a, b, c);
		}
	}
}

public class VectorProducts {

	public static void main(String[] args) {

	}

}
