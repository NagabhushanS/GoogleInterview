package ComputationalGeometry;

public class TCPointInPolygon {

	public String testPoint(String[] vertices, int x, int y) {
		int n = vertices.length;
		int[][] points = new int[n][2];
		for(int i=0; i<n; i++) {
			String[] xy = vertices[i].split("\\s+");
			points[i] = new int[] {Integer.parseInt(xy[0]), Integer.parseInt(xy[1])};
//			System.out.println(points[i][0]+" "+points[i][1]);
		}
		long area=0;
		for(int i=1; i<n-1; i++) {
			int[] u = {points[i][0]-points[0][0], points[i][1]-points[0][1]};
			int[] v = {points[i+1][0]-points[i][0], points[i+1][1]-points[i][1]};
			area += cross(u, v)/2;
		}
		area = Math.abs(area);
		
		long result = 0;
		
		for(int i=0; i<n; i++) {
			int[] u = {x-points[i][0], y-points[i][1]};
			int[] v = null;
			if (i+1<n)
				v = new int[] {points[i+1][0]-points[i][0], points[i+1][1]-points[i][1]};
			else
				v = new int[] {points[0][0]-points[i][0], points[0][1]-points[i][1]};
//			System.out.println(u[0]+" "+u[1]+",  "+v[0]+" "+v[1]);
			long cp = cross(u, v);
			if (cp==0) {
				int xMax = Math.max(points[i][0], points[i+1<n?i+1:0][0]);
				int xMin = Math.min(points[i][0], points[i+1<n?i+1:0][0]);
				int yMax = Math.max(points[i][1], points[i+1<n?i+1:0][1]);
				int yMin = Math.min(points[i][1], points[i+1<n?i+1:0][1]);
				if (x<=xMax && x>=xMin && y<=yMax && y>=yMin)
					return "BOUNDARY";
			}
			result += cp/2;
		}
//		result = Math.abs(result);
		System.out.println(result+" "+area);
		if (result!=area) {
			return "EXTERIOR";
		} else {
			return "INTERIOR";
		}
	}
	
	private long cross(int[] u, int[] v) {
		return u[0]*v[1]-v[0]*u[1];
	}

	public static void main(String[] args) {
		String[] v = 	
				
			{"0 0","0 1000","1000 1000","1000 800",
			 "200 800","200 600","600 600","600 400",
			 "200 400","200 200","1000 200","1000 0"};
		int x = 1000;
		int y = 5000;
		TCPointInPolygon sol = new TCPointInPolygon();
		System.out.println(sol.testPoint(v, x, y));
	}

}
