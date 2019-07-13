package Maths.NumberTheory;

import java.util.Scanner;

public class UVA10179 {

	private static int n;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		while(true) {
			n = in.nextInt();
			if (n==0) break;
			int temp = n;
			long ans=n;
			
			for(int i=2; i*i<=temp; i++) {
				
				if (temp%i==0) {
					ans *= (i-1);
					ans /= i;
				}
				
				while(temp%i==0) {
					temp/=i;
				}
			}
			
			if (temp>1) {
				ans = ans*(temp-1)/temp;
			}
			
			System.out.println(ans);
		}
		

	}

}
