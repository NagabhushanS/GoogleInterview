package AdvancedDS;

class MedianSegmentTree{
	int[] seg;
	int[] a;
	int n;
	
	public MedianSegmentTree(int[] a) {
		this.a = a;
		int m = (int)Math.ceil(Math.log10(n)/Math.log10(2));
		m = (int)(2*Math.pow(2, m)-1);
		seg = new int[m];
	}
	
	public void construct(int low, int high, int pos) {
		if (low==high) {
			seg[pos]=a[low];
		}
		
		
		int mid = (low+high)/2;
		construct(low, mid, 2*pos+1);
		construct(mid+1, high, 2*pos+2);
	}
}

public class TCFloatingMedian {

	public static void main(String[] args) {
		

	}

}
