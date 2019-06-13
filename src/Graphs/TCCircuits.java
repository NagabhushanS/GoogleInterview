package Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TCCircuits {

	private class Edge{
		int u, v;  //u->v
		int w;
		
		public Edge(int a, int b, int c) {
			u=a;
			v=b;
			w=c;
		}
	}
	
	HashMap<Integer, ArrayList<Edge>> g = new HashMap<>();
	int[] dp;
	
	
	public int howLong(String[] conn, String[] cost) {
		int n = conn.length;
		dp = new int[n];
		for(int i=0; i<n; i++) {
			g.put(i, new ArrayList<>());
		}
		for(int i=0; i<cost.length; i++) {
			if (cost[i].equals("")) continue;
			String[] sconn = conn[i].split("\\s+");
			String[] scost = cost[i].split("\\s+");
			for(int j=0; j<sconn.length; j++) {
				Edge e = new Edge(i, Integer.parseInt(sconn[j]), Integer.parseInt(scost[j]));
				g.get(i).add(e);
			}
		}
		Arrays.fill(dp, -1);
		
		int ans=0;
		for(int i=0; i<n; i++) {
			ans = Math.max(ans, solve(i));
		}
		return ans;
	}
	
	public int solve(int u) {
		if (dp[u]!=-1) return dp[u];
		
		int ans=0;
		for(Edge e: g.get(u)) {
			int v = e.v;
			ans = Math.max(ans, e.w+solve(v));
		}
		
		return dp[u]=ans;
	}

}
