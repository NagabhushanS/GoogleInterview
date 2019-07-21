package DP;

import java.util.Arrays;
import java.util.Scanner;

public class UVA108 {

	private static int n;
	private static int[][] a;
	private static int[] currentSum;
	private static int maxSum;
	private static int maxElement;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			n = in.nextInt();
			a = new int[n][n];
			maxElement = Integer.MIN_VALUE;
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					a[i][j] = in.nextInt();
					maxElement = Math.max(maxElement, a[i][j]);
				}
			}
			currentSum = new int[n];
			maxSum = 0;
		
			int left, right, top, down;
			left=0;
			right=0;
			top=0;
			down=0;
			
			for(int l=0; l<n; l++) {
				Arrays.fill(currentSum, 0);
				for(int r=l; r<n; r++) {
					for(int j=0; j<n; j++) {
						currentSum[j] += a[j][r];
					}
					int start=0;
					int sum=0;
					for(int j=0; j<n; j++) {
						sum+=currentSum[j];
						if (sum>maxSum) {
							maxSum=sum;
							left=l;
							right=r;
							down=j;
							top=start;
						}
						if (sum<0) {
							start = j+1;
							sum=0;
						}
					}
				}
			}
			System.out.println(left+" "+right+" "+top+" "+down);
			System.out.println(maxSum==0?maxElement:maxSum);
		}

	}

}
