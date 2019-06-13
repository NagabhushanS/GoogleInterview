package AdvancedDS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ChefLCA {

	private static class LCA {
		private HashMap<Integer, ArrayList<Integer>> g;
		private int[][] level;
		private HashMap<Integer, Integer> map;
		private int count;
		private int[][][] P;
		private int n, m;
		
		public LCA(HashMap<Integer, ArrayList<Integer>> g) {
			this.g=g;
			n = g.size();
			m = (int)Math.ceil(Math.log10(n)/Math.log10(2));
			level = new int[10][n];
			P = new int[10][n][m];
			count=0;
			map = new HashMap<Integer, Integer>();
			preprocess();
		}
		
		public void preprocess() {
			for(int i=0; i<10; i++) {
				for(int j=0; j<n; j++) {
					for(int k=0; k<m; k++) {
						P[i][j][k]=-1;
					}
				}
			}
		}
		
		public int query(int r, int u, int v) {
			int index = 0;
			if (!map.containsKey(r)) {
				index = count;
				map.put(r, count);
				process(r, count);
				count++;
			} else {
				index = map.get(r);
			}
			
			if (level[index][u]<level[index][v]) {
				int temp = u;
				u = v;
				v= temp;
			}
			
			int logHeight = (int)Math.ceil(Math.log10(level[index][u])/Math.log10(2));
			
			for(int i=logHeight; i>=0; i--) {
				if (level[index][u]-(1<<i)>=level[index][v]) {
					u = P[index][u][i];
				}
			}
			
//			System.out.println(level[index][u]==level[index][v]);
			if (u==v) {
				return u;
			}
			
			for(int i=logHeight; i>=0; i--) {
				int p = P[index][u][i];
				int q = P[index][v][i];
				if (p!=-1 && p!=q) {
					u = p;
					v = q;
				}
			}
			
			return P[index][u][0];
			
		}
		
		public void process(int r, int index) {
			dfs(r, -1, index);
			
			for(int j=1; j<m; j++) {
				for(int i=0; i<n; i++) {
					if (P[index][i][j-1]!=-1) {
						P[index][i][j]=P[index][P[index][i][j-1]][j-1];
					}
				}
			}
		}
		
		public void dfs(int u, int p, int index) {
			P[index][u][0]=p;
			if (p!=-1) {
				level[index][u]=level[index][p]+1;
			}
			
			for(int v: g.get(u)) {
				if (v!=p) {
					dfs(v, u, index);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		HashMap<Integer, ArrayList<Integer>> g = new HashMap<>();
		for(int i=0; i<n; i++) {
			g.put(i, new ArrayList<Integer>());
		}
		
		for(int i=0; i<n-1; i++) {
			int p = in.nextInt()-1;
			int q = in.nextInt()-1;
			g.get(p).add(q);
			g.get(q).add(p);
		}
		
		LCA lca = new LCA(g);
		
		int q = in.nextInt();
		for(int i=0; i<q; i++) {
			int r = in.nextInt()-1;
			int a = in.nextInt()-1;
			int b = in.nextInt()-1;
			System.out.println(lca.query(r, a, b)+1);
		}

	}

}
