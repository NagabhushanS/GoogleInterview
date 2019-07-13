package Graphs;

import java.util.PriorityQueue;
import java.util.Scanner;

public class UVA10801 {
	
	private static class Node implements Comparable<Node>{
		private int f;
		private int flag; //stopped or moving
		private int l;
		private int dist;
		
		public Node(int a, int b, int c, int d) {
			f=a;
			flag=b;
			l=c;
			dist=d;
		
		}
		
		@Override
		public int compareTo(Node node) {
			return dist-node.dist;
		}
		
	}

	private static int n, k;
	private static boolean[][] lift;
	private static int[] t;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			n = in.nextInt();
			k = in.nextInt();
			t = new int[n];
			for(int i=0; i<n; i++) {
				t[i] = in.nextInt();
			}
			in.nextLine();
			lift = new boolean[n][100];
			for(int i=0; i<n; i++) {
				String s = in.nextLine();
				String[] floors = s.split("\\s+");
				for(int j=0; j<floors.length; j++) {
					int u = Integer.parseInt(floors[j]);
//					System.out.println(lift[i].length+" "+u);
					lift[i][u]=true;
				}
			}
			if (k==0) {
				System.out.println(0);
				continue;
			}
			PriorityQueue<Node> queue = new PriorityQueue<>();
			queue.add(new Node(0, 0, -1, 0));
			boolean relaxed[][][] = new boolean[100][2][6];
			boolean flag=false;
			
			while(!queue.isEmpty()) {
				Node node = queue.poll();
				
				if (node.f==100 || node.f<0) continue;
				
				if (node.f==k && node.flag==0) {
					flag=true;
					System.out.println(node.dist-60);
					break;
				}
				
				if (relaxed[node.f][node.flag][node.l+1]) continue; 
				relaxed[node.f][node.flag][node.l+1]=true;
				
				if (node.flag==0) {
					//change
					for(int i=0; i<n; i++) {
						int changeCost = (i==node.l?0:60);
						if (!lift[i][node.f]) continue;
						queue.add(new Node(node.f, 1, i, node.dist+changeCost));
					}
					
				
				} else {
					//moving
					//get down
					if (lift[node.l][node.f]) {
						queue.add(new Node(node.f, 0, -1, node.dist));
					}
					//continue moving
					queue.add(new Node(node.f+1, 1, node.l, node.dist+t[node.l]));
					//continue moving
					queue.add(new Node(node.f-1, 1, node.l, node.dist+t[node.l]));
				}
			}
			if (!flag)
				System.out.println("IMPOSSIBLE");
		}

	}

}
