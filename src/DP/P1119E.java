package DP;

import java.util.Arrays;
import java.util.Scanner;

public class P1119E {

	static int n;
	static int[] a;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		a = new int[n];
		for(int i=0; i<n; i++) {
			a[i] = in.nextInt();
		}
		Arrays.sort(a);
		System.out.println(solve(0));
	}
	
	public static long solve(int i) {
		if (i==n) {
			return 0;
		}
		long ans=solve(i+1);
		if (i+2<n && (a[i+1]==a[i+2] && a[i]<a[i+1])) {
			ans = Math.max(ans, 1+solve(i+1));
		}
		return ans;
	}

}
