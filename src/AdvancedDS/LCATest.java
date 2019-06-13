package AdvancedDS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class LCA {
	
	HashMap<Integer, ArrayList<Integer>> g;
	int[] level;
	int[][] P;
	int m;
	int n;
	
	public LCA(HashMap<Integer, ArrayList<Integer>> g) {
		this.g = g;
		n = g.size();
		m = (int)Math.ceil(Math.log10(n)/Math.log10(2));
		level = new int[n];
		P = new int[n][m];
		preprocess();
	}

	private void preprocess() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				P[i][j]=-1;
			}
		}
		
		dfs(0, 0);
		
		for(int j=1; j<m; j++) {
			for(int i=0; i<n; i++) {
				if (P[i][j-1]!=-1) {
					P[i][j] = P[P[i][j-1]][j-1];
				}
			}
		}
		
	}
	
	private void dfs(int u, int p) {
		level[u]=1+level[p];
		P[u][0] = p;
		
		for(int v: g.get(u)) {
			if (v!=p) {
				dfs(v, u);
			}
		}
		
	}
	
	public int lca(int u, int v) {
		if (level[u]<level[v]) {
			int temp = u;
			u = v;
			v = temp;
		}
		
		int logHeight = (int)Math.ceil(Math.log10(level[u])/Math.log10(2));
		
		for(int i=logHeight; i>=0; --i) {
			if (level[u]-(1<<i)>=level[v]) {
				u = P[u][i];
			}
		}
		
		if (u==v) {
			return u;
		}
		
		for(int i=logHeight; i>=0; i--) {
			int p = P[u][i];
			int q = P[v][i];
			if (p!=-1 && p!=q) {
				u = p;
				v = q;
			}
		}
		
		return P[u][0];
	}
	
	
}

public class LCATest {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n;
		n = in.nextInt();
		
		HashMap<Integer, ArrayList<Integer>> g = new HashMap<>();
		for(int i=0; i<n; i++) {
			g.put(i, new ArrayList<>());
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
			int u = in.nextInt()-1;
			int v = in.nextInt()-1;
			System.out.println(lca.lca(u, v)+1);
		}

	}

}
