package BinarySearch;

public class TCFairWorkLoad {

	public int getMostWork(int[] a, int w) {
		int low = 0;
		int high = 0;
		for(int x: a) high+=x;
		int mid = 0;
		
		while(low<high) {
			mid = (low+high)/2;
			if (check(mid, a, w)) {
				high = mid;
			} else {
				low = mid+1;
			}
		}
		
		if (check(low, a, w)) return low;
		else return -1;
		
	}
	
	private static boolean check(int work, int[] a, int w) {
		
		int i=0;
		int n = a.length;
		int req=0;
		while(i<n) {
			int left = work;
			while(i<n && left>=a[i]) {
				left-=a[i];
				i++;
			}
			req++;
			if (req>w) return false;
		}
		
		return req<=w;
	}
	
	public static void main(String[] args) {
		TCFairWorkLoad sol = new TCFairWorkLoad();
		int[] a = { 568, 712, 412, 231, 241, 393, 865, 287, 128, 457, 238, 98, 980, 23, 782 };
		int w = 4;
		System.out.println(sol.getMostWork(a, w));

	}

}
