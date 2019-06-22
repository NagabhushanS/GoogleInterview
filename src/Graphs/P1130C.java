package Graphs;

import java.util.Arrays;
import java.util.Scanner;

public class P1130C {

	private static class Solution {
		
		static boolean[][] mark;
		static int[][] dist1;
		static int[][] dist2;
		static int[][] g;
		static int r1, c1, r2, c2;
		static int n;

		public static int minCost(int[][] grid, int R1, int C1, int R2, int C2){
			g = grid;
			n = grid.length;
			mark= new boolean[n][n];
			dist1 = new int[n][n];
			dist2 = new int[n][n];
			r1 = R1;
			r2 = R2;
			c1 = C1;
			c2 = C2;
			for(int i=0; i<n; i++){
				Arrays.fill(dist1[i], (int)1e8);
			}
			for(int i=0; i<n; i++){
				Arrays.fill(dist2[i], (int)1e8);
			}
			dfs(r1, c1, true);
			dfs(r2, c2, false);

			int minDistance = (int)1e8;
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if (dist1[i][j]<(int)1e8) {
						for(int x=0; x<n; x++) {
							for(int y=0; y<n; y++) {
								if (dist2[x][y]<(int)1e8) {
									if ((i-x)*(i-x)+(j-y)*(j-y)<minDistance) {
										minDistance = (i-x)*(i-x)+(j-y)*(j-y);
									}
								}
							}
						}
					}
				}
			}
			
			return minDistance;
			

		}

		private static void dfs(int u, int v, boolean flag){
			mark[u][v]=true;
			if (flag)
				dist1[u][v] = (u-r2)*(u-r2)+(v-c2)*(v-c2);
			else
				dist2[u][v] = (u-r1)*(u-r1)+(v-c1)*(v-c1);
			int[] dx = {1, -1, 0, 0};
			int[] dy = {0, 0, -1, 1};
			for(int k=0; k<4; k++){
				int nu = u+dx[k];
				int nv = v+dy[k];
				if (nu<0 || nu>=n || nv<0 || nv>=n){
					continue;
				}
				if (g[nu][nv]==1 || mark[nu][nv]) continue;
				dfs(nu, nv, flag);
			}

		}


	}

	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int r1 = in.nextInt()-1;
		int c1 = in.nextInt()-1;
		int r2 = in.nextInt()-1;
		int c2 = in.nextInt()-1;
		int[][] grid = new int[n][n];
		for(int i=0; i<n; i++) {
			String s = in.next();
			for(int j=0; j<n; j++) {
				grid[i][j] = s.charAt(j)-'0';
			}
		}
		Solution sol = new Solution();
		System.out.println(sol.minCost(grid, r1, c1, r2, c2));

	}

}
