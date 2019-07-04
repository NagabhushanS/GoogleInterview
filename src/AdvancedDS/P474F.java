package AdvancedDS;

import java.util.Arrays;
import java.util.Scanner;

public class P474F {

	private static int n;
	private static int[] a;
	private static int[] lowest;
	private static int[] count;
	private static final int MAX = (int)1e9+2;
	private static final int MAX2 = (int)1e9+4;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		a = new int[n];
		for(int i=0; i<n; i++) {
			a[i] = in.nextInt();
		}
		
		int m = 2*(int)Math.pow(2, Math.ceil(Math.log10(n)/Math.log10(2)))-1;
		lowest = new int[m]; //
		Arrays.fill(lowest, MAX);
		count = new int[m]; //0 if nothing exists which divides all others
		preprocess(0, n-1, 0);
//		for(int i=0; i<m; i++) {
//			System.out.println(i+" "+lowest[i]+" "+count[i]);
//		}
//		print(0, n-1, 0);
		
		int t = in.nextInt();
		while(t-->0) {
			int l = in.nextInt()-1;
			int r = in.nextInt()-1;
			int[] res = get(l, r, 0, n-1, 0);
			int ans = 0;
			if (res[0]!=MAX) {
				ans = res[1];
			}
//			System.out.println(res[0]+" "+res[1]);
			System.out.println(r-l+1-ans);
		}

	}
	
	private static int[] get(int qlow, int qhigh, int low, int high, int pos) {
		if (qhigh<low || high<qlow) {
			return new int[] {MAX2, 0};
		} else if (qlow<=low && qhigh>=high) {
//			System.out.println(pos+" "+lowest[pos]+" "+count[pos]);
			return new int[] {lowest[pos], count[pos]};
		} else {
			int mid = (low+high)/2;
			int[] left = get(qlow, qhigh, low, mid, 2*pos+1);
			int[] right = get(qlow, qhigh, mid+1, high, 2*pos+2);
			if (left[0]==MAX2 && left[1]==0) {
				return right;
			} else if (right[0]==MAX2 && right[1]==0) {
				return left;
			}
			int countLeft = left[1];
			int countRight = right[1];
			int lowestLeft = left[0];
			int lowestRight = right[0];
			int[] res = new int[] {MAX, 0};
			if (lowestLeft>lowestRight) {
				if (lowestLeft%lowestRight==0 && countRight!=0 && countLeft!=0) {
					res[0] = lowestRight;
					res[1] = countRight;
				} else {
					res[1] = 0;
					res[0] = MAX;
				}
			} else if (lowestLeft<lowestRight) {
				if (lowestRight%lowestLeft==0 && countLeft!=0 && countRight!=0) {
					res[1] = countLeft;
					res[0] = lowestLeft;
				} else {
					res[1] = 0;
					res[0] = MAX;
				}
			} else {
				if (lowestLeft!=MAX) {
					res[1] = countLeft+countRight;
					res[0] = lowestLeft;
				}
				else {
					res[1] = 0;
					res[0] = MAX;
				}
			}
//			System.out.println(pos+" "+res[0]+" "+res[1]);
			return res;
		}
	}

	private static void preprocess(int low, int high, int pos) {
		if (low==high) {
			lowest[pos] = a[low];
			count[pos] = 1;
			return;
		}
		
		int mid = (low+high)/2;
		preprocess(low, mid, 2*pos+1);
		preprocess(mid+1, high, 2*pos+2);
		if (lowest[2*pos+1]>lowest[2*pos+2]) {
			if (lowest[2*pos+1]%lowest[2*pos+2]==0 && count[2*pos+2]!=0 && count[2*pos+1]!=0) {
				lowest[pos] = lowest[2*pos+2];
				count[pos] = count[2*pos+2];
			} else {
				count[pos] = 0;
				lowest[pos] = MAX;
			}
		} else if (lowest[2*pos+1]<lowest[2*pos+2]) {
			if (lowest[2*pos+2]%lowest[2*pos+1]==0 && count[2*pos+1]!=0 && count[2*pos+2]!=0) {
				count[pos] = count[2*pos+1];
				lowest[pos] = lowest[2*pos+1];
			} else {
				count[pos] = 0;
				lowest[pos] = MAX;
			}
		} else {
			if (lowest[2*pos+1]!=MAX) {
				count[pos] = count[2*pos+1]+count[2*pos+2];
				lowest[pos] = lowest[2*pos+1];
			}
			else {
				count[pos] = 0;
				lowest[pos] = MAX;
			}
		}
		
	}
	
	private static void print(int low, int high, int pos) {
		System.out.println(pos+"-->"+low+" "+high);
		if (low==high) return;
		
		int mid = (low+high)/2;
		print(low, mid, 2*pos+1);
		print(mid+1, high, 2*pos+2);
	}

}
