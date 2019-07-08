package Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class UVA124 {

	private static int n;
	private static HashMap<Integer, ArrayList<Integer>> g;
	private static int[] degree;
	private static char[] nodes;
	private static int[] map;
	private static char[] buffer;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int z=0;
		while(in.hasNext()) {
			if (z!=0) {
				System.out.println();
			}
			z++;
			String all = in.nextLine();
			String[] nodeStrings = all.split("\\s+");
			n = nodeStrings.length;
			g = new HashMap<>();
			degree = new int[n];
			nodes = new char[n];
			map = new int[256];
			for(int i=0; i<n; i++) {
				nodes[i] = nodeStrings[i].charAt(0);
			}
			Arrays.sort(nodes);
			for(int i=0; i<n; i++) {
				map[nodes[i]]=i;
			}
			for(int i=0; i<n; i++) {
				g.put(i, new ArrayList<>());
			}
			String relations = in.nextLine();
			String[] rels = relations.split("\\s+");
			for(int i=0; i<rels.length-1; i+=2) {
				char c1 = rels[i].charAt(0);
				char c2 = rels[i+1].charAt(0);
				g.get(map[c1]).add(map[c2]);
				degree[map[c2]]++;
			}
			boolean[] mark = new boolean[n];
			buffer = new char[n];
//			run(mark, 0);
			run2(mark, 0);
			
		}
	}
	
	private static void run(boolean[] mark, int length) {
		if (length==n) {
			System.out.println(new String(buffer).trim());
			return;
		}
		
		for(int i=0; i<n; i++) {
			if (!mark[i]) {
				mark[i]=true;
				if (!check(mark, i)) {
					mark[i]=false;
					continue;
				}
				buffer[length] = nodes[i];
				run(mark, length+1);
				mark[i]=false;
			}
		}
	}
	
	private static boolean check(boolean[] mark, int i) {
		
		for(int v: g.get(i)) {
			if (mark[v]) return false;
		}
		return true;
	}
	
	
	private static void run2(boolean[] mark, int length) {
		if (length==n) {
			System.out.println(new String(buffer).trim());
			return;
		}
		
		for(int i=0; i<n; i++) {
			if (degree[i]==0 && !mark[i]) {
				mark[i] = true;
				buffer[length]=nodes[i];
				for(int v: g.get(i)) {
					degree[v]--;
				}
				run2(mark, length+1);
				for(int v: g.get(i)) {
					degree[v]++;
				}
				mark[i] = false;
			}
		}
	}

}
