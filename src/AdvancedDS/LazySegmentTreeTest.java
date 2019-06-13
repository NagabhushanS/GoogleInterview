package AdvancedDS;

import java.util.Random;

class LazySegmentTree {
	int[] a;
	int[] seg;
	int[] lazy;
	int n;
	
	public LazySegmentTree(int[] a) {
		this.a = a;
		this.n = a.length;
		int m = (int)Math.ceil(Math.log10(n)/Math.log10(2));
		m = (int)(2*Math.pow(2, m)-1);
		seg = new int[m];
		lazy = new int[m];
		construct(0, n-1, 0);
	}
	
	public void construct(int low, int high, int pos) {
		if (low==high) {
			seg[pos]=a[low];
			return;
		}
		int mid = (low+high)/2;
		construct(low, mid, 2*pos+1);
		construct(mid+1, high, 2*pos+2);
		seg[pos] = Math.min(seg[2*pos+1], seg[2*pos+2]);
		return;
	}
	
	public void update(int qlow, int qhigh, int delta) {
		update(0, n-1, qlow, qhigh, delta, 0);
	}
	
	public void update(int low, int high, int qlow, int qhigh, int delta, int pos) {
		if (low>high) {
			System.out.println("WEIRED");
		}
		
		if (lazy[pos]!=0) {
			seg[pos]+=lazy[pos];
			if (low!=high) {
				lazy[2*pos+1]+=lazy[pos];
				lazy[2*pos+2]+=lazy[pos];
			}
			lazy[pos]=0;
		}
		
		if (qlow<=low && qhigh>=high) {
			seg[pos]+=delta;
			if (low!=high) {
				lazy[2*pos+1]+=delta;
				lazy[2*pos+2]+=delta;
			}
			return;
		} else if (qhigh<low || high<qlow) {
			return;
		}
		
		
		int mid = (low+high)/2;
		update(low, mid, qlow, qhigh, delta, 2*pos+1);
		update(mid+1, high, qlow, qhigh, delta, 2*pos+2);
		seg[pos]=Math.min(seg[2*pos+1], seg[2*pos+2]);
		return;
	}

	public int get(int qlow, int qhigh) {
		return get(0, n-1, qlow, qhigh, 0);
	}
	
	public int get(int low, int high, int qlow, int qhigh, int pos) {
		
		if (low>high) {
			System.out.println("WEIRED");
		}
		
		if (lazy[pos]!=0) {
			seg[pos]+=lazy[pos];
			if (low!=high) {
				lazy[2*pos+1] += lazy[pos];
				lazy[2*pos+2] += lazy[pos];
			}
			lazy[pos]=0;
		}
		
		if (qlow<=low && qhigh>=high) {
			return seg[pos];
		} else if (qhigh<low || high<qlow) {
			return (int)1e9;
		}
		
		int mid = (low+high)/2;
		return Math.min(get(low, mid, qlow, qhigh, 2*pos+1), get(mid+1, high, qlow, qhigh, 2*pos+2));
		
	}
}

public class LazySegmentTreeTest {

	public static void main(String[] args) {
		int n = 100;
		int[] a = new int[n];
		Random rand = new Random();
		for(int i=0; i<n; i++) {
			a[i] = rand.nextInt(100);
		}
		LazySegmentTree st = new LazySegmentTree(a);
		
		System.out.println(st.get(23, 34));
		st.update(10, 40, 12);
		st.update(23, 50, -2);
		System.out.println(st.get(23, 34));
		
	}

}
