package DP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class P1187E {

	private static int n;
	private static HashMap<Integer, ArrayList<Integer>> g;
	private static int[] size;
	private static long[] dp;
	private static long ans=0;
	
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
		size = new int[n];
		dp = new long[n];
		dfs(0, -1);
		go(0, -1);
		System.out.println(ans);
	}
	
	private static void dfs(int u, int p) {
		
		size[u]=1;
		for(int v: g.get(u)) {
			if (v==p) continue;
			dfs(v, u);
			size[u]+=size[v];
			dp[u]+=dp[v];
		}
		dp[u] += size[u];
	}
	
	private static void go(int u, int p) {
		//u at the root
		ans = Math.max(ans, dp[u]);
		
		for(int v: g.get(u)) {
			if (v==p) continue;
			size[u]-=size[v];
			dp[u]-=size[v];
			dp[u]-=dp[v];
			size[v]+=size[u];
			dp[v]+=(dp[u]+size[u]);
			go(v, u);
			dp[v]-=(dp[u]+size[u]);
			size[v]-=size[u];
			dp[u]+=dp[v];
			dp[u]+=size[v];
			size[u]+=size[v];
		}
	}


}
