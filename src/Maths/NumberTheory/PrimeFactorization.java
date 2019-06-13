package Maths.NumberTheory;

import java.util.Arrays;

public class PrimeFactorization {

	public static void main(String[] args) {
//		factorization(210);
		goldbach(10);
	}
	
	public static void goldbach(int n) {
		long ans=0;
		boolean[] isPrime = soe(n);	
		for(int i=2; i<=n/2; i++) {
			if (isPrime[i] && isPrime[n-i]) {
				ans++;
			}
		}
		System.out.println(ans);
	}

	public static void factorization(int number) {
		
		for(int i=2; i<=Math.sqrt(number); i++) {
			while(number%i==0) {
				System.out.print(i+" ");
				number/=i;
			}
		}
		if (number>1) {
			System.out.println(number);
		}
		
		
	}
	public static boolean[] soe(int n) {
		boolean[] isPrime = new boolean[n+1];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		int sqrt = (int)Math.sqrt(n);
		for(int i=2; i<=sqrt; i++) {
			if (isPrime[i]) {
				for(int j=i*i; j<=n; j+=i) {
					isPrime[j]=false;
				}
			}
		}
		return isPrime;
	}
}
