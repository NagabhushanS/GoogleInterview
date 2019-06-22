package DP;

import java.util.Arrays;
import java.util.Scanner;

public class P1131D {
	private int[][] g;
	private int n;
	private int m;
	private int[] id;
	private boolean possible=true;
	private int[] dp;
	private boolean[] onStack;

	public P1131D (int [][] grid){
		if (grid==null) throw new IllegalArgumentException();
		//grid[i][]] → 0, 1, 2
		g = grid;
		n = g.length;
		if (n>=1){
			m = g[0].length;
		}
		id = new int[n+m];
		dp = new int[n+m];
		onStack = new boolean[n+m];
		Arrays.fill(dp, -1);
		for(int i=0; i<n+m; i++) id[i] = i;
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				if (g[i][j]==0){
					int p = root(i);
					int q = root(j+n);
					id[p] = q;	
				} 
			}
		}
		
	}

	private int root(int u) {
		while (u != id[u]) {
			id[u] = id[id[u]]; // path compression
			u = id[u];
		}
		return u;
	}

	public void maxRating() {
		if (!possible) {
			return;
		}
		for(int i=0; i<n; i++) {
			maxRating(root(i));
		}
		System.out.println("Yes");
		for(int i=0; i<n; i++) {
			System.out.print(maxRating(root(i))+" ");
		}
		System.out.println();
		for(int j=n; j<n+m; j++) {
			System.out.print(maxRating(root(j))+" ");
		}
		System.out.println();
		
	}

	private int maxRating(int u) {
		if (onStack[u]) {
			System.out.println("No");
			System.exit(0);
		}
		onStack[u]=true;
		if (dp[u]!=-1) {
			onStack[u]=false;
			return dp[u];
		}
		
		boolean setFlag = false;
		if (u < n) {
			setFlag = true;
		}

		if (setFlag) {
			int ans = 1;
			for (int j = 0; j < m; j++) {
				if (g[u][j] == 2) {
					ans = Math.max(ans, 1 + maxRating(root(j+n)));
				}
			}
			onStack[u] = false;
			return dp[u]=ans;
		} else {
			int ans = 1;
			for (int i = 0; i < n; i++) {
				if (g[i][u - n] == 1) {
					ans = Math.max(ans, 1 + maxRating(root(i)));
				}
			}
			onStack[u] = false;
			return dp[u]=ans;
		}

	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int[][] g = new int[n][m];
		for(int i=0; i<n; i++) {
			String s = in.next();
			for(int j=0; j<m; j++) {
				if (s.charAt(j)=='>') {
					g[i][j]=2;
				} else if (s.charAt(j)=='<') {
					g[i][j]=1;
				} else {
					g[i][j]=0;
				}
			}
		}
		P1131D sol = new P1131D(g);
		sol.maxRating();

	}

}
