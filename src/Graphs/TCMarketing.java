package Graphs;

import java.util.ArrayList;
import java.util.HashMap;

public class TCMarketing {

	HashMap<Integer, ArrayList<Integer>> g;
	boolean[] mark;
	boolean[] color;
	int count=0;
	boolean isBipartite = true;
	
	public long howMany(String[] compete) {
		int n = compete.length;
		g = new HashMap<>();
		mark = new boolean[n];
		for(int i=0; i<n; i++) {
			g.put(i, new ArrayList<>());
		}
		for(int i=0; i<n; i++) {
			String[] s = compete[i].split("\\s");
			int[] si = new int[s.length];
			for(int j=0; j<si.length; j++) {
				si[j] = Integer.parseInt(s[j]);
			}
			for(int j=0; j<si.length; j++) {
				g.get(i).add(si[j]);
				g.get(si[j]).add(i);
			}
					
		}
		for(int i=0; i<n; i++) {
			if (!mark[i]) {
				count++;
				dfs(i, -1);
			}
			if (isBipartite==false) {
				return -1;
			}
			
		}
		System.out.println(count+": Count");
		
		return 1<<count;
	}
	
	public void dfs(int i, int p) {
		System.out.println(i+" "+p);
		mark[i]=true;
		if (p==-1) {
			color[i]=true;
		} else {
			color[i]=!color[p];
		}
		
		for(int k: g.get(i)) {
			if (!mark[k]) {
				dfs(k, i);
			} else if (color[k]==color[i]) {
				isBipartite=false;
				return;
			}
		}
	}
	
	public static void main(String[] args) {
//		System.out.println(how)
	}

}
