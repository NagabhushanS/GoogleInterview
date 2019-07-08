package ComputationalGeometry;

public class GeneralLine {

	protected double a;
	protected double b;
	protected double c;
	
	public GeneralLine(double x1, double y1, double x2, double y2) {
		a = y2-y1;
		b = x1-x2;
		c = a*x1+b*y1;
	}
	
	public GeneralLine(double A, double B, double C) {
		a=A;
		b=B;
		c=C;
	}

	@Override
	public int hashCode() {
		int A = Double.valueOf(a).hashCode();
		int B = Double.valueOf(b).hashCode();
		int C = Double.valueOf(c).hashCode();
		return 31+31*A+31*31*B+31*31*31*C;
	}
	
	@Override
	public boolean equals(Object o) {
		GeneralLine line = (GeneralLine)o;
		return line.a==a && line.b==b && line.c==c;
	}
	
	public double[] intersectionPoint(GeneralLine line) {
		//if two lines, return null
//		if (a==line.a && b==line.b) return null;
		
		double det = a*line.b-line.a*b;
		if (det==0) return null;
		double x = c*line.b-line.c*b;
		double y = line.c*a-c*line.a;
		return new double[] {x/det, y/det};
	}
	
	public double det(GeneralLine line) {
		double det = a*line.b-line.a*b;
		return det;
	}
	

}
