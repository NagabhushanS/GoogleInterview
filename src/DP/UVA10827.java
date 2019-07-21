package DP;

import java.util.Arrays;
import java.util.Scanner;

public class UVA10827 {

	private static int n;
	private static int[][] a;
	private static int maxElement=-(int)1e9;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		while(T-->0) {
			maxElement=-(int)1e9;
			n = in.nextInt();
			a = new int[2*n][2*n];
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					a[i][j] = in.nextInt();
					maxElement = Math.max(maxElement, a[i][j]);
					a[i+n][j] = a[i][j+n] = a[i+n][j+n] = a[i][j];
				}
			}
			
			int maxSum = 0;
			int currentSum[] = new int[2*n];
			
			for(int l=0; l<n; l++) {
				Arrays.fill(currentSum, 0);
				for(int r=l; r<l+n; r++) {
					for(int j=0; j<2*n; j++) {
						currentSum[j] += a[j][r];
					}
//					if (r==l+2)
//						for(int x: currentSum) {
//							System.out.print(x+" ");
//						}
//					System.out.println();
					int sum=0;
					int start = 0;
					for(int j=1; j<2*n; j++) {
						if (j==start+n) {
							sum-=currentSum[start];
							start++;
						}
						sum+=currentSum[j];
						if (sum>=maxSum && j-start+1<=n) {
							maxSum=sum;
						}
						if (sum<0) {
							start = j+1;
							sum=0;
						}
					}
				}
			}
			
			System.out.println(maxSum==0?maxElement:maxSum);
		}

	}

}
