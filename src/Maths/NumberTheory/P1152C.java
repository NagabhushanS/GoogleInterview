package Maths.NumberTheory;

import java.util.Scanner;

public class P1152C {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		int b = in.nextInt();
		if (a<b) {
			solve(a, b);
		} else {
			solve(b, a);
		}

	}
	
	public static void solve(long a, long b) {
		long ans=(long)1e15;
		long kMax=0;
		for(int i=1; i*i<=(b-a); i++) {
			
			if ((b-a)%i==0) {
				long k=0;
				if (a%i==0) {
					k=0;
				} else {
					k = (a/i+1)*i-a;
				}
				long val = (a+k)*(b+k)/gcd(a+k, b+k);
				if (val<ans) {
					if (val==ans) {
						kMax = Math.min(k, kMax);
					} else {
						kMax = k;
					}
					ans = val;
				}
			}
			int temp = i;
			i = (int)(b-a)/i;
			if ((b-a)%i==0) {
				long k=0;
				if (a%i==0) {
					k=0;
				} else {
					k = (a/i+1)*i-a;
				}
				long val = (a+k)*(b+k)/gcd(a+k, b+k);
				if (val<ans) {
					if (val==ans) {
						kMax = Math.min(k, kMax);
					} else {
						kMax = k;
					}
					ans = val;
				}
			}
			i = temp;
			
		}
		System.out.println(kMax);
	}
	
	public static long gcd(long a, long b) {
		
		if (a<b) return gcd(b, a);
		
		if (b==0) {
			return a;
		}
		
		return gcd(b, a%b);
	}


}
