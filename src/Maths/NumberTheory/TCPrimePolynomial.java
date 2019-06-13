package Maths.NumberTheory;

public class TCPrimePolynomial {

	public static void main(String[] args) {
		System.out.println(solve(1, -79, 1601));
	}
	
	public static int solve(int A, int B, int C) {
		
		for(int i=0; i<(int)1e6; i++) {
			long number = A*i*i+B*i+C;
			
			if (!isPrime(number)) {
				System.out.println(number);
				return i;
			}
			
		}
			
		return -1;
	}
	
	public static boolean isPrime(long number) {
		if (number==1) {
			return false;
		}
		if (number==2) {
			return true;
		}
		if (number%2==0) return false;
		
		for(int i=3; i*i<=number; i+=2) {
			if (number%i==0) return false;
		}
		return true;
	}

}
