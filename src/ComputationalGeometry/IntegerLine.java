package ComputationalGeometry;

public class IntegerLine {
	//Line class for a line passing through aleast two integer cord points
	private int a;
	private int b;
	private int c;
	
	public IntegerLine(int x1, int y1, int x2, int y2) {
		a = y2-y1;
		b = x1-x2;
		c = a*x1+b*y1;
		//a=1, b=-2, c=2 is same as a=-1,b=2,c=-2
		int g = gcd(a, b);
		a/=g;
		b/=g;
		c/=g;
	}
	
	private int gcd(int a, int b) {
		if (b==0) return a;
		else return gcd(b, a%b);
	}

	@Override
	public int hashCode() {
		return 31+31*a+31*31*b+31*31*31*c;
	}
	
	@Override
	public boolean equals(Object o) {
		IntegerLine line = (IntegerLine)o;
		return line.a==a && line.b==b && line.c==c;
	}

}
