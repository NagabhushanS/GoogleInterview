package AdvancedDS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class P1182C {

	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		String[] s = new String[n];
		for(int i=0; i<n; i++) s[i] = in.next();
		
		ArrayList<String>[] numberToWords = new ArrayList[(int)1e6];
		for(int i=0; i<n; i++) {
			int count=0;
			for(int j=0; j<s[i].length(); j++) {
				switch(s[i].charAt(j)) {
				case 'a':
				case 'e':
				case 'i':
				case 'o':
				case 'u':
					count++;
					break;
				default:
				}
			}
			if (numberToWords[count]==null) {
				numberToWords[count] = new ArrayList<>();
			}
			numberToWords[count].add(s[i]);
		}
		
		int possible=0;
		for(int i=0; i<numberToWords.length; i++) {
			possible+=numberToWords[i].size()/2;
		}
//		possible/=2;
		
		HashMap<Integer, ArrayList<String>>[] vnw = new HashMap[26];
		for(int i=0; i<n; i++) {
			int count=0;
			Character lastVowel = null;
			for(int j=s[i].length()-1; j>=0; j--) {
				switch(s[i].charAt(j)) {
				case 'a':
				case 'e':
				case 'i':
				case 'o':
				case 'u':
					if (lastVowel==null) lastVowel = s[i].charAt(j);
					count++;
					break;
				default:
				}
			}
			char lv = (char)lastVowel;
			if (vnw[lv-'a']==null) {
				vnw[lv-'a'] = new HashMap<>();
			}
			if (vnw[lv-'a'].get(count)==null) {
				vnw[lv-'a'].put(count, new ArrayList<>());
			}
			vnw[lv-'a'].get(count).add(s[i]);
		}
		
		ArrayList<String> firstWords = new ArrayList<>();
		ArrayList<String> secondWords = new ArrayList<>();
		
		for(int i=0; i<26; i++) {
			
		}

	}

}
