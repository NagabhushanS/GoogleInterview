package AdvancedDS;

import java.util.ArrayList;

class QuickFind2 {
	private ArrayList<Integer> id;
	private int count;
	private int nodes;

	public QuickFind2(int n){
		id = new ArrayList<>(n);
		for(int i=0; i<n; i++) {
			id.add(i);
		}
		nodes = n;
		count = n;
	}

	public void addNode(){
		id.add(nodes);
		nodes++;
		count++;
	}

	public boolean connected(int u, int v){
		return id.get(u)==id.get(v);
	}

	public void union(int u, int v){
		//assign id[u] to every node having id as id[v]
		int uId = id.get(u);
		for(int i=0; i<nodes; i++){
			if (id.get(i)==id.get(v)){
				id.set(i, uId);
			}
		}
	
	}

}


public class QuickFindRecap {

	public static void main(String[] args) {
		QuickFind2 dsu = new QuickFind2(100);
		dsu.union(1, 2);
		dsu.union(2, 4);
		System.out.println(dsu.connected(1, 3));

	}

}
