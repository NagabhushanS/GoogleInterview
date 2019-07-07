//package DataStructuresProblems;
//
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class P1181D {
//
//	private class Pair implements Comparable<Pair> {
//		private long v;
//		private int prei;
//		private int posti;
//		
//		public Pair(long a, int b, int c) {
//			v=a;
//			prei=b;
//			posti=c;
//		}
//		
//		public int compareTo(Pair p) {
//			return Long.compare(v, p.v);
//		}
//		
//	}
//	
//	private int n, m;
//	private long[] a;
//	private Pair[] pairs;
//	private long sp;
//	
//	
//	public P1181D(int n, int m, long[] a) {
//		this.n=n;
//		this.m=m;
//		this.a=a;  //ai is the count of ith city
//		pairs = new Pair[m];
//		long max=0;
//		long sum=0;
//		for(int i=0; i<m; i++) {
//			pairs[i] = new Pair(a[i], i, 0);
//			max = Math.max(max, a[i]);
//			sum+=a[i];
//		}
//		Arrays.sort(pairs);
//		for(int i=0; i<m; i++) {
//			pairs[i].posti=i;
//		}
//		sp = m*max-sum;
//	}
//	
//	public int query(long year) {
//		year-=n;
//		if (year>sp) {
//			return (int)(year%sp);
//		} else {
//			return -1;
//		}
//	}
//	
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		int n = in.nextInt();
//		int m = in.nextInt();
//		int q = in.nextInt();
//		long[] a = new long[m];
//		for(int i=0; i<n; i++) {
//			a[in.nextInt()-1]++;
//		}
//		P1181D sol = new P1181D(n, m, a);
//		for(int i=0; i<q; i++) {
//			long y = in.nextLong();
//			System.out.println(sol.query(y));
//		}
//	}
//
//}
