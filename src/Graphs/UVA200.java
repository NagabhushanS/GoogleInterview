package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Scanner;

public class UVA200 {

	private static int n;
	private static HashMap<Character, ArrayList<Character>> g;
	private static HashSet<Character> mark;
	private static ArrayList<Character> topo;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();
		while(true) {
			String s = in.next();
			if (s.equals("#")) break;
			list.add(s);
		}
		g = new HashMap<>();
		for(int i=0; i<list.size()-1; i++) {
			//list[i]<list[i+1]
			for(int j=0; j<Math.min(list.get(i).length(), list.get(i+1).length()); j++) {
				if (list.get(i).charAt(j)!=list.get(i+1).charAt(j)) {
					if (!g.containsKey(list.get(i).charAt(j))) {
						g.put(list.get(i).charAt(j), new ArrayList<>());
					}
					g.get(list.get(i).charAt(j)).add(list.get(i+1).charAt(j));
					break;
				}
			}
		}
		topo = new ArrayList<>();
		mark = new HashSet<>();
		for(Entry<Character, ArrayList<Character>> e: g.entrySet()) {
			if (!mark.contains(e.getKey()))
					dfs(e.getKey());
		}
		
		for(int i=topo.size()-1; i>=0; i--) {
			System.out.print(topo.get(i));
		}
		System.out.println();
	}
	
	private static void dfs(char c) {
		mark.add(c);
//		System.out.println("C "+c);
		if (g.get(c)!=null)
			for(char d: g.get(c)) {
				if (mark.contains(d)) continue;
				dfs(d);
			}
		
		topo.add(c);
	}

}
