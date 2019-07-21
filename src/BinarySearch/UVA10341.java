package BinarySearch;

import java.util.Scanner;

public class UVA10341 {

	private static int p, q, r, s, t, u;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			p = in.nextInt();
			q = in.nextInt();
			r = in.nextInt();
			s = in.nextInt();
			t = in.nextInt();
			u = in.nextInt();
			double low=0;
			double high=1;
			double mid = 0;
			
			while((high-low)>1e-9) {
				mid = (low+high)/2;
				double function = p*Math.exp(-mid)+q*Math.sin(mid)+r*Math.cos(mid)+s*Math.tan(mid)+t*mid*mid+u;
				
				if (function>0) {
					low=mid;
				} else {
					high=mid;
				}
				
			}
			mid = low;
			double function = p*Math.exp(-mid)+q*Math.sin(mid)+r*Math.cos(mid)+s*Math.tan(mid)+t*mid*mid+u;
			
			if (Math.abs(function)<1e-7)
				System.out.println(low);
			else
				System.out.println("No solution");
		}
	}

}
