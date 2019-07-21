package Strings;

import java.util.Scanner;

public class UVA10010 {

	private static int n, m;
	private static char[][] g;
	private static int[] dx = {1, 1, -1, -1, 0, 0, 1, -1};
	private static int[] dy = {1, -1, -1, 1, 1, -1, 0, 0};
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		int z=0;
		while(t-->0) {
			z++;
			if (z!=1) {
				System.out.println();
			}
			n = in.nextInt();
			m = in.nextInt();
			g = new char[n][m];
			for(int i=0; i<n; i++) {
				g[i] = in.next().toLowerCase().toCharArray();
			}
			int words = in.nextInt();
			next:
			for(int x=0; x<words; x++) {
				String p = in.next().toLowerCase();
				for(int i=0; i<n; i++) {
					for(int j=0; j<m; j++) {
						for(int k=0; k<8; k++) {
							boolean found=true;
							for(int d=0; d<p.length(); d++) {
								int ni = i+dx[k]*d;
								int nj = j+dy[k]*d;
								if (ni<0 || ni>=n || nj<0 || nj>=m || g[ni][nj]!=p.charAt(d)) {
									found = false;
									break;
								}
							}
							if (found) {
								System.out.println((i+1)+" "+(j+1));
								continue next;
							}
						}
					}
				}
			}
		}
	}
	
	private static boolean search(String p, int i, int j, int d) {
		
		for(int k=0; k<8; k++) {
			int in = i+dx[k];
			int jn = j+dy[k];
			if (in<0 || in>=n || in<0 || jn>=m) continue;
			if (g[in][jn]==p.charAt(d)) {
				if (search(p, in, jn, d+1)) return true;
			}
		}
		return false;
	}

}
