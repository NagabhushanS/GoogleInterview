package DP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class P1092F2 {

	private static int n;
	private static int[] a;
	private static HashMap<Integer, ArrayList<Integer>> g;
	private static long[] sum;
	private static long[] cost;
	private static long ans=0;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		a = new int[n];
		sum = new long[n];
		cost = new long[n];
		for(int i=0; i<n; i++) {
			a[i] = in.nextInt();
		}
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
		
		dfs(0, -1);
		go(0, -1);
		System.out.println(ans);

	}
	
	public static void dfs(int u, int p) {
		
		sum[u] = a[u];
		for(int v: g.get(u)) {
			if (v==p) continue;
			
			dfs(v, u);
			sum[u] += sum[v];
			cost[u] += cost[v]+sum[v];
		}
	}
	
	public static void go(int u, int p) {
		ans	= Math.max(ans, cost[u]);
		
		for(int v: g.get(u)) {
			if (v==p) continue;
			sum[u]-=sum[v];
			cost[u]-=cost[v]+sum[v];
			sum[v]+=sum[u];
			cost[v]+=cost[u]+sum[u];
			go(v, u);
			cost[v]-=cost[u]+sum[u];
			sum[v]-=sum[u];
			cost[u]+=cost[v]+sum[v];
			sum[u]+=sum[v];
		}
	}
	
		

}
