package Miscallaneous.TwoPointers;

import java.util.Arrays;
import java.util.Scanner;

public class P1166C {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] a = new int[n];
		for(int i=0; i<n; i++) {
			a[i] = Math.abs(in.nextInt());
		}
		Arrays.sort(a);
		long ans=0;
		for(int i=0; i<n; i++) {
			int s = 2*a[i];
			int low=i+1;
			int high=n-1;
			int mid=0;
			while(low<high) {
				mid = low+(high-low+1)/2;
				if (a[mid]<=s) {
					low=mid;
				} else {
					high=mid-1;
				}
			}
			if (low<n && a[low]<=s) {
				ans+=low-i;
			}
		}
		System.out.println(ans);
	}
	
	public static int searchMax(int[] a, int x) {
		int n = a.length;
		int low=0;
		int high = n-1;
		int mid=0;
		while(low<high) {
			mid = low+(high-low+1)/2;
			if (a[mid]<=x) {
				low=mid;
			} else {
				high=mid-1;
			}
		}
		if (a[low]<=x) {
			return low;
		} else {
			return -1;
		}
	}
	
	public static int searchMin(int[] a, int x) {
		int n = a.length;
		int low=0;
		int high = n-1;
		int mid=0;
		while(low<high) {
			mid = low+(high-low)/2;
			if (a[mid]>=x) {
				high=mid;
			} else {
				low=mid+1;
			}
		}
		if (a[low]>=x) {
			return low;
		} else {
			return -1;
		}
	}

}
