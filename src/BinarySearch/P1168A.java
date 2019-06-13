package BinarySearch;

import java.util.Scanner;

public class P1168A {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n, m;
		n = in.nextInt();
		m = in.nextInt();
		int[] a = new int[n];
		for(int i=0; i<n; i++) a[i] = in.nextInt();
		
		int low=0;
		int high=m;
		int mid=0;
		while(low<high) {
			mid = (low+high)/2;
			boolean impossible=false;
			int last=0;
			for(int i=0; i<n; i++) {
				if ((a[i]>last && m-a[i]+last<=mid) || (a[i]<=last && last-a[i]<=mid)) {
					
				} else {
					if (a[i]<last) {
						impossible=true;
						break;
					}
					last=a[i];
				}
			}
			if (impossible==true) {
				low=mid+1;
			} else {
				high=mid;
			}
		}
		
		System.out.println(low);

	}

}
