package ComputationalGeometry;

public class TCTVTower {
	private class C3P {
		private double x1, y1;
		private double x2, y2;
		private double x3, y3;
		
		public C3P(double a, double b, double c, double d, double e, double f) {
			x1=a;
			y1=b;
			x2=c;
			y2=d;
			x3=e;
			y3=f;
		}
		
		public double[] getCenter() {
			//return null is all three points are collinear
			GeneralLine line1 = new GeneralLine(x1, y1, x2, y2);
			GeneralLine line2 = new GeneralLine(x2, y2, x3, y3);
			double det = line1.det(line2);
			if (det==0) return null;
			
			GeneralLine bisect1 = new GeneralLine(-1*line1.b, line1.a, -x1*line1.b+y1*line1.a);
			GeneralLine bisect2 = new GeneralLine(-1*line2.b, line2.a, -x2*line2.b+y2*line2.a);
			
			return bisect1.intersectionPoint(bisect2);
		}
		
	}
	
	public double minRadius(int[] x, int[] y) {
		
		int n = x.length;
		if (n==1) return 0;
		
		double ans = 1e15;
		System.out.println(ans);
		
		for(int i=0; i<n; i++) {
			for(int j=i+1; j<n; j++) {
				//points i and j are the end point of diameter of the circle
				double[] center = new double[] {(double)(x[i]+x[j])/2, (double)(y[i]+y[j])/2};
				double radius = (x[i]-x[j])*(x[i]-x[j])+(y[i]-y[j])*(y[i]-y[j]);
				boolean c = check(x, y, center, radius/4);
				System.out.println(radius+" "+c+" "+x[i]+" "+y[i]+" "+x[j]+" "+y[j]);
				if (c)
					ans = Math.min(ans, radius/4);
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=i+1; j<n; j++) {
				for(int k=j+1; k<n; k++) {
					C3P cp = new C3P(x[i], y[i], x[j], y[j], x[k], y[k]);
					double center[] = cp.getCenter();
					if (center==null) continue;
					//if (Double.valueOf(center[0]).compareTo(Double.NaN)==0) continue;
					double radius = (x[i]-center[0])*(x[i]-center[0])+(y[i]-center[1])*(y[i]-center[1]);
					System.out.println(radius+" "+x[i]+" "+y[i]+" "+x[j]+" "+y[j]+" "+x[k]+" "+y[k]+" "+center[0]+" "+center[1]);
					if (check(x, y, center, radius)) {
						ans = Math.min(ans, radius);
					}
				}
			}
		}
		
		return Math.sqrt(ans);
	}
	
	private boolean check(int[] x, int[] y, double[] center, double radius) {
		
		int n = x.length;
		for(int i=0; i<n; i++) {
			double dist = (x[i]-center[0])*(x[i]-center[0])+(y[i]-center[1])*(y[i]-center[1]);
			if (dist>radius) {
				return false;
			}
		}
		
		return true;
		
	}

	public static void main(String[] args) {
		int[] x =	
			{933, 743, 262, 529, 700, 508, 752, 256, 256, 119, 711, 351, 843, 705, 108, 393, 330, 366, 169, 932, 917, 847, 972, 868, 980, 223, 549, 592, 164, 169, 551, 427, 190, 624, 635, 920, 944, 310, 862, 484, 363, 301, 710, 236, 876, 431, 929, 397, 675, 491};
		int[] y = {190, 344, 134, 425, 629, 30, 727, 126, 743, 334, 104, 760, 749, 620, 256, 932, 572, 613, 490, 509, 119, 405, 695, 49, 327, 719, 497, 824, 596, 649, 356, 184, 93, 245, 7, 306, 509, 754, 352, 665, 783, 738, 801, 690, 330, 337, 195, 656, 963, 11};
			 
		TCTVTower sol = new TCTVTower();
		System.out.println(sol.minRadius(x, y));
	}
	//517.1671916738641		
}
