package DP;

import java.util.Arrays;
import java.util.Scanner;

public class P626F {
	static int n, k;
	static int[] a;
	static final long MOD = (long)1e9+7;
	static long[][] dp1;
	static long[][] dp2;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		k = in.nextInt();
		a = new int[n];
		
		dp1 = new long[n+2][k+1];
		dp2 = new long[n+2][k+1];
		
		for(int i=0; i<n; i++) a[i] = in.nextInt();
		Arrays.sort(a);
		
		for(int imbalance=0; imbalance<=k; imbalance++) {
			dp1[0][imbalance]=1;
		}
		
		for(int i=n-1; i>=1; i--) {
			for(int g=0; g<=n; g++) {
				for(int imbalance=0; imbalance<=k; imbalance++) {
					long ans=0;
					
					if (g!=0 && imbalance+g*(a[i]-a[i-1])<=k)
						ans = (ans+(g*dp1[g][imbalance+g*(a[i]-a[i-1])]));
					if (g>0 && imbalance+g*(a[i]-a[i-1])<=k)
						ans = (ans+(g*dp1[g-1][imbalance+g*(a[i]-a[i-1])])); //closing
					if (imbalance+g*(a[i]-a[i-1])<=k)
						ans = (ans+dp1[g+1][imbalance+g*(a[i]-a[i-1])]);
					if (imbalance+g*(a[i]-a[i-1])<=k)
						ans = (ans+dp1[g][imbalance+g*(a[i]-a[i-1])]);
					
					dp2[g][imbalance] = ans%MOD;
				}
			}
			
			for(int g=0; g<=n; g++) {
				for(int imbalance=0; imbalance<=k; imbalance++) {
					dp1[g][imbalance] = dp2[g][imbalance];
				}
			}
		}
		System.out.println((dp1[1][0]+dp1[0][0])%MOD);
		
	}

}
