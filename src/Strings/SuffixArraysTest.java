package Strings;

import java.util.Scanner;

public class SuffixArraysTest {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.next()+'$';
		int[] suffixArray = construct(s, 256);
//		for(int x: suffixArray) {
//			System.out.print(x+" ");
//		}
//		System.out.println();
	}
	
	public static int[] construct(String s, int R) {
		int n = s.length();
		int[] suffix = new int[n];
		for(int i=0; i<n; i++) {
			suffix[i] = i;
		}
		
		int[] rank = new int[n];  //rank of ith actual suffix
		for(int i=0; i<n; i++) {
			rank[i] = s.charAt(i);
		}
		int[] tempRank = new int[n];
		
		for(int k=1; k<=n; k*=2) {
			//at the end of kth loop, the suffixes are sorted according to
			//their first 2^x = (k+1) characters where x = lg(k+1)
			keyIndexSorting(suffix, rank, tempRank, k, R);
			keyIndexSorting(suffix, rank, tempRank, 0, R);
			for(int i=0; i<n; i++) {
				rank[i] = tempRank[i];
			}
		}
		
		for(int x: suffix) {
			System.out.print(x+" ");
		}
		System.out.println();
		
		return suffix;
	}

	private static void keyIndexSorting(int[] suffix, int[] rank, int[] tempRank, int k, int R) {
		
		int n = suffix.length;
		
		int[] count = new int[R+1];
		int[] aux = new int[n];
		
		for(int i=0; i<n; i++) {
			int pos = suffix[i]+k;
			count[(pos<n?rank[pos]:0)]++;
		}
		
		for(int i=1; i<R; i++) {
			count[i] += count[i-1];
		}
		
		for(int i=n-1; i>=0; i--) {
			int pos = suffix[i]+k;
			aux[--count[(pos<n?rank[pos]:0)]] = suffix[i];
		}
		
		for(int i=0; i<n; i++) {
			suffix[i] = aux[i];
			tempRank[suffix[i]]=i;
		}
	
	}

}
