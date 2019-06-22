package Sorting;

class QS{
	
	public static void sort(Comparable[] a) {
		//sorts a in place
		sort(a, 0, a.length-1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi) {
//		System.out.println(lo+" "+hi);
		if (lo>=hi) {
			return;
		}
		
		int[] p = partition2(a, lo, hi);
		sort(a, lo, p[0]-1);
		sort(a, p[1]+1, hi);
	}
	
	private static int partition(Comparable[] a, int lo, int hi) {
		//loop variant
		//a[lo:i] <= pivot
		//a[i+1:j] > pivot
		//a[j+1:hi] are unexplored
		
		
		Comparable pivot = a[hi];
		int i=lo-1;
		for(int j=lo; j<hi; j++) {
			if (a[j].compareTo(pivot)<=0) {
				i++;
				//swap a[i] and a[j];
				Comparable temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}
		}
		//swap a[hi] and a[i+1]
		Comparable temp = a[hi];
		a[hi] = a[i+1];
		a[i+1] = temp;
		return i+1;
		
	}
	
	private static int[] partition2(Comparable[] a, int lo, int hi) {
		int less = 0;
		int equals=0;
		Comparable pivot = a[hi];
		for(int i=lo; i<hi; i++) {
			int cmp = pivot.compareTo(a[i]);
			if (cmp==0) {
				equals++;
			} else if (cmp>0) {
				less++;
			}
		}
		int i=lo-1;
		int j=hi;
		
		for(int k=lo; k<hi; k++) {
			int cmp = pivot.compareTo(a[k]);
			if (cmp>0) {
				i++;
				Comparable temp = a[i];
				a[i] = a[k];
				a[k] = temp;
			} else if (cmp<0) {
				j--;
				Comparable temp = a[j];
				a[j] = a[k];
				a[k] = temp;
				if (a[k].compareTo(pivot)>0) k--;
			}
		}
		Comparable temp = a[i+1];
		a[i+1] = a[hi];
		a[hi] = temp;
		return new int[] {i+1, j-1};
	}
}

public class QuickSort {

	public static void main(String[] args) {
		Integer[] a = new Integer[] {3, 43, 3, 6, 3, 75, 978, 534, 756, 5, 756, 534, 765,5, 34};
		QS.sort(a);
		for(int x: a) {
			System.out.print(x+" ");
		}
		System.out.println();
	}

}

//3 3 3 5 5 6 34 43 75 534 534 756 756 765 978 
