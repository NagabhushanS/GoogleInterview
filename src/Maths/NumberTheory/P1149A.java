package Maths.NumberTheory;

import java.util.Scanner;

public class P1149A {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] a = new int[n];
		int ones = 0;
		int twos = 0;
		for(int i=0; i<n; i++) {
			a[i] = in.nextInt();
			if (a[i]==1) ones++;
			else twos++;
		
		}
		int sum=0;
		for(int i=0; i<n; i++) {
			if (ones==0) {
				sum+=2;
				System.out.print(2+" ");
				twos--;
			} else if (twos==0) {
				sum+=1;
				System.out.print(1+" ");
				ones--;
			} else if (isPrime(sum+1)) {
				System.out.print(1+" ");
				sum+=1;
				ones--;
			} else if (isPrime(sum+2)){
				System.out.print(2+" ");
				sum+=2;
				twos--;
			} else {
				System.out.print(2+" ");
				sum+=2;
				twos--;
			}
		}
	}

	public static boolean isPrime(int n) {
		if (n==1) return false;
		if (n==2) return true;
		if (n%2==0) return false;
		
		for(int i=3; i*i<=n; i+=2) {
			if (n%i==0) return false;
		}
		return true;
	}
}

