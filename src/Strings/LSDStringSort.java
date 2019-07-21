package Strings;

import java.util.Arrays;
import java.util.Scanner;

public class LSDStringSort {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		String[] a = new String[n];
		for(int i=0; i<n; i++) {
			a[i] = in.next();
		}
		lsdSortUnequalWidth(a, 256);
	
	}
	
	public static void lsdSortUnequalWidth(String[] a, int R) {
		//every string is of width w
		if (a==null || a.length==0) return;
		
		int w = 0;
		int n = a.length;
		for(int i=0; i<n; i++) {
			w = Math.max(w, a[i].length());
		}
		
		int[] count = new int[R+1];
		String[] aux = new String[n];
		
		for(int j=w-1; j>=0; j--) {
			//sort the strings in 'a' using char at i as keys
			Arrays.fill(count, 0);
			
			for(int i=0; i<n; i++) {
				count[charAt(j, a[i])+1]++;
			}
			
			for(int i=1; i<R+1; i++) {
				count[i] += count[i-1];
			}
			
			for(int i=n-1; i>=0; i--) {
				aux[--count[charAt(j, a[i])+1]] = a[i];
			}
			
			for(int i=0; i<n; i++) {
				a[i] = aux[i];
			}
		}
		
		for(String s: a) {
			System.out.print(s+" ");
		}
		System.out.println();
	}

	public static void lsdSort(String[] a, int R) {
		//every string is of width w
		if (a==null || a.length==0) return;
		
		int w = a[0].length();
		int n = a.length;
		
		int[] count = new int[R];
		String[] aux = new String[n];
		
		for(int j=w-1; j>=0; j--) {
			//sort the strings in 'a' using char at i as keys
			Arrays.fill(count, 0);
			
			for(int i=0; i<n; i++) {
				count[a[i].charAt(j)]++;
			}
			
			for(int i=1; i<R; i++) {
				count[i] += count[i-1];
			}
			
			for(int i=n-1; i>=0; i--) {
				aux[--count[a[i].charAt(j)]] = a[i];
			}
			
			for(int i=0; i<n; i++) {
				a[i] = aux[i];
			}
		}
		
		for(String s: a) {
			System.out.print(s+" ");
		}
		System.out.println();
	}
	
	
	private static int charAt(int j, String s) {
		if (j>=s.length()) {
			return -1;
		} else {
			return s.charAt(j);
		}
	}
}


//5
//abcdf
//dewff
//dnwej
//asdfd
//csdgf