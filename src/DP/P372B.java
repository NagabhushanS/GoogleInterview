//package DP;
//
//import java.util.Scanner;
//
//public class P372B {
//
//	private static int n, m;
//	private static int q;
//	private static int[][][][] dp = new int[41][41][41][41];
//	private static int[][] count = new int[41][41];
//	private static int[][] a;
//	
//	public static void main(String[] args) {
//		
//		Scanner in = new Scanner(System.in);
//		n = in.nextInt();
//		m = in.nextInt();
//		q = in.nextInt();
//		a = new int[n][m];
//		
//		for(int i=0; i<n; i++) {
//			String s = in.next();
//			for(int j=0; j<m; j++) {
//				a[i][j] = (s.charAt(j)-'0');
//			}
//		}
//		System.out.println("VALUE "+allZeroes(0, 0, 0, 1));
//		
//		for(int i=0; i<n; i++) {
//			for(int j=0; j<m; j++) {
//				for(int x=i; x<n; x++) {
//					for(int y=j; y<m; y++) {
//						if (allZeroes(i, j, x, y)==1) {
//							count[x][y]++;
//						}
//					}
//				}
//			}
//		}
//		
//		
//		for(int x=0; x<n; x++) {
//			for(int y=0; y<n; y++) {
//				for(int i=0; i<=x; i++) {
//					for(int j=0; j<=y; j++) {
//						count[x]
//					}
//				}
//			}
//		}
//		
////		for(int i=0; i<n; i++) {
////			for(int j=0; j<m; j++) {
////				System.out.print(a[i][j]+" ");
////			}
////			System.out.println();
////		}
//		
//		for(int i=0; i<n; i++) {
//			for(int j=0; j<m; j++) {
//				System.out.print(count[i][j]+" ");
//			}
//			System.out.println();
//		}
//		
////		int q = in.nextInt();
//		while(q-->0) {
//			int a = in.nextInt()-1;
//			int b = in.nextInt()-1;
//			int c = in.nextInt()-1;
//			int d = in.nextInt()-1;
//			System.out.println(count[c][d]-count[a][d]-count[c][b]+count[a][b]);
//		}
//
//	}
//	
//	private static int allZeroes(int i, int j, int x, int y) {
//		if (i>x || j>y) return 1;
//		//returns the number of rectanges from (i, j) to (x, y)
//		if (i==x && j==y) {
//			return a[i][j]==0?1:0;
//		}
//		
//		if (allZeroes(i, j, x-1, y)==1 && allZeroes(i, j, x, y-1)==1 && a[x][y]==0) return 1;
//		else return 0;
//	}
//	
//	private static int solve(int i, int j, int x, int y) {
//		if (i>x || j>y) return 0;
//		//returns the number of rectanges from (i, j) to (x, y)
//		if (i==x && j==y) {
//			return a[i][j]==0?1:0;
//		}
//		
//		return solve(i, j, x-1, y)+solve(i, j, x, y-1)+(a[x][y]==0?1:0)-solve(i, j, x-1, y-1);
//	}
//
//}
