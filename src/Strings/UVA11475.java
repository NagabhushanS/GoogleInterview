package Strings;

import java.util.Scanner;

public class UVA11475 {

	private static String s;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			s = in.next();
			int n = s.length();
			char[] c = s.toCharArray();
			for(int i=0; i<n/2; i++) {
				//swap c[i] with c[n-i-1]
				char temp = c[i];
				c[i] = c[n-i-1];
				c[n-i-1] = temp;
			}
			String rs = new String(c);
			int index = search(s, rs);
			//reverse characters in s from 0 to index-1 and append this reverse
			//string to the original string
			char[] ans = new char[s.length()+index];
			for(int i=0; i<s.length(); i++) {
				ans[i] = s.charAt(i);
			}
			int j=s.length();
			for(int i=index-1; i>=0; i--) {
				ans[j++] = s.charAt(i);
			}
			System.out.println(new String(ans));
			
			
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
