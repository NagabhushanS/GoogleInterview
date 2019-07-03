package Graphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P1065D {

	static class Node{
		int i, j;
		int minNumber;
		int replacements;
		int currentPiece; //0-knight, 1--rook, 2-bishop
		int dist;
		
		public Node(int a, int b, int c, int d, int e, int f) {
			i=a;
			j=b;
			minNumber=c;
			replacements=d;
			currentPiece=e;
			dist=f;
		}
	}
	
	static int n;
	static int[][] g;
	static boolean[][][][][] mark;
	static int[][][][][] dist;
	
	static int[] kdx = {1, 2, 1, 2, -1, -2, -1, -2};
	static int[] kdy = {2, 1, -2, -1, 2, 1, -2, -1};
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		g = new int[n][n];
		int sx=0, sy=0;
		int ex=0, ey=0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				g[i][j] = in.nextInt()-1;
				if (g[i][j]==0) {
					sx=i;
					sy=j;
				} else if (g[i][j]==n*n-1) {
					ex=i;
					ey=j;
				}
			}
		}
		
		mark = new boolean[n][n][n*n+1][n*n+1][3];
		dist = new int[n][n][n*n+1][n*n+1][3];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				for(int p=0; p<n*n+1; p++) {
					for(int q=0; q<n*n+1; q++) {
						for(int z=0; z<3; z++) {
							dist[i][j][p][q][z] = (int)1e8;
						}
					}
				}
			}
		}
		
		Queue<Node> queue = new LinkedList<>();
		dist[sx][sy][0][0][0]=0;
		dist[sx][sy][0][0][1]=0;
		dist[sx][sy][0][0][2]=0;
		queue.add(new Node(sx, sy, 0, 0, 0, 0));
		queue.add(new Node(sx, sy, 0, 0, 1, 0));
		queue.add(new Node(sx, sy, 0, 0, 2, 0));
		
		while(!queue.isEmpty()) {
//			System.out.println("POP");
			Node node = queue.poll();
			int x = node.i;
			int y = node.j;
			//remember to add the state with replacement
			
			//PIECE REPLACEMENT
			for(int np=0; np<3; np++) {
				if (np==node.currentPiece) continue;
				int nx=node.i;
				int ny=node.j;
				if (node.replacements==n*n) continue;
				if (mark[nx][ny][node.minNumber][node.replacements+1][np]) continue;
				queue.add(new Node(nx, ny, node.minNumber, node.replacements+1, np, node.dist+1));
				mark[nx][ny][node.minNumber][node.replacements+1][np]=true;
				dist[nx][ny][node.minNumber][node.replacements+1][np]=node.dist+1;
			}
			
			if (node.currentPiece==0) {
				//KNIGHT
				for(int k=0; k<8; k++) {
					int nx = x+kdx[k];
					int ny = y+kdy[k];
					if (nx<0 || nx>=n || ny<0 || ny>=n) continue;
					int newMinNumber = node.minNumber;
					if (g[nx][ny]==node.minNumber+1) {
						newMinNumber = node.minNumber+1;
					}
					if (mark[nx][ny][newMinNumber][node.replacements][node.currentPiece]) continue;
					queue.add(new Node(nx, ny, newMinNumber, node.replacements, node.currentPiece, node.dist+1));
					mark[nx][ny][newMinNumber][node.replacements][node.currentPiece]=true;
					dist[nx][ny][newMinNumber][node.replacements][node.currentPiece] = node.dist+1;
				}
			} else if (node.currentPiece==1) {
				//ROOK
				for(int i=0; i<n; i++) {
					if (i==node.i) continue;
					//to i, node.y
					int nx = i;
					int ny = node.j;
					int newMinNumber = node.minNumber;
					if (g[nx][ny]==node.minNumber+1) {
						newMinNumber = node.minNumber+1;
					}
					if (mark[nx][ny][newMinNumber][node.replacements][node.currentPiece]) continue;
					queue.add(new Node(nx, ny, newMinNumber, node.replacements, node.currentPiece, node.dist+1));
					mark[nx][ny][newMinNumber][node.replacements][node.currentPiece]=true;
					dist[nx][ny][newMinNumber][node.replacements][node.currentPiece]=node.dist+1;
				}
				for(int j=0; j<n; j++) {
					if (j==node.j) continue;
					//to node.x, j
					int nx = node.i;
					int ny = j;
					int newMinNumber = node.minNumber;
					if (g[nx][ny]==node.minNumber+1) {
						newMinNumber = node.minNumber+1;
					}
					if (mark[nx][ny][newMinNumber][node.replacements][node.currentPiece]) continue;
					queue.add(new Node(nx, ny, newMinNumber, node.replacements, node.currentPiece, node.dist+1));
					mark[nx][ny][newMinNumber][node.replacements][node.currentPiece]=true;
					dist[nx][ny][newMinNumber][node.replacements][node.currentPiece]=node.dist+1;
				}
			} else {
				//BISHOP
				for(int i=node.i, j=node.j; i<n && j<n; i++, j++) {
					if (i==node.i && j==node.j) continue; 
					int nx = i;
					int ny = j;
					int newMinNumber = node.minNumber;
					if (g[nx][ny]==node.minNumber+1) {
						newMinNumber = node.minNumber+1;
					}
					if (mark[nx][ny][newMinNumber][node.replacements][node.currentPiece]) continue;
					queue.add(new Node(nx, ny, newMinNumber, node.replacements, node.currentPiece, node.dist+1));
					mark[nx][ny][newMinNumber][node.replacements][node.currentPiece]=true;
					dist[nx][ny][newMinNumber][node.replacements][node.currentPiece] = node.dist+1;
				}
				for(int i=node.i, j=node.j; i<n && j>=0; i++, j--) {
					if (i==node.i && j==node.j) continue; 
					int nx = i;
					int ny = j;
					int newMinNumber = node.minNumber;
					if (g[nx][ny]==node.minNumber+1) {
						newMinNumber = node.minNumber+1;
					}
					if (mark[nx][ny][newMinNumber][node.replacements][node.currentPiece]) continue;
					queue.add(new Node(nx, ny, newMinNumber, node.replacements, node.currentPiece, node.dist+1));
					mark[nx][ny][newMinNumber][node.replacements][node.currentPiece]=true;
					dist[nx][ny][newMinNumber][node.replacements][node.currentPiece] = node.dist+1;
				}
				for(int i=node.i, j=node.j; i>=0 && j<n; i--, j++) {
					if (i==node.i && j==node.j) continue; 
					int nx = i;
					int ny = j;
					int newMinNumber = node.minNumber;
					if (g[nx][ny]==node.minNumber+1) {
						newMinNumber = node.minNumber+1;
					}
					if (mark[nx][ny][newMinNumber][node.replacements][node.currentPiece]) continue;
					queue.add(new Node(nx, ny, newMinNumber, node.replacements, node.currentPiece, node.dist+1));
					mark[nx][ny][newMinNumber][node.replacements][node.currentPiece]=true;
					dist[nx][ny][newMinNumber][node.replacements][node.currentPiece] = node.dist+1;
				}
				for(int i=node.i, j=node.j; i>=0 && j>=0; i--, j--) {
					if (i==node.i && j==node.j) continue; 
					int nx = i;
					int ny = j;
					int newMinNumber = node.minNumber;
					if (g[nx][ny]==node.minNumber+1) {
						newMinNumber = node.minNumber+1;
					}
					if (mark[nx][ny][newMinNumber][node.replacements][node.currentPiece]) continue;
					queue.add(new Node(nx, ny, newMinNumber, node.replacements, node.currentPiece, node.dist+1));
					mark[nx][ny][newMinNumber][node.replacements][node.currentPiece]=true;
					dist[nx][ny][newMinNumber][node.replacements][node.currentPiece] = node.dist+1;
				}
			}
		}
		
		long ans=(long)1e15;
		int minrep = (int)1e8;
		for(int replacement=0; replacement<=n*n; replacement++) {
			for(int cp=0; cp<3; cp++) {
				if(ans>dist[ex][ey][n*n-1][replacement][cp]) {
					minrep=replacement;
					ans = dist[ex][ey][n*n-1][replacement][cp];
				}
			}
			
		}
		System.out.println(ans+" "+minrep);
		
//			int i, j;
//			int minNumber;
//			int replacements;
//			int currentPiece; //0-knight, 1--rook, 2-bishop
//			int dist;
			
			
			
		
	}

}
