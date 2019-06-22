package DP;

import java.util.Scanner;

public class P1108D {

	static int n;
	static String s;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long[][] dp = new long[2*100000+1][3];
		n = in.nextInt();
		s = in.next();
		
		for(int i=n-1; i>=0; i--) {
			for(int prev=0; prev<3; prev++) {
				//prev = 0--R, 1--G, 2--B
				//either
				//1. change color
				//2. keep same (only if current is not same as prev)
				int current = (s.charAt(i)=='R'?0:(s.charAt(i)=='G'?1:2));
				long ans=(long)1e15;
				for(int nc=0; nc<3; nc++) {
					if (nc==prev) continue;
					ans = Math.min(ans, dp[i+1][nc]+(nc!=current?1:0));
				}
				dp[i][prev] = ans;
			}
		}
		
		long ans=(long)1e15;
		int startColor = 0;
		int current = (s.charAt(0)=='R'?0:(s.charAt(0)=='G'?1:2));
		for(int i=0; i<3; i++) {
			long val = dp[1][i]+(current==i?0:1);
			if (val<ans) {
				startColor = i;
				ans = val;
			}
		}
		System.out.println(ans);
		
		int i = 1;
		int prev = startColor;
		char[] colors = {'R', 'G', 'B'};
		System.out.print(colors[startColor]);
		while(i!=n) {
			for(int j=0; j<3; j++) {
				if (prev==j) continue;
				current = (s.charAt(i)=='R'?0:(s.charAt(i)=='G'?1:2));
				if (dp[i][prev]==(current!=j?1:0)+dp[i+1][j]) {
					i++;
					prev = j;
					System.out.print(colors[j]);
					break;
				}
			}
		}
		

	}

}
