package DP;

import java.util.Scanner;
import java.util.Stack;

public class UVA836 {

	private static int[][] a;
	private static int n;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		while(T-->0) {
			String s = in.next();
			n = s.length();
			a = new int[n][n];
			for(int i=0; i<n; i++) {
				a[0][i] = s.charAt(i)-'0';
			}
			for(int i=1; i<n; i++) {
				String ss = in.next();
				for(int j=0; j<n; j++) {
					a[i][j] = ss.charAt(j)-'0';
				}
			}
			
			int[] currentRow = new int[n];
			int maxArea=0;
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if (a[i][j]==0) {
						currentRow[j]=0;
					} else {
						currentRow[j]+=a[i][j];
					}
				}
				int area = getHistogramArea(currentRow);
				maxArea = Math.max(area, maxArea);
			}
			
			System.out.println(maxArea);
			
			if (T!=0) {
				System.out.println();
			}
		}

	}

	private static int getHistogramArea(int[] currentRow) {
		int n = currentRow.length;
		
		Stack<Integer> stack = new Stack<>();
		int maxRectArea=0;
		
		int i=0;
		while(i<n) {
			if (stack.isEmpty() || currentRow[i]>=currentRow[stack.peek()]) {
				stack.push(i++);
			} else {
				//find max rect with stack.peek as top
				int top = stack.pop();
				int areaWithTop = currentRow[top]*(stack.isEmpty()?i:i-stack.peek()-1);
				maxRectArea = Math.max(maxRectArea, areaWithTop);
			}
		}
		
		
		while(!stack.isEmpty()) {
			int top = stack.pop();
			int areaWithTop = currentRow[top]*(stack.isEmpty()?n:n-stack.peek()-1);
			maxRectArea = Math.max(maxRectArea, areaWithTop);
			
		}
        return maxRectArea; 
	}

}
