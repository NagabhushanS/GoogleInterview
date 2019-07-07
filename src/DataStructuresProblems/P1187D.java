//package DataStructuresProblems;
//
//import java.util.HashMap;
//import java.util.Scanner;
//import java.util.TreeSet;
//
//public class P1187D {
//
//	private static int t;
//	private static int n;
//	private static int[] a;
//	private static int[] b;
//	private static int[] ug;
//	
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		t = in.nextInt();
//		out:
//		while(t-->0) {
//			n = in.nextInt();
//			a = new int[n];
//			int[] count = new int[n+1];
//			for(int i=0; i<n; i++) {
//				a[i] = in.nextInt();
//				count[a[i]]++;
//			}
//			b = new int[n];
//			for(int i=0; i<n; i++) {
//				b[i] = in.nextInt();
//				count[b[i]]--;
//			}
//			
//			for(int x: count) {
//				if (x!=0) {
//					System.out.println("NO");
//					continue out;
//				}
//			}
//			
//			int segSize = 2*(int)Math.pow(2, Math.ceil(Math.log10(n+1)/Math.log10(2)))-1;
//			ug = new int[segSize];
//			
//			for(int i=0; i<n; i++) {
//				count[b[i]]++;
//				if (get(0, a[i]-1, 0, n, 0)!=0) {
//					System.out.println("NO");
//					continue out;
//				}
//				if (count[a[i]]!=0) {
//					count[a[i]]--;
//				} else {
//					update(a[i], 0, n, 0);
//				}
//			}
//			System.out.println("YES");
//		}
//	}
//
//	private static void update(int index, int low, int high, int pos) {
//		if (low==high) {
//			ug[pos] = 1;
//			return;
//		}
//		int mid = (low+high)/2;
//		if (index<=mid) {
//			update(index, low, mid, 2*pos+1);
//		} else {
//			update(index, mid+1, high, 2*pos+2);
//		}
//		ug[pos] = ug[2*pos+1]+ug[2*pos+2];
//	}
//
//	private static int get(int qlow, int qhigh, int low, int high, int pos) {
//		if (qhigh<low || high<qlow) {
//			return 0;
//		} else if (qlow<=low && qhigh>=high) {
//			return ug[pos];
//		} else {
//			int mid = (low+high)/2;
//			return get(qlow, qhigh, low, mid, 2*pos+1)+get(qlow, qhigh, mid+1, high, 2*pos+2);
//		}
//	}
//
//}
