package AdvancedDS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class TimusTree2 {
	private static class Task {
		private HashMap<Integer, ArrayList<Integer>> g;
		private int[] depth;
		private int n;
		private int diameter;
		private int diameterRoot;
		private int[] level;
		
		public Task(HashMap<Integer, ArrayList<Integer>> g) {
			this.g=g;
			n = g.size();
			diameter=0;
			diameterRoot=-1;
			depth = new int[n];
			level = new int[n];
			dfs1(0, -1);
			dfs2(diameterRoot, -1);
			int max=0;
			for(int i=0; i<n; i++) {
				if (level[i]>level[max]) {
					max=i;
				} 
			}
			int nextMax=0;
			level[max]=-1;
			for(int i=0; i<n; i++) {
				if (level[i]>level[nextMax]) {
					nextMax=i;
				}
			}
			
		}
		
		public void dfs1(int u, int p) {
			
			depth[u]=0;
			ArrayList<Integer> list = new ArrayList<>();
			
			for(int v: g.get(u)) {
				if (v!=p) {
					dfs1(v, u);
					list.add(depth[v]);
					depth[u] = Math.max(depth[u], 1+depth[v]);
					
				}
			}
			Collections.sort(list);
			if (list.size()==1) {
				if (1+list.get(0)>diameter) {
					diameter=1+list.get(0);
					diameterRoot=u;
				}
			} else if (list.size()>1) {
				int z = list.size();
				if (list.get(z-1)+list.get(z-2)+1>diameter) {
					diameter=list.get(z-1)+list.get(z-2)+1;
					diameterRoot = u;
				}
			}
		}
		
		public void dfs2(int u, int p) {
			if (p!=-1)
				level[u]=level[p]+1;
			for(int v: g.get(u)) {
				if (v!=p) {
					dfs2(v, u);
				}
			}
		}
		
		public int query(int u, int d) {
			return 0;
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int q = in.nextInt();
		
		HashMap<Integer, ArrayList<Integer>> g = new HashMap<>();
		for(int i=0; i<n; i++) {
			g.put(i, new ArrayList<>());
		}
		
		for(int i=0; i<n-1; i++) {
			int u = in.nextInt()-1;
			int v = in.nextInt()-1;
			g.get(u).add(v);
			g.get(v).add(u);
		}
		
		for(int i=0; i<q; i++) {
			int p = in.nextInt()-1;
			int d = in.nextInt();
		}
		
		Task task = new Task(g);

	}

}
