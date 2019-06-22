package AdvancedDS;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;


public class P1163B1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] a = new int[n];
		
		for(int i=0; i<n; i++) {
			a[i] = in.nextInt();
		}
		
		int[] colorCount = new int[10001];
		HashMap<Integer, Integer> countFrequency = new HashMap<>();
		int maxPrefix=0;
		for(int i=0; i<n; i++) {
			colorCount[a[i]]++;
			int count=colorCount[a[i]];
			if (!countFrequency.containsKey(count)) {
				countFrequency.put(count, 0);
			}
			countFrequency.put(count, countFrequency.get(count)+1);
			
			if (countFrequency.get(count-1)!=null) {
				countFrequency.put(count-1, countFrequency.get(count-1)-1);
				if (countFrequency.get(count-1)==0) {
					countFrequency.remove(count-1);
				}
			}
			if (countFrequency.size()==1) {
				for(Entry<Integer, Integer> e: countFrequency.entrySet()) {
					if (e.getKey()==1) {
						maxPrefix = i+1;
					}
				}
			}
			if (countFrequency.size()==2) {
				boolean isValid = false;
				boolean flag=false;
				int p = 0;
				for(Entry<Integer, Integer> e: countFrequency.entrySet()) {
					if (flag) {
						p+=e.getKey();
					} else {
						p-=e.getKey();
					}
					if (e.getValue()==1) {
						isValid=true;
//						break;
					}
					flag=true;
				}
				if ((p==1 || p==-1)) {
					maxPrefix = i+1;
				}
			}
			
			System.out.println(i+": ");
			for(Entry<Integer, Integer> e: countFrequency.entrySet()) {
				System.out.println(e.getKey()+" "+e.getValue());
			}
		}
		
		System.out.println(maxPrefix);

	}

}
