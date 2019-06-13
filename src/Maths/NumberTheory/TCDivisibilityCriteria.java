package Maths.NumberTheory;

public class TCDivisibilityCriteria {

	public static void main(String[] args) {
		
		int[] ans = dc(16, 97);
		
		for(int x: ans) {
			System.out.print(x+" ");
		}
		System.out.println();
	}
	
	public static int[] dc(int n, int p) {
		
		long multiplier=10;
		int partialNumber=p;
		
		int[] factors = new int[n];
		factors[0]=1;
		
		for(int i=1; i<n; i++) {
			factors[i] = (int)(multiplier%p);
			multiplier*=10;
		}
		
		return factors;
	}

}
