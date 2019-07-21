package DP;

import java.util.ArrayList;
import java.util.Scanner;

public class UVA481 {

	private static int n;
	private static ArrayList<Integer> a;
	private static int[][] dp;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		a = new ArrayList<Integer>();
//		int count=0;
		while(in.hasNext()) {
			int u = in.nextInt();
			if (u==-199) break;
			a.add(u);
			
		}
		n = a.size();
		dp = new int[n+1][n+1];
		for(int i=0; i<=n; i++) {
			for(int j=0; j<=n; j++) {
				dp[i][j]=-1;
			}
		}
//		System.out.println("SIZE "+n);
		int ans = 0;
		int start = 0;
		for(int i=0; i<n; i++) {
			int lis = 1+solve(i+1, i);
			if (lis>=ans) {
				ans = lis;
				start=i;
			}
		}
		System.out.println(ans);
		System.out.println('-');
		System.out.println(a.get(start));
		
		int i = start+1;
		int lastIndex = start;
		
		while(i!=n) {
			if (a.get(i)<=a.get(lastIndex)) {
				i++;
			} else {
				if (solve(i, lastIndex)==solve(i+1, lastIndex)) {
					i++;
				} else if (solve(i, lastIndex)==1+solve(i+1, i)){
					System.out.println(a.get(i));
					lastIndex = i;
					i++;
				} else {
					System.out.println("LOL");
				}
			}
		}
		
	}
	
	public static int solve(int i, int lastIndex) {
		if (i==n) {
			return 0;
		}
//		System.out.println("I "+i);
		if (dp[i][lastIndex]!=-1) return dp[i][lastIndex];
		
		if (a.get(i)<=a.get(lastIndex)) {
			return dp[i][lastIndex]=solve(i+1, lastIndex);
		} else {
			return dp[i][lastIndex]=Math.max(solve(i+1, lastIndex), 1+solve(i+1, i));
		}
	}

}
