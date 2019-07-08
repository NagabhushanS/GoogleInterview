package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UVA10505 {

	private static int n;
	private static HashMap<Integer, ArrayList<Integer>> g;
	private static boolean[] mark;
	private static boolean[] color;
	private static int white, black;
	private static boolean error;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for(int z=0; z<t; z++) {
			n = in.nextInt();
			g = new HashMap<>();
			for(int i=0; i<n; i++) g.put(i, new ArrayList<>());
			for(int i=0; i<n; i++) {
				int p = in.nextInt();
				for(int j=0; j<p; j++) {
					int en = in.nextInt()-1;
					g.get(i).add(en);
					System.out.println(z);
					g.get(en).add(i);
					
				}
			}
			mark = new boolean[n];
			color = new boolean[n];
			long ans = 0;
			for(int i=0; i<n; i++) {
				if (!mark[i]) {
					white = 0;
					black = 1;
					error=false;
//					System.out.println("NEW");
					dfs(i);
					if (!error)
						ans += Math.max(white, black);
				}
			}
			System.out.println(ans);
		}
		

	}

	private static void dfs(int u) {
//		System.out.println("DFS "+u);
		mark[u] = true;
		
		for(int v: g.get(u)) {
			if (mark[v]) {
				if (color[v]==color[u]) {
					error=true;
				}
				continue;
			}
			color[v] = !color[u];
			if (color[v]) white++;
			else black++;
			dfs(v);
		}
		
	}

}
