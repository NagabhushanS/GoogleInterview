package Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class UVA11504 {

	private static int n, m;
	private static HashMap<Integer, ArrayList<Integer>> g;
	private static HashMap<Integer, ArrayList<Integer>> gR;
	private static ArrayList<Integer> por;
	private static boolean[] mark;
	private static int[] id;
	private static int comp;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		while(T-->0) {
			n = in.nextInt();
			m = in.nextInt();
			g = new HashMap<>();
			gR = new HashMap<>();
			for(int i=0; i<n; i++) {
				g.put(i, new ArrayList<>());
				gR.put(i, new ArrayList<>());
			}
			for(int i=0; i<m; i++) {
				int p = in.nextInt()-1;
				int q = in.nextInt()-1;
				g.get(p).add(q);
				gR.get(q).add(p);
			}
			
			por = new ArrayList<Integer>();
			mark = new boolean[n];
			id = new int[n];
			comp=0;
			
			for(int i=0; i<n; i++) {
				if (!mark[i]) {
					dfs(gR, i, false);
				}
			}
			
			Arrays.fill(mark, false);
			
			for(int i=por.size()-1; i>=0; i--) {
				if (!mark[por.get(i)]) {
					dfs(g, por.get(i), true);
					comp++;
				}
			}
			
			
			HashSet<Integer> ans = new HashSet<>();
			
			for(int i=0; i<n; i++) {
				for(int v: g.get(i)) {
					if (i==v) continue;
					if (id[i]!=id[v]) {
						ans.add(id[v]);
					}
				}
			}
			System.out.println(comp-ans.size());
		}
	}

	private static void dfs(HashMap<Integer, ArrayList<Integer>> graph, int u, boolean flag) {
		mark[u] = true;
		if (flag) {
			id[u] = comp;
		}
		
		for(int v: graph.get(u)) {
			if (!mark[v]) {
				dfs(graph, v, flag);
			}
		}
		
		if (!flag) {
			por.add(u);
		}
		
	}

}
