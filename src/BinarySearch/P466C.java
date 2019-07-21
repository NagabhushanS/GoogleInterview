package BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class P466C {
	
	private static int n;
	private static int[] a;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		a = new int[n];
		for(int i=0; i<n; i++) {
			a[i] = in.nextInt();
		}
		long sum = 0;
		for(int x: a) {
			sum+=x;
		}
		long newSum=0;
		ArrayList<Long> first = new ArrayList<>();
		ArrayList<Long> second = new ArrayList<>();
		for(int i=0; i<n; i++) {
			newSum+=a[i];
			if (newSum*3==sum) {
				first.add((long)i);
			}
			if (i!=n-1 && newSum*3 == 2*sum) {
				second.add((long)i);
			}
		}
		
		long ans=0;
		for(int i=0; i<first.size(); i++) {
			int low = 0;
			int high = second.size()-1;
			int mid = 0;
			long pivot = first.get(i);
			while(low<high) {
				mid = low+(high-low)/2;
				if (second.get(mid)>pivot) {
					high = mid;
				} else {
					low = mid+1;
				}
			}
			if (second.get(low)>pivot) {
				ans += second.size()-low;
			}
		}
		System.out.println(ans);
		

	}

}
