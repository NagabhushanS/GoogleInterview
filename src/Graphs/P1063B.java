package Graphs;

import java.util.ArrayDeque;
import java.util.Scanner;

public class P1063B {

	static int n, m;
	static int r, c;
	static int left, right;
	static char[][] grid;
	
	static class Node{
		int x, y;
		long dist;
		
		public Node(int a, int b, long dist) {
			x=a;
			y=b;
			this.dist = dist;
		}
	}
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		r = in.nextInt()-1;
		c = in.nextInt()-1;
		left = in.nextInt();
		right = in.nextInt();
		
		grid = new char[n][m];
		for(int i=0; i<n; i++) {
			String s = in.next();
			for(int j=0; j<m; j++) {
				grid[i][j] = s.charAt(j);
			}
		}

		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		long[][] dist = new long[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				dist[i][j]=(long)1e15;
			}
		}
		
		ArrayDeque<Node> stack = new ArrayDeque<>();
		stack.addFirst(new Node(r, c, 0));
		
		dist[r][c]=0;
		
		while(!stack.isEmpty()) {
			Node node = stack.pollFirst();
			int x = node.x;
			int y = node.y;
			for(int k=0; k<4; k++) {
				int nx = x+dx[k];
				int ny = y+dy[k];
				if (nx<0 || nx>=n || ny<0 || ny>=m) continue;
				if (grid[nx][ny]=='*') continue;
				if (dy[k]==1) {
					if (dist[nx][ny]<=node.dist+1) continue;
					stack.addLast(new Node(nx, ny, node.dist+1));
					dist[nx][ny]=node.dist+1;
				} else {
					if (dist[nx][ny]<=node.dist) continue;
					stack.addFirst(new Node(nx, ny, node.dist));
					dist[nx][ny] = node.dist;
				}
			}
		}

		int ans=0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				long L = dist[i][j]-(j-c);
				if (L<=left && dist[i][j]<=right) {
					ans++;
				} 
			}
		}
		
		System.out.println(ans);
		
	}

}
