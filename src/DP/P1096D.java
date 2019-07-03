package DP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class P1096D {

	static int n;
	static int[] a;
	static String s;
	static HashMap<String, Integer> sp;
	static String[] ps;
	static long[][] dp = new long[100001][4];
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();

		s = in.next();
		a = new int[n];
		
		for(int i=0; i<n; i++) {
			a[i] = in.nextInt();
		}
		
		ps = new String[] {"", "h", "ha", "har"};
		sp = new HashMap<>();
		for(int i=0; i<ps.length; i++) {
			sp.put(ps[i], i);
		}
		
		
		for(int i=n-1; i>=0; i--) {
			for(int prefix=0; prefix<4; prefix++) {
				String prefixString = ps[prefix];
				String afterString = prefixString+s.charAt(i);
				long ans = (long)1e15;
				if (afterString.equals("hard")) {
					ans = Math.min(ans, a[i]+dp[i+1][prefix]);
				} else {
					if (sp.containsKey(afterString)) {
						ans = Math.min(ans, dp[i+1][sp.get(afterString)]);
						ans = Math.min(ans, a[i]+dp[i+1][prefix]);
					} else {
						ans = Math.min(ans, dp[i+1][prefix]);
					}
				}
				dp[i][prefix] = ans;
			}
		}
		
		System.out.println(dp[0][0]);
	}
	
	/*public static long solve(int i, int prefix) {
		if (i==n) return 0;
		//prefix
		//""-->0
		//"h"-->1
		//"ha"-->2
		//"har"-->3
		//"hard"-->4
		
		if (dp[i][prefix]!=-1) return dp[i][prefix];
		
		String prefixString = ps[prefix];
		String afterString = prefixString+s.charAt(i);
		long ans = (long)1e15;
		if (afterString.equals("hard")) {
			ans = Math.min(ans, a[i]+solve(i+1, prefix));
		} else {
			if (sp.containsKey(afterString)) {
				ans = Math.min(ans, solve(i+1, sp.get(afterString)));
				ans = Math.min(ans, a[i]+solve(i+1, prefix));
			} else {
				ans = Math.min(ans, solve(i+1, prefix));
			}
		}
		return dp[i][prefix]=ans;
		
	}*/

}
