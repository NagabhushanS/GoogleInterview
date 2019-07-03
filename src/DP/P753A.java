package DP;

import java.util.Arrays;
import java.util.Scanner;

public class P753A {

	static int n;
	static long[][] dp;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		
		System.out.println(solve(n));
		
		dp = new long[1002][1002];
		
		for(int i=0; i<dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		System.out.println(solve(1, n));
		
		int i = 1;
		int left = n;
		
		while(i<=left) {
			if (dp[i][left]==1+dp[i+1][left-i]) {
				left-=i;
				i++;
				System.out.print(i-1+" ");
			} else {
				i++;
			}
		}
		if (left!=0) {
			System.out.println(left);
		}
		System.out.println();
	}
	
	public static int solve(int n) {
		int low = 1;
		int high = n;
		int mid = 0;
		while(low<high) {
			mid = low+(high-low+1)/2;
			if (n-mid*(mid+1)/2>=mid+1) {
				low=mid;
			} else {
				high = mid-1;
			}
		}
		return low+1;
	}
	
	public static long solve(int i, int left) {
		
		if (left==0) return 0;
		if (i>left) {
			return -(long)1e15;
		}
		
		if (dp[i][left]!=-1) return dp[i][left];
		
		return dp[i][left]=Math.max(1+solve(i+1, left-i), solve(i+1, left));
	}

}
