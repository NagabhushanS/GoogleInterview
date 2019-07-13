package Maths.NumberTheory;

import java.util.Scanner;

public class UVA294 {

	private static int lower, upper;
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		while(T-->0) {
			lower = in.nextInt();
			upper = in.nextInt();
			
			int maxdiv = 0;
			int ans = lower;
			
			for(int n=lower; n<=upper; n++) {
				int temp = n;
				int count = 1;
				for(int i=2; i*i<=temp; i++) {
					int exp=0;
					while(temp%i==0) {
						temp/=i;
						exp++;
					}
					count *= (exp+1);
				}
				if (temp>1) {
					count *= 2;
				}
				if (count>maxdiv) {
					maxdiv = count;
					ans = n;
				}
			}
			
			System.out.println("Between "+lower+" and "+upper+", "+ans+" has a maximum of "+maxdiv+" divisors.");
			
		}

	}

}
