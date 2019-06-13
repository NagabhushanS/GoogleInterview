package DP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Leet834 {
	private static class Solution {
	    private HashMap<Integer, ArrayList<Integer>> g;
		private int[] sum;
		private int[] count;
		private int[] ans;
	    
		public int[] sumOfDistancesInTree(int N, int[][] edges) {
			g = new HashMap<>();
			sum = new int[N];
			count = new int[N];
			ans = new int[N];
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
			
			count[u]=1;
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
		
		
	}
	
	

}
