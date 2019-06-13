package Maths.BitManipulation;

public class TCSeperateConnections {

	static int n;
	
	public static void main(String[] args) {
		System.out.println(solve(new String[]	
				
				{
				"NNNNNNNNNYNNNNNYN",
				"NNNNNNNNNNNNNNNNN",
				"NNNNNNNYNNNNNNNNN",
				"NNNNNYNNNNNYNNYYY",
				"NNNNNNNNNNNNNNNYN",
				"NNNYNNNNNNNNNNYNN",
				"NNNNNNNNNYNNNNNNN",
				"NNYNNNNNNNNNNNNNN",
				"NNNNNNNNNYNNNNNNN",
				"YNNNNNYNYNNNNNNNY",
				"NNNNNNNNNNNNNNNNN",
				"NNNYNNNNNNNNNNNNN",
				"NNNNNNNNNNNNNNNNN",
				"NNNNNNNNNNNNNNNNN",
				"NNNYNYNNNNNNNNNNN",
				"YNNYYNNNNNNNNNNNN",
				"NNNYNNNNNYNNNNNNN"
				}));
	}

	public static int solve(String[] conn) {
		n = conn.length;
		return 2*solve(0, 0, conn);
	}
	
	public static int solve(int i, int mask, String[] conn) {
		
		if (i==n) {
			return 0;
		}
		
		int ans=0;
		
		for(int j=i+1; j<n; j++) {
			if ((mask&(1<<j))==0 && (mask&(1<<i))==0 && conn[i].charAt(j)=='Y' && conn[j].charAt(i)=='Y') {
				ans = Math.max(ans, 1+solve(i+1, (mask|(1<<i))|(1<<j), conn));
			}
		}
		ans = Math.max(ans, solve(i+1, mask, conn));
		
		return ans;
	}
}
