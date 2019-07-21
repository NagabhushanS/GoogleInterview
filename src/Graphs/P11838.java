package Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class P11838 {

	private static int n;
	private static int m;
	private static HashMap<Integer, ArrayList<Integer>> g;
	private static HashMap<Integer, ArrayList<Integer>> gr;
	private static ArrayList<Integer> por;
	private static boolean[] mark;
	private static int[] id;
	private static int components;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(true) {
			n = in.nextInt();
			m = in.nextInt();
			if (n==0 && m==0) break;
			g = new HashMap<>();
			gr = new HashMap<>();
			for(int i=0; i<n; i++) {
				g.put(i, new ArrayList<>());
				gr.put(i, new ArrayList<>());
			}
			for(int i=0; i<m; i++) {
				int p = in.nextInt()-1;
				int q = in.nextInt()-1;
				int two = in.nextInt();
				if (two==1) {
					g.get(p).add(q);
					gr.get(q).add(p);	
				} else {
					g.get(q).add(p);
					g.get(p).add(q);
					gr.get(p).add(q);
					gr.get(q).add(p);
				}
			}
			
			mark = new boolean[n];
			por = new ArrayList<>();
			id = new int[n];
			components = 0;
			
			for(int i=0; i<n; i++) {
				if (!mark[i]) {
					dfs(i, gr, false);
				}
			}
			
			Arrays.fill(mark, false);
			
			for(int i=por.size()-1; i>=0; i--) {
				int u = por.get(i);
				if (!mark[u]) {
					dfs(u, g, true);
					components++;
				}
				
			}
			System.out.println(components==1?1:0);
		}
	}

	private static void dfs(int u, HashMap<Integer, ArrayList<Integer>> gr, boolean b) {
		mark[u] = true;
		if (b) {
			id[u] = components;
		}
		
		for(int v: gr.get(u)) {
			if (mark[v]) continue;
			dfs(v, gr, b);
		}
		
		if (!b) {
			por.add(u);
		}
		
	}

}
