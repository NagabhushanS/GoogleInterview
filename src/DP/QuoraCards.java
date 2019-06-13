package DP;

//https://drive.google.com/file/d/0B4WSylvehiOLZFAyUVc0ZWRmZDA/view

public class QuoraCards {

	public static void main(String[] args) {
		int[] a = new int[] {4, 5, 6, 2};
		System.out.println(solve(a));

	}
	
	public static int solve(int[] a) {
//		return solve(a, 0, a.length-1);
		int n =a.length;
		int[][] dp = new int[n][n];
		
		for(int diff=2; diff<n; diff++) {
			for(int beg=0; beg<=n-diff; beg++) {
				int end = beg+diff;
				if (end>=n) continue;
				for(int i=beg+1; i<end; i++) {
					System.out.println(beg+" "+end);
					dp[beg][end]=Math.max(dp[beg][end], a[i]*(a[beg]+a[end])+dp[beg][i]+dp[i][end]);
				}
			}
		}
		
		return dp[0][n-1];
	}
	
	public static int solve(int[] cards, int beg, int end) {
		
		int ans=0;
		for(int i=beg+1; i<end; i++) {
			ans=Math.max(ans, cards[i]*(cards[beg]+cards[end])+solve(cards, beg, i)+solve(cards, i, end));
		}
		
		return ans;
	}

}
