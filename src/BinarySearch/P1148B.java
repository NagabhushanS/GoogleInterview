package BinarySearch;

import java.util.Scanner;

public class P1148B {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n, m, ta, tb, k;
		n = in.nextInt();
		m = in.nextInt();
		ta = in.nextInt();
		tb = in.nextInt();
		k = in.nextInt();
		int[] a = new int[n];
		int[] b = new int[m];
		for(int i=0; i<n; i++) a[i] = in.nextInt();
		for(int i=0; i<m; i++) b[i] = in.nextInt();
		int ans=0;
		for(int j=0; j<m; j++) {
			//take jth flight from b to c
			//start from a on or before bj-ta
			int low=0;
			int high=n-1;
			int mid=0;
			while(low<high) {
				mid = (low+high)/2;
				//mid is the flight from a to b, find the first flight from b to c
				//for mid, find the first flight available from b to c
				int reachAtB = a[mid]+ta;
				int innerLow=0;
				int innerHigh=m-1;
				int innerMid=0;
				while(innerLow<innerHigh) {
					innerMid = (innerLow+innerHigh)/2;
					if (b[innerMid]>=reachAtB) {
						innerHigh = innerMid;
					} else {
						innerLow = innerMid+1;
					}
				}
				if (reachAtB<=b[mid]) {
					high=mid;
				} else {
					low=mid+1;
				}
			}
			//check if low satisfied
			int reachAtB = a[low]+ta;
			if (reachAtB>=b[mid]) {
				
			} else {
				//continue;
			}
		}

	}

}
