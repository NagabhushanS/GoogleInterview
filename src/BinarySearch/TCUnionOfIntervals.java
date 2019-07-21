package BinarySearch;

public class TCUnionOfIntervals {
	
	private int[] lb;
	private int[] ub;
	private int m;

	public int nthElement(int[] lowerBound, int[] upperBound, int n) {
		//find nth smallest number
		m = lowerBound.length; //no of intervals
		lb = lowerBound;
		ub = upperBound;

		long[] cc = count(3, n);
		System.out.println(cc[0]+" "+cc[1]);
		
		long low = -(long)1e15;
		long high = (long)1e15;
		
		long mid = 0;
		
		while(low<=high) {
			mid = (low+high)/2;
			long[] counts = count(mid, n);
			System.out.println("MID "+mid);
			if (counts[0]<=n && n<=counts[1]) {
				return (int)mid;
			} else if (n>counts[1]) {
				low=mid+1;
			} else {
				high=mid-1;
			}
		}
		
		return -1;
		
	}
	
	private long[] count(long x, int n) {
		//return the number of elements less than x
		long ans1=0;
		long ans2=0;
		for(int i=0; i<m; i++) {
			if (lb[i] <= x && x <= ub[i]) {
				ans1 += x-lb[i]+1;
				ans2 += x-lb[i];
			} else if (x>ub[i]) {
				ans1 += ub[i]-lb[i]+1;
				ans2 += ub[i]-lb[i]+1;
			}
		}
		return new long[] {ans2, ans1};
	}
	
	public static void main(String[] args) {
		int n = 947;
		int[] lb = {-28290, -95666, 56009, 46845, 957, -92924, -81921, 87726, -96436, -31200, -36490, -29356, 97994, -35475, 88148, 76565, 80071, 14060};
		int[] ub = {-28209, -95568, 56049, 46909, 1051, -92924, -81865, 87742, -96366, -31184, -36458, -29274, 98071, -35393, 88221, 76601, 80151, 14141};
		TCUnionOfIntervals sol = new TCUnionOfIntervals();
		System.out.println(sol.nthElement(lb, ub, n));

	}

}
