package DP;

import java.util.ArrayList;
import java.util.Arrays;

public class TCConnectTheCities {

	static ArrayList<Integer> a;
	static int dist;
	static long[][][] dp;
	static int funds;
	
	public static void main(String[] args) {
		int[] position = {3, 7, 9};
		int distance = 10;
		int f = 5;
		
		dp = new long[position.length][f+1][distance+1];
		for(int i=0; i<dp.length; i++) {
			for(int j=0; j<dp[0].length; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		
		System.out.println(minimalRange(distance, f, position));

	}
	

	public static int minimalRange(int distance, int f, int[] position) {
		Arrays.sort(position);
		a = new ArrayList<>();
		for(int i=0; i<position.length; i++) {
			a.add(position[i]);
		}
		funds = f;
//		a.add(distance);
	
		dist = distance;
		
		return (int)solve(0, 0, 0);
	}

//	O(n*funds*dist^2)

	public static long solve(int i, int fundsLeft, int last) {
		if (fundsLeft>funds) {
			return (long)1e15;
		}
		if (i==a.size()) {
			return (dist-last);
		}
		
		if (dp[i][fundsLeft][last]!=-1) return dp[i][fundsLeft][last];
		
		long ans = (long)1e15;
		
		for(int j=last; j<=dist; j++) {
			ans = Math.min(ans, Math.max(j-last, solve(i+1, fundsLeft+Math.abs(a.get(i)-j), j)));
		}
		
		return dp[i][fundsLeft][last]=ans;
		
	}
	
	
}
