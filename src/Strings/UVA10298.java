package Strings;

import java.util.Scanner;

public class UVA10298 {

	private static String s;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(true) {
			s = in.next();
			if (s.equals(".")) break;
			
			int[] b = backTable(s);
			int n = s.length();
			
			System.out.println(n/(n-b[n-1]));
		}
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
