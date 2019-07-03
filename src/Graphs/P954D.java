package Graphs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P954D {

	private static int n, m, s, t;
	private static HashMap<Integer, HashSet<Integer>> g;
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		s = in.nextInt()-1;
		t = in.nextInt()-1;
		g = new HashMap<>();
		for(int i=0; i<n; i++) g.put(i, new HashSet<>());
		
		for(int i=0; i<m; i++) {
			int p = in.nextInt()-1;
			int q = in.nextInt()-1;
			g.get(p).add(q);
			g.get(q).add(p);
		}
		
		int[] d1 = new int[n];
		int[] d2 = new int[n];
		
		Arrays.fill(d1, (int)1e9);
		Arrays.fill(d2, (int)1e9);
		

		boolean[] mark = new boolean[n];
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(s);
		mark[s] = true;
		d1[s]=0;
		
		while(!queue.isEmpty()) {
			int u = queue.poll();
			
			for(int v: g.get(u)) {
				if (mark[v]) continue;
				queue.add(v);
				mark[v]=true;
				d1[v]=d1[u]+1;
			}
		}
		
		Arrays.fill(mark, false);
		queue.add(t);
		mark[t]=true;
		d2[t]=0;
		
		while(!queue.isEmpty()) {
			int u = queue.poll();
			
			for(int v: g.get(u)) {
				if (mark[v]) continue;
				queue.add(v);
				mark[v]=true;
				d2[v]=d2[u]+1;
			}
		}
		
		
		int ans=0;
		for(int i=0; i<n; i++) {
			for(int j=i+1; j<n; j++) {
				if (g.get(i).contains(j)) continue;
				if (d1[i] + d2[j] +1 >= d1[t] && d1[j]+d2[i]+1>=d1[t]) ans++;
			}
		}
		System.out.println(ans);
	}

}
