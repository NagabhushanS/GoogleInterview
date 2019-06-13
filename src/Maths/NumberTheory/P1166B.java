package Maths.NumberTheory;

import java.util.Scanner;

public class P1166B {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		
		char[][] c = solve(n);
		
		if (c==null) {
			System.out.println(-1);
		} else {
			char[] cc = new char[c.length*c[0].length];
			
			for(int i=0; i<cc.length; i++) {
				cc[i] = c[i/c[0].length][i%c[0].length];
			}
			
			System.out.println(new String(cc));
		}
	}
	
	public static char[][] solve(int n) {
		
		char[] vowels = new char[] {'a', 'e', 'i', 'o', 'u'};
		char[][] c;
		for(int i=5; i*i<=n; i++) {
			if (n%i==0) {
				if (n/i<5) continue;
				c = new char[i][n/i];
				for(int j=0; j<i; j++) {
					for(int k=0; k<n/i; k++) {
						c[j][k] = vowels[(k%5+j%5)%5];
//						System.out.print(c[j][k]+" ");
					}
//					System.out.println();
				}
				
				return c;
			}
		}
		
		return null;
	}

}
