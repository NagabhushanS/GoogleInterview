package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class P1153D {

	static int n;
	static HashMap<Integer, ArrayList<Integer>> g;
	static int[] op;
	static int k;
	static int start=1;
	static int end;
	static int[] val;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		g = new HashMap<>();
		for(int i=0; i<n; i++) {
			g.put(i, new ArrayList<>());
		}
		op = new int[n];
		val = new int[n];
		for(int i=0; i<n; i++) {
			op[i] = in.nextInt();
		}

		HashSet<Integer> set = new HashSet<>();
		
		for(int i=1; i<n; i++) {
			int p = in.nextInt()-1;
			set.add(p);
			g.get(i).add(p);
			g.get(p).add(i);
		}
		
		k = n-set.size();
//		System.out.println(k);
		end=k;
		
		System.out.println(dfs(0, 0));
		
//		for(int z: val) {
//			System.out.print(z+" ");
//		}
//		System.out.println();
	}

	public static int dfs(int u, int p) {
		
		boolean isLeaf=true;
		int uVal = op[u]==0?(int)1e9:0;
		for(int v: g.get(u)) {
			if (v==p) continue;
			isLeaf=false;
			int z = dfs(v, u);
			if (op[u]==0) {
				uVal = Math.min(uVal, z);
			} else {
				uVal = Math.max(uVal, z);
			}
		}
		if (isLeaf) {
			if (op[p]==0) {
				val[u]=end--;
//				System.out.println("Leaf: "+(u+1)+" "+val[u]);
				return val[u];
			} else {
				val[p]=start++;
//				System.out.println("Leaf "+(u+1)+" "+val[u]);
				return val[u];
			}
			
		}
		return val[u]=uVal;
		
	}
}
