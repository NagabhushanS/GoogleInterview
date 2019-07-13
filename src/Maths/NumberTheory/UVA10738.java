package Maths.NumberTheory;

import java.util.Arrays;
import java.util.Scanner;

public class UVA10738 {

	private static int n;
	private static final int MAX = (int)1e6+1;
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[] count = new int[MAX];
		boolean[] sf = new boolean[MAX];
		Arrays.fill(sf, true);
		
		for(int i=2; i<MAX; i++) {
			if (count[i]==0) {
				for(int j=i; j<MAX; j+=i) {
					count[j]++;
					if (i*1L*i<MAX && j%(i*1L*i)==0) {
						sf[j]=false;
					}
				}
			}
		}
		
		long[] mertens = new long[MAX];
		
		mertens[1]=1;
		
		int[] mu = new int[MAX];
		mu[1]=1;
		
		for(int i=2; i<MAX; i++) {
			if (!sf[i]) {
				mertens[i] = mertens[i-1];
				mu[i]=0;
				continue;
			}
			if (count[i]%2==0) {
				mertens[i] = mertens[i-1]+1;
				mu[i]=1;
			} else {
				mertens[i] = mertens[i-1]-1;
				mu[i]=-1;
			}
		}
		
		
		while(true) {
			n = in.nextInt();
			if (n==0) break;
			
			System.out.printf("%8d%8d%8d\n", n, mu[n], mertens[n]);
			
		}
		
		

	}

}
