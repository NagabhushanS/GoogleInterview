package Maths.NumberTheory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UVA10680 {

	private static int n;
	private static final int MAX = (int)1e6+1;
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		ArrayList<Integer> primes = new ArrayList<>();
		boolean[] isPrime = new boolean[MAX];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		
		for(int i=2; i<MAX; i++) {
			if (isPrime[i]) {
				primes.add(i);
				if (i*1L*i<MAX)
					for(int j=i*i; j<MAX; j+=i) {
						isPrime[j] = false;
					}
			}
		}
		
		while(in.hasNext()) {
			n = in.nextInt();
			if (n==0) break;
			
			int c2 = (int)Math.floor(Math.log10(n+1e-9)/Math.log10(2));
			int c5 = (int)Math.floor(Math.log10(n+1e-9)/Math.log10(5));
			
			
			long ans=1;
			
			assert(c2<c5);
			
			for(int i=0; i<c2-c5; i++) {
				ans = (ans*2)%10;
			}
			
			for(int i=0; i<primes.size(); i++) {
				int p = primes.get(i);
				if (p==2 || p==5) continue;
				if (p>n) break;
				int count = (int)Math.floor(Math.log10(n+1e-9)/Math.log10(p));
				for(int j=0; j<count; j++) {
					ans = (ans*p)%10;
				}
			}
			
			System.out.println(ans);
		}
		
	}

}
