package Maths.NumberTheory;

import java.util.Arrays;

public class TCPrimeAnagrams {

	static String s;
	static int[] ans;
	static int currentProduct = (int)1e9;
	static boolean[] isPrime;
	
	public static void main(String[] args) {
		ans = new int[] {0, 0, 0};
		soe((int)1e6+1);
		primes("10401210");
		if (ans!=null)
		for(int x: ans) {
			System.out.print(x+" ");
		}
		System.out.println();
	}
	
	public static void primes(String s) {
		TCPrimeAnagrams.s=s;
		solve(0, 0, "", "", "");
	}
	
	public static void solve(int mask, int assigned, String n1, String n2, String n3) {
		
		if (assigned==s.length()) {
			if (n1.equals("") || n2.equals("") || n3.equals("") || n1.startsWith("0")|| n2.startsWith("0")|| n3.startsWith("0")) {
				return;
			}
			int N1 = Integer.parseInt(n1);
			int N2 = Integer.parseInt(n2);
			int N3 = Integer.parseInt(n3);
			
			if (isPrime[N1] && isPrime[N2] && isPrime[N3] && N1*N2*N3<currentProduct) {
				currentProduct=N1*N2*N3;
				ans[0]=N1;
				ans[1]=N2;
				ans[2]=N3;
			}
			return;
		}
//		System.out.println("D");
		
		for(int i=0; i<s.length(); i++) {
			if ((mask&(1<<i))==0) {
				solve(mask|(1<<i), assigned+1, n1+s.charAt(i), n2, n3);
				solve(mask|(1<<i), assigned+1, n1, n2+s.charAt(i), n3);
				solve(mask|(1<<i), assigned+1, n1, n2, n3+s.charAt(i));
			}
		}
	}
	
	public static boolean[] soe(int n) {
		isPrime = new boolean[n+1];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		
		for(int i=2; i*i<=n; i++) {
			if (isPrime[i]) {
				for(int j=i*i; j<=n; j+=i) {
					isPrime[j] = false;
				}
			}
		}
		
		return isPrime;
	}

}
