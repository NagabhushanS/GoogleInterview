package Strings;

import java.util.Scanner;

public class KMPTest {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String t = in.next();
		String p = in.next();
		
		System.out.println(search(t, p));
		System.out.println(kmpSearch(t, p));

	}
	
	private static int[] backTable(String p) {
		int m = p.length();
		int[] b = new int[m];
		
		int j=0;
		int i=1;
		
		while(i<m) {
			if (p.charAt(i)==p.charAt(j)) {
				b[i] = j+1;
				i++;
				j++;
			} else {
				while(j!=0 && p.charAt(i)!=p.charAt(j)){
					j = b[j-1];
				}
				if (p.charAt(j)==p.charAt(i)) {
					b[i]=j+1;
					j++;
				} 
				i++;
			}
		}
		
		return b;
	}
	
	private static int kmpSearch(String t, String p) {
		int n = t.length();
		int m = p.length();
		
		int[] b = backTable(p);
		
		int j=0;
		int i=0;
		for(i=0; i<n; i++) {
			if (j==m) {
				break;
			}
			if (p.charAt(j)==t.charAt(i)) {
				j++;
			} else {
				while(j!=0 && p.charAt(j)!=t.charAt(i)) {
					j = b[j-1];
				}
				if (p.charAt(j)==t.charAt(i)) {
					j++;
				}
			}
		}
		if (j==m) {
			return i-m;
		}
		return -1;
	}
	
	private static int search(String t, String p) {
		//return the index of first occurance of p in t
		
		
		int n = t.length();
		int m = p.length();
		
		for(int i=0; i<n-m+1; i++) {
			boolean notFound = false;
			for(int j=0; j<m; j++) {
				if (t.charAt(i+j)!=p.charAt(j)) {
					notFound = true;
					break;
				}
			}
			if (!notFound) {
				return i;
			}
		}
		
		return -1;
	}

}

//ewfanjsklgvnfhdsgnlkfdvdnfdjklfhjefushoijkerfhcijowkecefhijef
//cefhi
