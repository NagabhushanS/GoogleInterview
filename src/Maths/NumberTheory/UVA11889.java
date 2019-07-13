package Maths.NumberTheory;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class UVA11889 {

	private static int a, c;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		next:
		for(int z=0; z<t; z++) {
			a = in.nextInt();
			c = in.nextInt();
			
			HashMap<Integer, Integer> pfA = new HashMap<>();
			HashMap<Integer, Integer> pfC = new HashMap<>();
			
			for(int i=2; i*i<=a; i++) {
				int count=0;
				while(a%i==0) {
					a/=i;
					count++;
				}
				if (count!=0)
					pfA.put(i, count);
			}
			if (a>1) {
				pfA.put(a, 1);
			}
			
			for(int i=2; i*i<=c; i++) {
				int count=0;
				while(c%i==0) {
					c/=i;
					count++;
				}
				if (count!=0)
					pfC.put(i, count);
			}
			if (c>1) {
				pfC.put(c, 1);
			}
			
			for(int p: pfA.keySet()) {
				if (!pfC.containsKey(p)) {
					System.out.println("NO SOLUTION");
					continue next;
				}
			}
			
			long b=1;
			for(Entry<Integer, Integer> e: pfC.entrySet()) {
				int p = e.getKey();
				int count = e.getValue();
				if (pfA.containsKey(p)) {
					if (pfA.get(p)>count) {
						System.out.println("NO SOLUTION");
						continue next;
					}
					if (pfA.get(p)!=count) {
						b*=Math.pow(p, count);
					}
				} else {
					b*=Math.pow(p, count);
				}
			}
			System.out.println(b);
			
		}
	}

}
