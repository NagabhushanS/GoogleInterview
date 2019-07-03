package DP;

import java.util.Scanner;

public class P769C {

	static Scanner in = new Scanner(System.in);
	static int n, m, k;
	static char[][] g;
	static int sx;
	static int sy;
	static int[] dx = {1, 0, 0, -1};
	static int[] dy = {0, -1, 1, 0};
	
	public static void main(String[] args) {
		n = in.nextInt();
		m = in.nextInt();
		k = in.nextInt();
		g = new char[n][m];
		
		for(int i=0; i<n; i++) {
			String s = in.next();
			for(int j=0; j<m; j++) {
				g[i][j] = s.charAt(j);
				if (g[i][j]=='X') {
					sx = i;
					sy = j;
				}
			}
		}
		
		
		boolean[][][] dp = new boolean[n+1][m+1][k+1];
		dp[sx][sy][0] = true;
		
		for(int kLeft=1; kLeft<=k; kLeft++) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					boolean ans = false;
					
					for(int k=0; k<4; k++) {
						int ni = i+dx[k];
						int nj = j+dy[k];
						if (ni<0 || ni>=n || nj<0 || nj>=m) continue;
						if (g[ni][nj]=='*') continue;
						ans = ans||dp[ni][nj][kLeft-1];
						if (ans) break;
					}
					dp[i][j][kLeft]=ans;
				}
			}
		}
		if (!dp[sx][sy][k]) {
			System.out.println("IMPOSSIBLE");
			return;
		}
		
		int i = sx;
		int j = sy;
		int kLeft = k;
		
		String[] direction = {"D", "L", "R", "U"};
		
		while(kLeft!=0) {
			int k;
			for(k=0; k<4; k++) {
				int ni = i+dx[k];
				int nj = j+dy[k];
				if (ni<0 || ni>=n || nj<0 || nj>=m) continue;
				if (g[ni][nj]=='*') continue;
				if (dp[ni][nj][kLeft-1]) {
					i = ni;
					j = nj;
					kLeft--;
					System.out.print(direction[k]);
					break;
				}
			}
			
		}
		System.out.println();
	}
	
	

}
