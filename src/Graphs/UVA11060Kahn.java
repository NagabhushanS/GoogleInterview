package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class UVA11060Kahn {

	private static class Node implements Comparable<Node> {
		private int degree;
		private int i;
		
		public Node(int a, int b) {
			i=a;
			degree=b;
		}
		
		public int compareTo(Node node) {
			return -i+node.i;
		}
	}
	
	private static int n;
	private static HashMap<Integer, ArrayList<Integer>> g;
	private static ArrayList<String> rt;
	private static HashMap<Integer, String> revMap;
	private static HashMap<String, Integer> map;
	private static int[] degree;
	private static boolean[] mark;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int z=0;
		while (in.hasNext()) {
			z++;
			n = in.nextInt();
			g = new HashMap<>();
			degree = new int[n];
			map = new HashMap<>();
			revMap = new HashMap<>();
			for (int i = 0; i < n; i++) {
				String node = in.next();
//				System.out.println(node);
				map.put(node, i);
				revMap.put(i, node);
				g.put(i, new ArrayList<Integer>());
			}

			int m = in.nextInt();
			for (int i = 0; i < m; i++) {
				int u = map.get(in.next());
				int v = map.get(in.next());
//				g.get(v).add(u);
//				degree[u]++;
				g.get(u).add(v);
				degree[v]++;
			}
			mark = new boolean[n];
			rt = new ArrayList<>();
			
			boolean[] covered = new boolean[n];
			
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			for(int i=0; i<n; i++) {
				if (degree[i]==0) pq.add(i);
			}
			
			while(!pq.isEmpty()) {
//				System.out.println(pq);
				int u = pq.poll();
				rt.add(revMap.get(u));
				
				for(int v: g.get(u)) {
					degree[v]--;
					if (degree[v]==0)
						pq.add(v);
				}
			}
			
			System.out.print("Case #"+z+": Dilbert should drink beverages in this order: ");
			for(int i=0; i<rt.size(); i++) {
				System.out.print(rt.get(i)+(i!=rt.size()-1?" ":""));
			}
			System.out.println(".\n");
		}
	}


}
//5
//a b c d e
//4
//b a
//b c
//d b
//d c
