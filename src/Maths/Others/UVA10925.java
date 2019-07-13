package Maths.Others;

import java.math.BigInteger;
import java.util.Scanner;

public class UVA10925 {

	private static int n, f;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int z=0;
		while(true) {
			z++;
			n = in.nextInt();
			f = in.nextInt();
			if (n==0 && f==0) break;
			
			BigInteger sum = new BigInteger("0");
			
			for(int i=0; i<n; i++) {
				sum = sum.add(new BigInteger(in.next()));
			}
			
			System.out.println("Bill #"+z+" costs "+sum+": each friend should pay "+sum.divide(new BigInteger(""+f))+"\n");
			
		}
		

	}

}
