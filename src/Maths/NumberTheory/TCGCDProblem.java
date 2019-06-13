package Maths.NumberTheory;

import java.util.HashMap;

public class TCGCDProblem {
	static class P{
		long a, b;
		
		public P(long A, long B) {
			a=A;
			b=B;
		}
		
		public int hashCode() {
			return (int)((a+b)%1e9);
		}
		
		public boolean equals(Object o) {
			P p = (P)o;
			return p.a==a && p.b==b;
		}
	}
	static HashMap<P, Integer> hash;
	static int count=0;
	
	public static void main(String[] args) {
		hash = new HashMap<>();
		System.out.println(solve(1916006400));
		System.out.println(count);
	}
	
	public static int solve(long n) {
		return solve(n, 2);
	}
	
	public static int solve(long n, int m) {
		if (hash.containsKey(new P(n, m))) {
			count+=1;
			return hash.get(new P(n, m));
		}
		
		int ans=0;
		for(int i=m; i*i<=n; i++) {
			if (n%i==0) {
				ans+=solve(n/i, i)+1;
			}
		}
		hash.put(new P(n, m), ans);
		return ans;
	}

}
