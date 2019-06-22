package Miscallaneous.TwoPointers;

import java.util.Scanner;

public class P1168B {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.next();
		
		int r, n;
		r = n = s.length();
		long ans=0;
		out:
		for(int x=n-1; x>=0; x--) {
			for(int k=1; x+2*k<r; k++) {
				if (s.charAt(x)==s.charAt(x+k) && s.charAt(x)==s.charAt(x+2*k)) {
					r = x+2*k;
					break;
				}
			}
			ans+=n-r;
		}
		System.out.println(ans);

	}

}
