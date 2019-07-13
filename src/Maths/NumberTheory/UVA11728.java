package Maths.NumberTheory;

import java.util.Scanner;

public class UVA11728 {

	private static int S;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int z=0;
		next:
		while(true) {
			z++;
			S = in.nextInt();
			if (S==0) break;
			for(int i=S; i>=0; i--) {
				int sum=1;
				int temp = i;
				for(int j=2; j*j<=temp; j++) {
					int count=0;
					while(temp%j==0) {
						temp/=j;
						count++;
					}
					if (count!=0) {
						sum *= (Math.pow(j, count+1)-1)/(j-1);
					}
				}
				if (temp>1) {
					sum *= (Math.pow(temp, 2)-1)/(temp-1);
				}
				
				if (sum==S) {
					System.out.println("Case "+z+": "+i);
					continue next;
				}
			}
			System.out.println("Case "+z+": -1");
			
		}
			

	}

	private static boolean isPrime(int x) {
		
		if (x==0 || x==1) return false;
		if (x==2) return true;
		if (x%2==0) return false;
		
		for(int i=3; i*i<=x; i+=2) {
			if (x%i==0) return false;
		}
		
		return true;
	}
	
}
