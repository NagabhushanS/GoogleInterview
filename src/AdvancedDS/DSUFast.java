package AdvancedDS;

class DSURecap{
	private int[] par;

	private int[] size; //height
	private int count;
	private int n;

	public DSURecap(int n){
		par= new int[n];
		for(int i=0; i<n; i++){
			par[i] = i;
		}
		count=n;
		this.n=n;
	}

	public boolean connected(int u, int v){
		return root(u)==root(v);
	}

	private int root(int u){
		while(par[u]!=u){
			par[u] = par[par[u]]; //path compression
			u = par[u];
		}
		return u;
	}

	public void union(int u, int v){
		int p = root(u);
		int q = root(v);
		//weighted disjoint set data structure --> O(logn)
		if (size[p] > size[q]) {
			par[q] = p;
			size[p]+=size[q];
		} else {
			par[p] = q;
			size[q]+=size[p];
		}
	}
}


public class DSUFast {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
