package DP;

public class Byes {

	public static void main(String[] args) {
		for(int i=1; i<=8; i++) {
			System.out.println(i+" "+count(i));
		}
		System.out.println(solve(4, 1));
	}
	
	public static long solve(long lb, int nb) {
		long low = lb;
		long high = 2*lb-1;
		long mid = 0;
		while(low<high) {
			mid = low+(high-low)/2;
			if (count(mid)>=nb) {
				high=mid;
			} else {
				low=mid+1;
			}
		}
		
		return low;
		
		
	}
	
	public static int count(long n) {
		int ans=0;
		while(n>1) {
			n = (int)Math.ceil(n/2);
			if (n%2!=0) {
				ans++;
			}
		}
		return ans;
	}
	
	public static boolean possible(long mid, int nb) {
		
		int count=0;
		while(mid>1) {
			mid=(int)Math.ceil((double)mid/2);
			if (mid%2!=0) count++;
		}
		if (count==nb) return true;
		else return false;
	}
	

}
