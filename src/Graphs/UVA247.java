package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Scanner;

public class UVA247 {

	private static int n, m;
	private static HashMap<String, ArrayList<String>> g;
	private static HashMap<String, ArrayList<String>> gr;
	private static ArrayList<String> porg;
	private static ArrayList<String> nodes;
	private static HashSet<String> nodesSet;
	private static HashSet<String> mark;
	private static HashMap<Integer, ArrayList<String>> id;
	private static int cc;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int z=1;
		while(true) {
			n = in.nextInt();
			m = in.nextInt();
			if (n==0 && m==0) break;
			g = new HashMap<>();
			gr = new HashMap<>();
			nodes = new ArrayList<>();
			nodesSet = new HashSet<>();
			porg = new ArrayList<>();
			mark = new HashSet<>();
			for(int i=0; i<m; i++) {
				String u = in.next();
				String v = in.next();
				if (g.get(u)==null) {
					g.put(u, new ArrayList<>());
				}
				if (gr.get(v)==null) {
					gr.put(v, new ArrayList<>());
				}
				if (!nodesSet.contains(u))
					nodes.add(u);
				if (!nodesSet.contains(v))
					nodes.add(v);
				g.get(u).add(v);
				gr.get(v).add(u);
			}
			for(String node: nodes) {
				if (!mark.contains(node)) {
					dfs(gr, node, false);
				}
			}
			mark.clear();
			id = new HashMap<>();
			cc=0;
			for(int i=porg.size()-1; i>=0; i--) {
				if (mark.contains(porg.get(i))) continue;
				id.put(cc, new ArrayList<>());
				dfs(g, porg.get(i), true);
				cc++;
			}
			System.out.println("Calling circles for data set "+z+":");
			for(Entry<Integer, ArrayList<String>> e: id.entrySet()) {
				int count = e.getValue().size();
				for(String part: e.getValue()) {
					count--;
					System.out.print(part+(count!=0?", ":""));
				}
				System.out.println();
			}
			System.out.println();
			z++;
			
		}
	}

	private static void dfs(HashMap<String, ArrayList<String>> g, String u, boolean ccFlag) {
		if (ccFlag) {
			id.get(cc).add(u);
		}
		
		mark.add(u);
		
		if (g.get(u)!=null)
			for(String v: g.get(u)) {
				if (!mark.contains(v)) {
					dfs(g, v, ccFlag);
				}
			}
		if (!ccFlag) porg.add(u);
	}

}
