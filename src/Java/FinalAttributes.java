package Java;

public class FinalAttributes {

	public static void main(String[] args) {
		int[] a = new int[] {2, 3};
		solve(a);

	}

	public static long solve(final int[] a) {
		
		for(int x: a) {
			System.out.print(x+" ");
		}
		System.out.println();
		return 0;
	}
	
}
