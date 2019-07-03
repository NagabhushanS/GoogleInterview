package DP;

import java.util.Arrays;
import java.util.Scanner;

public class P367E {

	static int n, m, x;
	static long[][][] dp;
	static long[][][] prevDp;
	static long[] dp2;
	static final int MOD = (int)1e9+7;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		x = in.nextInt();
		
		if (n>m) {
			System.out.println(0);
			return;
		}
		
		dp = new long[m+2][n+1][2];
		prevDp = new long[m+2][n+1][2];
		dp2 = new long[m+1];
		Arrays.fill(dp2, -1);
		
		dp[0][n][1]=1;
		
		for(int i=m; i>=2; i--) {
			for(int g=0; g<=Math.min(n, i); g++) {
				for(int count=0; count<=Math.min(n, i); count++) {
					for(int flag=0; flag<2; flag++) {
						long ans=0;
						
						//nothing ends
						ans += dp[g][count][flag];
						
						//nothing ends and a new one begins
						if (count+1<=n)
							ans += dp[g+1][count+1][toInt(toBool(flag) || (i==x))];
						
						//g==0 then begins and end
						if (g==0 && count+1<=n)
							ans += dp[g][count+1][toInt(toBool(flag) || (i==x))];
						
						
						//min l ends
						if (g-1>=0)
							ans += dp[g-1][count][flag];
						
						//min l ends and a new one begins
						if (g!=0 && count+1<=n)
							ans += dp[g][count+1][toInt(toBool(flag) || (i==x))];
						prevDp[g][count][flag] = ans%MOD;
					}
				}
			}
			
			for(int g=0; g<=Math.min(n, i); g++) {
				for(int count=0; count<=Math.min(n, i); count++) {
					for(int flag=0; flag<2; flag++) {
						dp[g][count][flag] = prevDp[g][count][flag];
					}
				}
			}
		}
		
		System.out.println((fact(n)*(dp[1][1][x==1?1:0]+dp[0][1][x==1?1:0]+dp[0][0][0])%MOD)%MOD);
		
	}
	
	/*public static long solve(int i, int g, int flag, int count) {
		if (g<0) return 0;
		if (count>n) return 0;
	
		if (i>m) {
//			System.out.println(i+" "+g+" "+flag+" "+count);
			if (g==0 && flag==1 && count==n) {
				return 1;
			} else {
				return 0;
			}
		}
		
		if (dp[i][g][count][flag]!=-1) return dp[i][g][count][flag];
		
		
		long ans=0;
		
		//nothing ends
		ans += solve(i+1, g, flag, count);
		
		//nothing ends and a new one begins
		ans += solve(i+1, g+1, toInt(toBool(flag) || (i==x)), count+1);
		
		//g==0 then begins and end
//		if (g==0)
//			ans += solve(i+1, g, toInt(toBool(flag) || (i==x)), count+1);
		
		
		//min l ends
		ans += solve(i+1, g-1, flag, count);
		
		//min l ends and a new one begins
		ans += solve(i+1, g, toInt(toBool(flag) || (i==x)), count+1);
		
		return dp[i][g][count][flag]=ans%MOD;
	}*/
	
	private static int toInt(boolean b) {
		return b?1:0;
	}

	private static boolean toBool(int i) {
		return i>0?true:false;
	}
	
	private static long fact(int i) {
		if (i==1) return 1;
		
		if (dp2[i]!=-1) return dp2[i];
		return dp2[i]=(i*fact(i-1))%MOD;
	}
}
