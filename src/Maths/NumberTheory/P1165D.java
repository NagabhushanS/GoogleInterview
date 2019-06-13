package Maths.NumberTheory;

import java.util.Arrays;
import java.util.Scanner;

public class P1165D {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		
		while(t-->0) {
			int n = in.nextInt();
			int[] d = new int[n];
			long ans = -1;
			int min = (int)1e7;
			for(int i=0; i<n; i++) {
				d[i] = in.nextInt();
				if (ans==-1) ans=d[i];
				ans = lcm(ans, d[i]);
				min = Math.min(min, d[i]);
			}
			
			for(int i=0; i<n; i++) {
				if (ans==d[i]) {
					ans*=min;
				}
			}
			
			System.out.println(ans);	
			
			
			
			
		}

	}
	
	public static long lcm(long a, long b) {
		return a*b/gcd(a, b);
	}
	
	public static long gcd(long a, long b) {
		
		if (a<b) return gcd(b, a);
		
		if (b==0) {
			return a;
		}
		
		return gcd(b, a%b);
	}

}
