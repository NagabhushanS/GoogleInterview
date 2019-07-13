package Maths.CombinatoricsAndProbability;

import java.math.BigInteger;
import java.util.Scanner;

public class UVA763 {

	private static String s1;
	private static String s2;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		BigInteger[] fib = new BigInteger[101];
		fib[0] = BigInteger.ONE;
		fib[1] = new BigInteger("2");
		for(int i=2; i<fib.length; i++) {
			fib[i] = fib[i-1].add(fib[i-2]);
		}
		
		int z=0;
		
		while(in.hasNext()) {
			s1 = in.next();
			s2 = in.next();
			z++;
			System.out.print(z==1?"":"\n");
			
			BigInteger b1 = BigInteger.ZERO;
			for(int i=s1.length()-1; i>=0; i--) {
				b1 = b1.add(fib[s1.length()-(i+1)].multiply(new BigInteger(""+s1.charAt(i))));
			}
			BigInteger b2 = BigInteger.ZERO;
			for(int i=s2.length()-1; i>=0; i--) {
				b2 = b2.add(fib[s2.length()-(i+1)].multiply(new BigInteger(""+s2.charAt(i))));
			}
			BigInteger sum = b1.add(b2);
			if (sum.equals(BigInteger.ZERO)) {
				System.out.println("0");
				continue;
			}
			boolean started = false;
			for(int i=fib.length-1; i>=0; i--) {
				if (sum.compareTo(fib[i])>=0) {
					sum = sum.subtract(fib[i]);
					System.out.print(1);
					started = true;
				} else {
					if (started)
						System.out.print(0);
				}
			}
			System.out.println();
		}

	}

}
