package AdvancedDS;

class DSU {
	int[] id;
	int[] size;
	
	public DSU (int n){
		id = new int[n];
		size = new int[n];
		for(int i=0; i<n; i++) {
			id[i] = i;
		}
	}
	
	public boolean connected(int u, int v) {
		return root(u)==root(v);
	}
	
	public int root(int u) {
		
		while(u!=id[u]) {
			id[u] = id[id[u]]; //path compression
			u = id[u];
		}
		
		return u;
	}
	
	public void union(int u, int v) {
		int i = root(u);
		int j = root(v);
		//weighted DSU
		if (size[i]>size[j]) {
			id[j]=i;
			size[i]+=size[j];
		} else {
			id[i]=j;
			size[j]+=size[i];
		}
		
	}
	
}

public class FastestDSU {

	public static void main(String[] args) {
		

	}
	
	

}
