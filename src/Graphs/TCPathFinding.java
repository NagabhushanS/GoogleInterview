package Graphs;

import java.util.LinkedList;
import java.util.Queue;

class PathFinding {
	class Node {
		int ax;
		int ay;
		int bx;
		int by;
		int steps;
		
		public Node(int a, int b, int c, int d, int e) {
			ax=a;
			ay=b;
			bx=c;
			by=d;
			steps=e;
		}
		
	}
	
	boolean[][][][] mark;
	int[] dx = {0, 1, -1, 0, 0, 1, 1, -1, -1};
	int[] dy = {0, 0, 0, -1, 1, -1, 1, -1, 1};
	
	public int minTurns(String[] board) {
		
		int n = board.length;
		int m = board[0].length();
		mark = new boolean[n][m][n][m];
		
		char[][] g = new char[n][m];
		int ax=0;
		int bx=0;
		int ay=0;
		int by=0;
		for(int i=0; i<board.length; i++) {
			g[i] = board[i].toCharArray();
			for(int j=0; j<g[i].length; j++) {
				if (g[i][j]=='A') {
					ax=i;
					ay=j;
				} else if (g[i][j]=='B') {
					bx=i;
					by=j;
				}
			}
		}
		
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(ax, ay, bx, by, 0));
		mark[ax][ay][bx][by]=true;
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			if (node.ax==bx && node.ay==by && node.bx==ax && node.by==ay) {
				return node.steps;
			}
//			System.out.println(node.ax+" "+node.ay+" "+node.bx+" "+node.by);
			int pax=node.ax;
			int pay=node.ay;
			int pbx=node.bx;
			int pby=node.by;
			for(int k=0; k<9; k++) {
				for(int l=0; l<9; l++) {
					int nax=pax+dx[k];
					int nay=pay+dy[k];
					int nbx=pbx+dx[l];
					int nby=pby+dy[l];
					if (nax==pbx && nay==pby && nbx==pax && nby==pay || nax==nbx && nay==nby) continue;
					if (!(nax>=0 && nax<n && nay>=0 && nay<m && nbx>=0 && nbx<n && nby>=0 && nby<m)) continue;
					if (g[nax][nay]=='X' || g[nbx][nby]=='X' || mark[nax][nay][nbx][nby]) continue;
					System.out.println("NEVER");
					queue.add(new Node(nax, nay, nbx, nby, node.steps+1));
					mark[nax][nay][nbx][nby]=true;
				}
			}
			
		}
		
		return -1;
	}
	
}

public class TCPathFinding {

	
	public static void main(String[] args) {
		PathFinding pf = new PathFinding();
		String[] grid = 	
			{".XXXX..X..X.X..X.X", "X.XX..X.XX.X.XX..X", "X...X...XXXXXX.X.X", "..XXX..XXX..X...XX", "X..XXXXXXX.X.XX...", "XXX..XX.XX..X...XB", "XX.X..X.XX........", "..X..X.XX..X.X.XXX", ".XX..X.X.XXX.X.XXX", "...X.XX.XX....X.X.", "X.XX..X..XX..XX..X", "X..XX..X.XXX..X...", "AX....X..X.XXXXXXX", ".XX.X..X.X.X..XX..", ".XXXX.X.X.X.X..X..", "X.XX..XXX...X.X..."}
;
		System.out.println(pf.minTurns(grid));


	}

}
