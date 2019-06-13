package AdvancedDS;

class QuickFind{
	private int[] id;
	private int count;
	
	public QuickFind(int n) {
		id = new int[n];
		for(int i=0; i<n; i++) {
			id[i]=i;
		}
	}
	
	public boolean connected(int u, int v) {
		return id[u]==id[v];
	}
	
	public void union(int u, int v) {
		int p = id[u];
		int q = id[v];
		
		for(int i=0; i<id.length; i++) {
			if (id[i]==p) {
				id[i]=q;
			}
		}
	}
}

public class QuickFindTest {

	public static void main(String[] args) {

	}

}
