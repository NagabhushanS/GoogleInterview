package DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class P1172B {

	static int n;
	static HashMap<Integer, ArrayList<Integer>> g;
	static final int MOD = 998244353;
	static long[] dp1 = new long[200001];
	static long[] dp2 = new long[200001];
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		g = new HashMap<>();
		for(int i=0; i<n; i++) {
			g.put(i, new ArrayList<>());
		}
		for(int i=0; i<n-1; i++) {
			int p = in.nextInt()-1;
			int q = in.nextInt()-1;
			g.get(p).add(q);
			g.get(q).add(p);
		}
		Arrays.fill(dp1, -1);
		Arrays.fill(dp2, -1);
		
		System.out.println((n*solve(0, -1))%MOD);

	}
	
	public static long solve(int u, int p) {
		
		if (dp1[u]!=-1) {
			return dp1[u];
		}
		
		long ans=1;
		ans = (ans*fact(g.get(u).size()))%MOD;
		
		for(int v: g.get(u)) {
			if (v==p) continue;
			ans = (ans*solve(v, u))%MOD;
			
		}
		
		return dp1[u]=ans;
	}
	
	
	public static long fact(int i) {
		if (i==1) {
			return 1;
		}
		if (dp2[i]!=-1) return dp2[i];
		
		return dp2[i]=(fact(i-1)*i)%MOD;
	}

}
