package Graphs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class UVA10067 {

	private static class Node {
		int[] a;
		
		public Node(int[] b) {
			a = Arrays.copyOf(b, b.length);
		}
		
		public Node increment(int i) {
			int[] res = Arrays.copyOf(a, a.length);
			if (res[i]==9) {
				res[i]=0;
			} else {
				res[i]++;
			}
			return new Node(res);
		}
		
		public Node decrement(int i) {
			int[] res = Arrays.copyOf(a, a.length);
			if (res[i]==0) {
				res[i]=9;
			} else {
				res[i]--;
			}
			return new Node(res);
		}
		
		public int toInt() {
			return a[0]*1000+a[1]*100+a[2]*10+a[3];
		}
	}
	
	private static int bc;
	private static HashSet<Integer> barred;
	private static int s, t;
	private static int[] dist;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		for(int z=1; z<=N; z++) {
			int[] s = new int[4];
			int[] t = new int[4];
			for(int i=0; i<4; i++) {
				s[i] = in.nextInt();
			}
			for(int i=0; i<4; i++) {
				t[i] = in.nextInt();
			}
			bc = in.nextInt();
			barred = new HashSet<>();
			for(int i=0; i<bc; i++) {
				int u = in.nextInt()*1000+in.nextInt()*100+in.nextInt()*10+in.nextInt();
				barred.add(u);
			}
			dist = new int[100000];
			Arrays.fill(dist, (int)1e9);
			Queue<Node> queue = new LinkedList<>();
			Node start = new Node(s);
			queue.add(start);
			dist[start.toInt()]=0;
			boolean[] mark = new boolean[10000];
			mark[start.toInt()]=true;
			int[] par = new int[10000];
			par[start.toInt()]=-1;
			
			while(!queue.isEmpty()) {
				Node node = queue.poll();
				
				for(int i=0; i<4; i++) {
					Node node1 = node.increment(i);
					Node node2 = node.decrement(i);
					if (!barred.contains(node1.toInt()) && !mark[node1.toInt()]) {
						dist[node1.toInt()]=dist[node.toInt()]+1;
						mark[node1.toInt()]=true;
						queue.add(node1);
					}
					if (!barred.contains(node2.toInt()) && !mark[node2.toInt()]) {
						dist[node2.toInt()]=dist[node.toInt()]+1;
						mark[node2.toInt()]=true;
						queue.add(node2);
					}
				}
			}
			int ans = dist[new Node(t).toInt()];
			System.out.println(ans==(int)1e9?-1:ans);
			
		}
		
	}
	

}
