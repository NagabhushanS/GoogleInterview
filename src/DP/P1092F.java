package DP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class P1092F {
	private static class Solution {
	    private HashMap<Integer, ArrayList<Integer>> g;
		private long[] sum;
		private long[] count;
		private long[] ans;
		private int[] w;
	    
		public long[] sumOfDistancesInTree(int N, int[][] edges, int[] w) {
			this.w=w;
			g = new HashMap<>();
			sum = new long[N];
			count = new long[N];
			ans = new long[N];
			for(int i=0; i<N; i++) {
				g.put(i, new ArrayList<>());
			}
			for(int i=0; i<N-1; i++) {
				int a = edges[i][0];
				int b = edges[i][1];
				g.get(a).add(b);
				g.get(b).add(a);
			}
			
			dfs(0, -1);
			go(0, -1);
			
			return ans;
	    }
		
		public void dfs(int u, int p) {
			
			count[u]=w[u];
			sum[u]=0;
			for(int v: g.get(u)) {
				if (v!=p) {
					dfs(v, u);
					count[u]+=count[v];
					sum[u]+=sum[v]+count[v];
				}
			}
		}
		
		public void go(int u, int p) {
			//now tree is rooted at u and sum[u] stores correct answer
			ans[u]=sum[u];
			
			for(int v: g.get(u)) {
				if (p==v) continue;
				
				sum[u]-=(sum[v]+count[v]);
				count[u]-=count[v];
				sum[v]+=(count[u]+sum[u]);
				count[v]+=count[u];
				
				go(v, u);
				
				count[v]-=count[u];
				sum[v]-=(count[u]+sum[u]);
				count[u]+=count[v];
				sum[u]+=(sum[v]+count[v]);
			}
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] a = new int[n];
		for(int i=0; i<n; i++) {
			a[i] = in.nextInt();
		}
//		Solution.sumOfDistancesInTree(int N, int[][] edges)
		int[][] edges = new int[n-1][2];
		for(int i=0; i<n-1; i++) {
			int p = in.nextInt()-1;
			int q = in.nextInt()-1;
			edges[i] = new int[] {p, q};
		}
		Solution sol = new Solution();
		long[] ans = sol.sumOfDistancesInTree(n, edges, a);
		
		long max=0;
		for(int i=0; i<n; i++) {
			max=Math.max(max, ans[i]);
		}
		
		System.out.println(max);
		
	}
	
	

}
