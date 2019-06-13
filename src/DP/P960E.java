package DP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class P960E {

	static HashMap<Integer, ArrayList<Integer>> g;
	static long[] V;
	static long[] dp1;
	static long[] dp2;
	static long[] size;
	static long ans=0;
	static final long MOD = (long)1e9+7;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		g = new HashMap<>();
		for(int i=0; i<n; i++) {
			g.put(i, new ArrayList<>());
		}
		dp1 = new long[n];
		dp2 = new long[n];
		size = new long[n];
		V = new long[n];
		for(int i=0; i<n; i++) {
			V[i] = in.nextLong();
		}

		for(int i=0; i<n-1; i++) {
			int p = in.nextInt()-1;
			int q = in.nextInt()-1;
			g.get(p).add(q);
			g.get(q).add(p);
		}
		
		dfs(0, -1);
		go(0, -1);
		
		System.out.println((ans%MOD+MOD)%MOD);
	}

	public static void dfs(int u, int p) {
		
		
		dp1[u]=V[u];
		dp2[u]=-1*V[u];
		size[u]=1;
		
		for(int v: g.get(u)) {
			if (v==p) continue;
			
			dfs(v, u);
			
			dp1[u]+=dp2[v]+V[u]*size[v];
			dp2[u]+=dp1[v]-V[u]*size[v];
			size[u]+=size[v];
			
		}
	}
	
	public static void go(int u, int p) {
		//u is the root of the tree
		ans+=dp1[u];
		
		for(int v: g.get(u)) {
			if (v==p) continue;
			dp1[u]-=(dp2[v]+V[u]*size[v]);
			dp2[u]-=(dp1[v]-V[u]*size[v]);
			size[u]-=size[v];
			dp1[v]+=dp2[u]+V[v]*size[u];
			dp2[v]+=dp1[u]-V[v]*size[u];
			size[v]+=size[u];
			go(v, u);
			size[v]-=size[u];
			dp2[v]-=(dp1[u]-V[v]*size[u]);
			dp1[v]-=(dp2[u]+V[v]*size[u]);
			size[u]+=size[v];
			dp2[u]+=dp1[v]-V[u]*size[v];
			dp1[u]+=dp2[v]+V[u]*size[v];
		}
	}
}
//-5, -9, -2
//5, 10, 3
//9, 10, 7
//2, 3, 7
