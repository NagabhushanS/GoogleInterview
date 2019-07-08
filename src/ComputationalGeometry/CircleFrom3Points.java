package ComputationalGeometry;

public class CircleFrom3Points {

	private double x1, y1;
	private double x2, y2;
	private double x3, y3;
	
	public CircleFrom3Points(double a, double b, double c, double d, double e, double f) {
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
		
		double alpha1 = y1+y2-line1.b/line1.a*(x1+x2);
		GeneralLine bisect1 = new GeneralLine(2*line1.b, -2*line1.a, -1*line1.a*alpha1);
		double alpha2 = y2+y3-line2.b/line2.a*(x2+x3);
		GeneralLine bisect2 = new GeneralLine(2*line2.b, -2*line2.a, -1*line2.a*alpha2);
		
		return bisect1.intersectionPoint(bisect2);
	}
	
	public static void main(String[] args) {
		
		
	}

}
