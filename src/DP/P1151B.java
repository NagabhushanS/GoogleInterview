package DP;

import java.util.Scanner;

public class P1151B {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int[][] a = new int[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				a[i][j] = in.nextInt();
			}
		}
		boolean[][] dp = new boolean[n+1][1<<11];
		for(int i=1; i<(1<<11); i++) {
			dp[n][i]=true;
		}
		for(int i=n-1; i>=0; i--) {
			out:
			for(int xor=0; xor<(1<<11); xor++) {
				
				for(int j=0; j<m; j++) {
					if (dp[i+1][a[i][j]^xor]) {
						dp[i][xor]=true;
						continue out;
					}
				}
			}
		}
		if (dp[0][0]) {
			System.out.println("TAK");
		} else {
			System.out.println("NIE");
		}
		
		if (dp[0][0]) {
			int i=0;
			int xor=0;
			while(i!=n) {
				for(int j=0; j<m; j++) {
					if (dp[i+1][xor^a[i][j]]) {
						xor=xor^a[i][j];
						i=i+1;
						System.out.print((j+1)+" ");
						break;
					}
				}
			}
		}

	}

}
