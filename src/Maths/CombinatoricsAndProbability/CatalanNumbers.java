package Maths.CombinatoricsAndProbability;

import java.util.Scanner;

public class CatalanNumbers {

	private static int N;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		System.out.println(catalan(N));
		System.out.println(catalan2(N));
		System.out.println(solveParenthesis(2*N));
		System.out.println(polygonTriangulation(N+2));
	}

	private static long catalan(int n) {
		if (n==0) return 1;
		
		long ans=0;
		
		for(int i=1; i<=n; i++) {
			ans += catalan(i-1)*catalan(n-i);
		}
		
		return ans;
	}
	
	private static long solveParenthesis(int n) {
		if (n==0) return 1;
		
		long ans=0;
		
		for(int i=1; i<n; i++) {
			ans += solveParenthesis(n-i-1)*solveParenthesis(i-1);
		}
		
		return ans;
	}
	
	private static long polygonTriangulation(int n) {
		if (n<=3) return 1;
		
		long ans=0;
		
		for(int i=1; i<=n-2; i++) {
			ans += polygonTriangulation(i+1)*polygonTriangulation(n-i);
		}
		return ans;
	}
	
	private static long catalan2(int n) {
		if (n==0) return 1;
		
//		return (2*(n-1)+1)*(2*(n-1)+2)/(n*(n+1))*catalan2(n-1);
		return 2*(2*n-1)*catalan2(n-1)/(n+1);
	}
}
