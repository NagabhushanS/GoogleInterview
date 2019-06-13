package Maths.NumberTheory;

import java.util.Arrays;

public class EulersTotientFunction {

	public static void main(String[] args) {
		System.out.println(fi(616));
	}
	
	public static int fi(int n) {
		
		boolean[] isPrime = new boolean[n+1];
		
		Arrays.fill(isPrime, true);
		
		isPrime[0] = isPrime[1] = false;
		
		int totient = n;
		
		int sqrt = (int)Math.sqrt(n);
		for(int i=2; i*i<=n; i++) {
				if (n%i==0)
					totient = totient*(i-1)/i;
				while(n%i==0) {
					n/=i;
				}
		}
		if (n>1) {
			System.out.println("HI");
			totient = totient*(n-1)/n;
		}
		
		return totient;
	
	}

}
