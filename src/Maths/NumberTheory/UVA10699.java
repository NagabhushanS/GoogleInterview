package Maths.NumberTheory;

import java.util.Arrays;
import java.util.Scanner;

public class UVA10699 {

	private static int n;
	private static final int MAX = (int)1e6+1;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int[] count = new int[MAX];
		
		for(int i=2; i<MAX; i++) {
			if (count[i]==0) {
				for(int j=i; j<MAX; j+=i) {
					count[j]++;
				}
			}
		}
		
		while(true) {
			n = in.nextInt();
			if (n==0) break;
//			naiveSolution(n);
			System.out.println(n+" : "+count[n]);
			
		}
	}
	
	private static void naiveSolution(int n) {
		int temp=n;
		int ans=0;
		
		for(int i=2; i*i<=temp; i++) {
			if (temp%i==0) {
				ans++;
			}
			while(temp%i==0) {
				temp/=i;
			}
		}
		if (temp>1) ans++;
		System.out.println(n+" : "+ans);
	}

}
