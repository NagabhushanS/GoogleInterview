package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

public class P1176E {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while(t-->0) {
			int n = in.nextInt();
			int m = in.nextInt();
			HashMap<Integer, SortedSet<Integer>> g = new HashMap<>();
			for(int i=0; i<n; i++) {
				g.put(i, new TreeSet<>());
			}
			
			for(int i=0; i<m; i++) {
				int p = in.nextInt()-1;
				int q = in.nextInt()-1;
				g.get(p).add(q);
				g.get(q).add(p);
			}
	
			ArrayList<Integer> ans1 = bfs(true, g, n);
			ArrayList<Integer> ans2 = bfs(false, g, n);
			if (ans1.size()<ans2.size()) {
				System.out.println(ans1.size());
				for(int x: ans1) {
					System.out.print((x+1)+" ");
				}
				System.out.println();
			} else {
				System.out.println(ans2.size());
				for(int x: ans2) {
					System.out.print((x+1)+" ");
				}
				System.out.println();
			}
		}
	}
	
	public static ArrayList<Integer> bfs(boolean flag, HashMap<Integer, SortedSet<Integer>> g, int n) {
		ArrayList<Integer> ans = new ArrayList<>();
		boolean[] color = new boolean[n];
		boolean[] mark = new boolean[n];
		color[0]=flag;
		Stack<Integer> queue = new Stack<>();
		queue.add(0);
		mark[0]=true;
		ans.add(0);
		while(!queue.isEmpty()) {
			int u = queue.pop();
			
			for(int v: g.get(u)) {
				if (mark[v]) continue;
				queue.add(v);
				mark[v]=true;
				color[v]=!color[u];
				if (color[v]) ans.add(v);
			}
		}
		return ans;
	}

}
