package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class UVA11060 {

	private static int n;
	private static HashMap<String, ArrayList<String>> g;
	private static HashSet<String> mark;
	private static ArrayList<String> nodes;
	private static ArrayList<String> rt;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int z=0;
		while (in.hasNext()) {
			z++;
			n = in.nextInt();
			g = new HashMap<>();
			nodes = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				String node = in.next();
				nodes.add(node);
				g.put(node, new ArrayList<String>());
			}

			int m = in.nextInt();
			for (int i = 0; i < m; i++) {
				String u = in.next();
				String v = in.next();
				g.get(v).add(u);
			}
			mark = new HashSet<>();
			rt = new ArrayList<>();
			for(String node: nodes) {
				if (!mark.contains(node)) {
					dfs(node);
				}
			}
			System.out.print("Case #"+z+": Dilbert should drink beverages in this order: ");
			for(int i=0; i<rt.size(); i++) {
				System.out.print(rt.get(i)+(i!=rt.size()-1?" ":""));
			}
			System.out.println(".\n");
		}
	}

	private static void dfs(String u) {
		mark.add(u);
		
		for(String v: g.get(u)) {
			if (mark.contains(v)) continue;
			dfs(v);
		}
		rt.add(u);
	}

}
//3
//vodka
//wine
//beer
//2
//wine vodka
//beer wine
//5
//wine
//beer
//rum
//apple-juice
//cachaca
//6
//beer cachaca
//apple-juice beer
//apple-juice rum
//beer rum
//beer wine
//wine cachaca
//10
//cachaca
//rum
//apple-juice
//tequila
//whiskey
//wine
//vodka
//beer
//martini
//gin
//11
//beer whiskey
//apple-juice gin
//rum cachaca
//vodka tequila
//apple-juice martini
//rum gin
//wine whiskey
//apple-juice beer
//beer rum
//wine vodka
//beer tequila
