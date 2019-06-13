package Graphs;

import java.util.LinkedList;
import java.util.Queue;

class Escape {
	
	private class Node {
		int x, y;
		int damage;
		
		public Node(int a, int b, int c) {
			x=a;
			y=b;
			damage=c;
		}
	}

	
	
	public int lowest(String[] harmful, String[] deadly) {
		int[][] grid = new int[501][501];
		for(int i=0; i<harmful.length; i++) {
			String[] s = harmful[i].split("\\s+");
			int sx=Integer.parseInt(s[0]), sy=Integer.parseInt(s[1]), ex=Integer.parseInt(s[2]), ey=Integer.parseInt(s[3]);
			
			for(int x=sx; x<=ex;) {
				for(int y=sy; y<=ey;) {
					grid[x][y]=Math.max(grid[x][y], 1);
					if (sy<=ey) y++; else y--;
				}
				if (sx<=ex) x++; else x--;
			}
		}
		for(int i=0; i<deadly.length; i++) {
			String[] s = deadly[i].split("\\s+");
			int sx=Integer.parseInt(s[0]), sy=Integer.parseInt(s[1]), ex=Integer.parseInt(s[2]), ey=Integer.parseInt(s[3]);
			for(int x=sx; x<=ex;) {
				for(int y=sy; y<=ey;) {
					grid[x][y]=Math.max(grid[x][y], 2);
					if (sy<=ey) y++; else y--;
				}
				if (sx<=ex) x++; else x--;
			}
		}
		for(int i=0; i<=500; i++) {
			for(int j=0; j<=500; j++) {
				System.out.print(grid[i][j]+" ");
			}
			System.out.println();
		}
		
		boolean[][] mark = new boolean[501][501];
		
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(0, 0, 0));
		mark[0][0]=true;
		
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, -1, 1};

		while(!queue.isEmpty()) {
			Node node = queue.poll();
			int x = node.x;
			int y = node.y;
//			System.out.println(x+" "+y);
			if (x==500 && y==500) {
				return node.damage;
			}
			
			for(int k=0; k<4; k++) {
				int nx = x+dx[k];
				int ny = y+dy[k];
				
				if (!(nx>=0 && nx<=500 && ny>=0 && ny<=500)) continue;
				if (grid[nx][ny]==2 || mark[nx][ny]) continue;
				
				queue.add(new Node(nx, ny, grid[nx][ny]+node.damage));
				mark[nx][ny]=true;
			}
		}
		
		return -1;
	}
}

public class TCEscape {
	
	public static void main(String[] args) {
		Escape e = new Escape();
		String[] harmful = {"500 0 0 500"};
		String[] deadly = {"0 0 0 0"};
		System.out.println(e.lowest(harmful, deadly));
	}

}
