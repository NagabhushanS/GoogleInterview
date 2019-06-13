package AdvancedDS;

import java.util.Random;

class SegmentTree{
	int[] a;
	int[] seg;
	int n;
	int count=0;
	
	public SegmentTree(int[] a) {
		n = a.length;
		this.a = a;
		int m = (int)Math.ceil(Math.log10(n)/Math.log10(2));
		m = (int)(2*Math.pow(2, m)-1);
		seg = new int[m];
		constructTree(0, n-1, 0);
	}
	
	public void constructTree(int low, int high, int pos) {
		if (low==high) {
			seg[pos]=a[low];
			return;
		}
		int mid = (low+high)/2;
		constructTree(low, mid, 2*pos+1);
		constructTree(mid+1, high, 2*pos+2);
		seg[pos] = Math.min(seg[2*pos+1], seg[2*pos+2]);
		return;
	}
	
	public int getMinimum(int qlow, int qhigh) {
		return getMinimum(0, 0, n-1, qlow, qhigh);
	}
	
	public int getMinimum(int pos, int low, int high, int qlow, int qhigh) {
		if (qlow>high || qhigh<low) {
			return (int)1e9;
		} else if (qlow<=low && qhigh>=high) {
			return seg[pos];
		} else {
			count++;
			System.out.println("HERE");
			int mid = (low+high)/2;
			return Math.min(getMinimum(2*pos+1, low, mid, qlow, qhigh), getMinimum(2*pos+2, mid+1, high, qlow, qhigh));
				
		}
	}
}

public class SegmentTreesTest {

	public static void main(String[] args) {
		int n = 1000;
		int[] a = new int[n];
		Random rand = new Random();
		for(int i=0; i<n; i++) {
			a[i] = rand.nextInt(100);
		}
		SegmentTree st = new SegmentTree(a);
		
		int[][] min = new int[n][n];
		for(int i=0; i<n; i++) {
			min[i][i]=a[i];
			for(int j=i+1; j<n; j++) {
				min[i][j]=Math.min(min[i][j-1], a[j]);
			}
		}
		System.out.println("ST Created");
		
//		for(int i=0; i<n; i++) {
//			for(int j=i; j<n; j++) {
//				int q = st.getMinimum(i, j);
//				if (min[i][j]!=q) {
//					System.out.println("ERROR");
//				}
////				System.out.println(min[i][j]+" "+st.getMinimum(i, j));
//			}
//		}
//		System.out.println(st.getMinimum(10, 20));
	}

}
