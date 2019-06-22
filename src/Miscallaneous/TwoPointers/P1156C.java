package Miscallaneous.TwoPointers;

import java.util.Arrays;
import java.util.Scanner;

public class P1156C {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n, z;
		n = in.nextInt();
		z = in.nextInt();
		
		long[] a = new long[(int)n];
		for(int i=0; i<n; i++) a[i] = in.nextLong();
		
		Arrays.sort(a);
		int j=n-1;
		long ans=0;
		boolean[] mark = new boolean[n];
		for(int i=n-2; i>=0; i--) {
			
			while(j>=0 && mark[j]==false) {
				j--;
			}
			if (j<=i) break;
			if (a[j]-a[i]>=z) {
				mark[i]=mark[j]=true;
				ans++;
				j--;
			} else {
				continue;
			}
		}
		System.out.println(ans);

	}

}
