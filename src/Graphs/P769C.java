package Graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P769C {
	static Scanner in = new Scanner(System.in);
	static int n, m, k1;
	static char[][] g;
	static int sx;
	static int sy;
	static int[] dx = {1, 0, 0, -1};
	static int[] dy = {0, -1, 1, 0};
	
	static class Node{
		int i, j;
		int dist;
		
		public Node(int x, int y, int d) {
			i=x;
			j=y;
			dist=d;
		}
	}
	
	
	
	public static void main(String[] args) {
		n = in.nextInt();
		m = in.nextInt();
		k1 = in.nextInt();
		g = new char[n][m];
		
		for(int i=0; i<n; i++) {
			String s = in.next();
			for(int j=0; j<m; j++) {
				g[i][j] = s.charAt(j);
				if (g[i][j]=='X') {
					sx = i;
					sy = j;
				}
			}
		}
		
		int[][] dist = new int[n][m];
		for(int i=0; i<n; ++i) {
			for(int j=0; j<m; j++) {
				dist[i][j] = (int)1e8;
			}
		}
		dist[sx][sy] = 0;
		
		int[][] parX = new int[n][m];
		int[][] parY = new int[n][m];
		char[][] dir = new char[n][m];
		char[] direction = {'D', 'L', 'R', 'U'};
		char[] revDirection = {'U', 'R', 'L', 'D'};
		
		boolean[][] mark = new boolean[n][m];
		
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(sx, sy, 0));
		mark[sx][sy] = true;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			int x = node.i;
			int y = node.j;
			
			for(int k=0; k<4; k++) {
				int nx = x+dx[k];
				int ny = y+dy[k];
				if (nx<0 || nx>=n || ny<0 || ny>=m) continue;
//				System.out.println(nx+" "+ny+" "+n+" "+m);
				if (mark[nx][ny] || g[nx][ny]=='*') continue;
				queue.add(new Node(nx, ny, node.dist+1));
				parX[nx][ny] = x;
				parY[nx][ny] = y;
				dir[nx][ny] = revDirection[k];
				dist[nx][ny] = node.dist+1;
				mark[nx][ny]=true;
			}
		}
		
		
		int x = sx;
		int y = sy;
		int steps=0;
		char[] ans = new char[k1];
		out:
		while(true) {
			for(int k=0; k<4; k++) {
				int nx = x+dx[k];
				int ny = y+dy[k];
				if (nx<0 || nx>=n || ny<0 || n>=m) continue;
				if (g[nx][ny]=='*') continue;
				if (k1-(steps+1)>=dist[nx][ny]) {
					x = nx;
					y = ny;
					steps++;
					ans[steps-1] = direction[k];
					if (k1-steps==dist[nx][ny]) {
						break out;
					}
					continue out;
				} 
			}
			System.out.println("IMPOSSIBLE");
			return;
		}
		String s = new String(ans);
		System.out.print(s.trim());
//		System.out.println(x+" "+y);
		for(int z=0; z<k1-steps; z++) {
			int tempX = x;
			int tempY = y;
			x = parX[tempX][tempY];
			y = parY[tempX][tempY];
			System.out.print(dir[tempX][tempY]);
		}
		
		
		
		
	}

}
