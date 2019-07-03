package DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

//https://codeforces.com/problemset/problem/1101/D

public class P1101D {

	static int n;
	static int[] a;
	static HashMap<Integer, ArrayList<Integer>> g;
	static ArrayList<Integer> primes;
	static int[] pi;
	static boolean[] isPrime;
	static int[][] dp;
	static long ans=0;
	static int np = 2000;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		a = new int[n];
		for(int i=0; i<n; i++) {
			a[i] = in.nextInt();
		}
		
		g = new HashMap<>();
		for(int i=0; i<n; i++) {
			g.put(i, new ArrayList<>());
		}
		
		for(int i=0; i<n-1; i++) {
			int p = in.nextInt()-1;
			int q = in.nextInt()-1;
			g.get(p).add(q);
			g.get(q).add(p);
		}
		
		dp = new int[n][np];
		
		soe(np);
		
		dfs(0, -1);
		
//		for(int i=0; i<n; i++) {
//			for(int j=0; j<np; j++) {
//				System.out.print(dp[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		System.out.println(ans);
	}
	
	public static void dfs(int u, int p) {
		
		
		for(int i=0; i<primes.size(); i++) {
			if (a[u]!=1)
			dp[u][primes.get(i)]=1;
		}
		
		for(int v: g.get(u)) {
			if (v==p) continue;
			
			dfs(v, u);
			
			
			
			for(int i=0; i<primes.size(); i++) {
				
//				System.out.println("DD");
				if (a[u]%primes.get(i)==0 && a[v]%primes.get(i)==0) {
//					System.out.println("DD");
					dp[u][primes.get(i)] = Math.max(dp[u][primes.get(i)], 1+dp[v][primes.get(i)]);
				}
			}
		}
		
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=0; i<primes.size(); i++) {
			list.add(dp[u][primes.get(i)]);
		}
		Collections.sort(list);
		
		if (list.size()==1) {
			ans = Math.max(ans, list.get(0));
		} else if (list.size()>=2) {
			ans = Math.max(ans, list.get(list.size()-1)+list.get(list.size()-2)-1);
		}
	}
	
	public static void soe(int N) {
		isPrime = new boolean[N+1];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		
		for(int i=2; i*i<=N; i++) {
			if (isPrime[i])
				for(int j=i*i; j<=N; j+=i) {
					isPrime[j] = false;
				}
			
		}
		
		
		primes = new ArrayList<>();
		pi = new int[N];
		Arrays.fill(pi, -1);
		int size=0;
		for(int i=0; i<=N; i++) {
			if (isPrime[i]) {
				primes.add(i);
				pi[i] = size;
				size++;
			}
		}
		
//		System.out.println(primes.size());
		
	
	}
}
