package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class AP {
	
	private HashMap<Integer, ArrayList<Integer>> g;
	private int[] num;
	private int[] low;
	private boolean[] mark;
	private int n;
	private Set<Integer> art;
	private int time;
	private int rootChild;
	
	public AP(HashMap<Integer, ArrayList<Integer>> g) {
		this.g=g;
		n = g.size();
		num = new int[n];
		low = new int[n];
		mark = new boolean[n];
		time=0;
		rootChild = 0;
	}
	
	public Set<Integer> getArticulationPoints() {
		//returns a list of articulation points
		//assuming graph is connected
		if (art!=null) return art;
		art = new HashSet<>();
		dfs(0, -1);
		if (rootChild>1) art.add(0);
		
		return art;
	}
	
	public int getArtCount() {
		if (art!=null) {
			return art.size();
		} else {
			return getArticulationPoints().size();
		}
	}
	
	private void dfs(int u, int p) {
		num[u] = low[u] = time++;
		mark[u] = true;
		
		for(int v: g.get(u)) {
			if (v==p) continue;
			if (!mark[v]) {
				dfs(v, u);
				if (u==0) rootChild++;
				if (u!=0 && low[v]>=num[u]) {
					art.add(u);
				}
				low[u] = Math.min(low[u], low[v]);
			} else {
				low[u] = Math.min(low[u], num[v]);
			}
		}
	}
}

public class ArticulationPoints {

	public static void main(String[] args) {

	}

}
