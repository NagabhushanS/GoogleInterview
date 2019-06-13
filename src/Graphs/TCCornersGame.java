package Graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class TCCornersGame {

	static class Node{
		int[] x;
		int[] y;
		
		public Node(int[] X, int[] Y) {
			x=X;
			y=Y;
		}
		
		public Node() {
			x = new int[4];
			y = new int[4];
		}
		
		public int hashCode() {
			return x[0]+x[1]+x[2]+x[3]+y[0]+y[1]+y[2]+y[3];
		}
		
		@Override
		public boolean equals(Object o) {
			Node n = (Node)o;
			for(int i=0; i<4; i++) {
				if (x[i]!=n.x[i]) {
					return false;
				}
				if (y[i]!=n.y[i]) {
					return false;
				}
			}
			return true;
		}
	}
	
	static String[] grid;
	
	static HashSet<Node> mark;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int[] jx = {2, -2, 0, 0};
	static int[] jy = {-2, 2, 0, 0};
	static HashMap<Node, Integer> dist;
	
	public static void main(String[] args) {
		grid = new String[] {
			"......","......","......","......","......","......"	
		};
		Queue<Node> queue = new LinkedList<>();
		mark = new HashSet<>();
		dist = new HashMap<>();
		Node startNode = new Node(new int[] {4, 4, 5, 5}, new int[] {4, 5, 4, 5});
		queue.add(startNode);
		mark.add(startNode);
		dist.put(startNode, 0);
		Node endNode = new Node(new int[] {0, 1, 1, 0}, new int[] {0, 0, 1, 1});
		while(!queue.isEmpty()) {
//			System.out.println("HI");
			Node node = queue.poll();
			boolean outerEquals=false;
			for(int i=0; i<4; i++) {
				boolean equals=true;
				for(int j=0; j<4; j++) {
					if (node.x[i]==endNode.x[j] && node.y[i]==endNode.y[j]) {
						
					} else {
						equals=false;
						break;
					}
				}
				if (equals) {
					outerEquals=true;
					break;
				}
			}
			
			if (outerEquals) {
				System.out.println(dist.get(node));
			}
			
			for(int i=0; i<4; i++) {
				//ith drought
				int x = node.x[i];
				int y = node.y[i];
				
				
				
				//first type of movement
				for(int k=0; k<4; k++) {
					int xn = x+dx[k];
					int yn = y+dy[k];
					boolean allowed = true;
					if (!(xn>=0 && xn<6 && yn>=0 && yn<6)) continue;
					
					for(int j=0; j<4; j++) {
					    if (j==i) continue;
						if (node.x[j]==xn && node.y[j]==yn) {
							allowed=false;
							break;
						}
					}
					if (allowed) {
						if (grid[xn].charAt(yn)=='.') {
							Node newNode = new Node();
							for(int z=0; z<4; z++) {
								if (z!=i) {
									newNode.x[z]=node.x[z];
									newNode.y[z]=node.y[z];
								}
							}
							newNode.x[i]=xn;
							newNode.y[i]=yn;
							if (!mark.contains(newNode)) {
								mark.add(newNode);
								queue.add(newNode);
								dist.put(newNode, dist.get(node)+1);
							}
						}
					}
				}
				
				//second time of movement
				for(int k=0; k<4; k++) {
					int xn = x+jx[k];
					int yn = y+jy[k];
					boolean allowed=true;
					if (!(xn>=0 && xn<6 && yn>=0 && yn<6)) continue;
					for(int j=0; j<4; j++) {
					    if (j==i) continue;
						if (node.x[j]==xn && node.y[j]==yn) {
							allowed=false;
							break;
						}
					}
					if (allowed) {
						if (grid[xn].charAt(yn)=='.') {
							int midx = (x+xn)/2;
							int midy = (y+yn)/2;
							boolean innerAllowed = false;
							if (grid[midx].charAt(midy)=='s') {
								innerAllowed=true;
							}
							for(int j=0; j<4; j++) {
								if (node.x[j]==midx && node.y[j]==midy) {
									innerAllowed=true;
									break;
								}
							}
							if (innerAllowed) {
								Node newNode = new Node();
								for(int z=0; z<4; z++) {
									if (z!=i) {
										newNode.x[z]=node.x[z];
										newNode.y[z]=node.y[z];
									}
								}
								newNode.x[i]=xn;
								newNode.y[i]=yn;
								if (!mark.contains(newNode)) {
									mark.add(newNode);
									queue.add(newNode);
									dist.put(newNode, dist.get(node)+1);
								}
							}
						}
					}
				}
				
			}
		}
	}
	
	public static int solve(String[] grid) {
		return 0;
	}

}
