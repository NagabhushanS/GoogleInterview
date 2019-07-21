package Strings;

import java.util.Scanner;

public class UVA11576 {

	private static int n;
	private static int w;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		while(T-->0) {
			n = in.nextInt();
			w = in.nextInt();
			String last = in.next();
			long ans = n*w;
			for(int i=0; i<w-1; i++) {
				String current = in.next();
				int p=search(last, current);
//				System.out.println("P "+p);
				ans-=(n-p);
				last = current;
			}
			System.out.println(ans);
		}

	}
	
	private static int search(String s, String p) {
		
		int n = s.length();
		int m = p.length();
		
		int[] b = backTable(p);
		
		int i=0; //pointer in s
		int j=0; //pointer in p
		
		for(i=0; i<n; i++) {
			if (j==m) {
				break;
			}
			if (s.charAt(i)==p.charAt(j)) {
				j++;
			} else {
				while(j!=0 && s.charAt(i)!=p.charAt(j)) {
					j = b[j-1];
				}
				if (s.charAt(i)==p.charAt(j)) {
					j++;
				}
			}
		}
		return i-j;
	}
	
	private static int[] backTable(String p) {
		int m = p.length();
		int[] b = new int[m];
		
		int j=0;
		int i=1;  //i points to the index in our back table
		
		while(i!=m) {
			if (p.charAt(j)==p.charAt(i)) {
				b[i] = j+1;
				i++;
				j++;
			} else {
				while(j!=0 && p.charAt(j)!=p.charAt(i)) {
					j = b[j-1];
				}
				if (p.charAt(j)==p.charAt(i)) {
					b[i] = j+1;
					j++;
				}
				i++;
			}
		}
		
		return b;
		
	}

}
