package AdvancedDS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class P519E {
	private static class LCA2 {
		private HashMap<Integer, ArrayList<Integer>> g;
		private int[] level;
		private int[][] P;
		private int[] size;
		int n, m;

		public LCA2(HashMap<Integer, ArrayList<Integer>> g) {
			this.g = g;
			n = g.size();
			m = (int) Math.ceil(Math.log10(n) / Math.log10(2));
			P = new int[n][m];
			size = new int[n];
			level = new int[n];
			preprocess();
		}

		private void preprocess() {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					P[i][j] = -1;
				}
			}
			dfs(0, -1);

			for (int j = 1; j < m; j++) {
				for (int i = 0; i < n; i++) {
					if (P[i][j - 1] != -1)
						P[i][j] = P[P[i][j - 1]][j - 1];
				}
			}
		}

		private void dfs(int u, int p) {
			P[u][0] = p;
			if (p != -1)
				level[u] = 1 + level[p];
			size[u] = 1;

			for (int v : g.get(u)) {
				if (v != p) {
					dfs(v, u);
					size[u] += size[v];
				}
			}
		}

		public int lca(int u, int v) {
			if (level[u] < level[v]) {
				int temp = u;
				u = v;
				v = temp;
			}

			int logHeight = (int) Math.ceil(Math.log10(level[u]) / Math.log10(2));

			for (int i = logHeight; i >= 0; i--) {
				if (level[u] - (1 << i) >= level[v]) {
					u = P[u][i];
				}
			}

			if (u == v) {
				return u;
			}

			for (int i = logHeight; i >= 0; i--) {
				int p = P[u][i];
				int q = P[v][i];
				if (p != -1 && p != q) {
					u = p;
					v = q;
				}
			}

			return P[u][0];
		}

		public int query(int u, int v) {
			if (u==v) {
				return n;
			}
			if (level[u] < level[v]) {
				int temp = u;
				u = v;
				v = temp;
			}
			int lca = lca(u, v);
			int sumOfLengths = level[v] + level[u] - 2 * level[lca];
			if (sumOfLengths % 2 != 0) {
				return 0;
			}
			int toJump = level[u] - sumOfLengths / 2;
			int logHeight = (int) Math.ceil(Math.log10(level[u]) / Math.log10(2));

			int tempU = u;
			for (int i = logHeight; i >= 0; i--) {
				if (level[u] - (1 << i) >= toJump) {
					u = P[u][i];
				}
			}
			int midParent = u;
			toJump++;
			u = tempU;
			for (int i = logHeight; i >= 0; i--) {
				if (level[u] - (1 << i) >= toJump) {
					u = P[u][i];
				}
			}
			int sub = 0;
			int add=0;
			if (level[tempU] == level[v]) {
				for (int i = logHeight; i >= 0; i--) {
					if (level[v] - (1 << i) >= toJump) {
						v = P[v][i];
					}
				}
				sub = size[v];
				add = n-size[lca];
			}
			int childOfMidParent = u;
			return size[midParent] - size[childOfMidParent] - sub + add;
		}

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		HashMap<Integer, ArrayList<Integer>> g = new HashMap<>();
		for (int i = 0; i < n; i++) {
			g.put(i, new ArrayList<>());
		}
		for (int i = 0; i < n - 1; i++) {
			int p = in.nextInt() - 1;
			int q = in.nextInt() - 1;
			g.get(p).add(q);
			g.get(q).add(p);
		}

		LCA2 lca = new LCA2(g);
		int Q = in.nextInt();
		for (int i = 0; i < Q; i++) {
			int p = in.nextInt() - 1;
			int q = in.nextInt() - 1;
			System.out.println(lca.query(p, q));
		}

	}

}
