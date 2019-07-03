package DP;

import java.util.Arrays;

public class TCTheSequencesLevelThree {

	static int[] a;
	static int k;
	static int n;
	
	public static void main(String[] args) {
		a = new int[]{96, 29, 21, 90, 46, 77, 31, 63, 79};
		k = 44;
		n = a.length;
		Arrays.sort(a);
		
		
	}
	
	public static long solve(int leftPrev, int rightPrev) {
		int i = 1+Math.max(leftPrev, rightPrev);
		if (i==n-1) {
			if (rightPrev!=-1 && leftPrev!=-1 && a[n-1]-a[leftPrev]<=k && a[n-1]-a[rightPrev]<=k) return 1;
			else return 0;
		}
		
		return (leftPrev==-1 || a[i]-a[leftPrev]<=k?solve(i, rightPrev):0)+(rightPrev==-1 || a[i]-a[rightPrev]<=k?solve(leftPrev, i):0);
	}

}
