package Strings;

public class EncodingString {

	public static void main(String[] args) {
		System.out.println(encode("aaabbccccccdaccf"));
	}
	
	public static String encode(String s) {
		if (s==null) return null;
		
		int n = s.length();
		int i = 0;
		
		StringBuilder sb = new StringBuilder();
		
		while(i!=n) {
			int j = i;
			while(j<n && s.charAt(j)==s.charAt(i)) {
				j++;
			}
			if (j-i>1) {
				sb.append((j-i)+"x"+s.charAt(i));
			} else {
				sb.append(s.charAt(i));
			}
			i = j;
		}
		return sb.toString();
		
	}

}
