package AdvancedDS;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class P1095F {
	
	private class Node implements Comparable<Node>{
		int u;
		int w;

		public Node(int a, int b){
			u=a;
			w=b;
		}

		public int compareTo(Node node){
			return w-node.w;
		}
	}

	private int[] w;
	private int[][] sp;
	private int n;
	private int m;
	private HashMap<Integer, PriorityQueue<Node>> spMapping;

	public P1095F(int[] weights, int[][] specialOffers){
		w = weights;
		sp = specialOffers;
		n = weights.length;
		m = specialOffers.length;
		spMapping = new HashMap<>();
		for(int i=0; i<n; i++) spMapping.put(i, new PriorityQueue<>());
		for(int i=0; i<m; i++){
			int u = sp[i][0];
			int v = sp[i][1];
			int w = sp[i][2];
			spMapping.get(u).add(new Node(v, w));
			spMapping.get(v).add(new Node(u, w));
		}
	}

	public long minCost(){
		PriorityQueue<Node> pqWeights = new PriorityQueue<>();
		int minNode = -1;
		HashSet<Integer> covered = new HashSet<>();
		for(int i=0; i<n; i++){
			pqWeights.add(new Node(i, w[i]));
		}
		long totalCost = 0;
		
		while(!pqWeights.isEmpty()){
			Node node = pqWeights.poll();
			int u = node.u;
			covered.add(u);
			if (minNode==-1) {
				minNode = node.w;
			}
			long competitor=(long)1e15;
			while(!spMapping.get(u).isEmpty()){
				Node spNode = spMapping.get(u).poll();
				if (covered.contains(spNode.u)){
					competitor = spNode.w;
					break;	
				}
			}
			totalCost += Math.min(minNode+node.w, competitor);
//			System.out.println(u+" "+totalCost);
		}

		return totalCost-2*minNode;
	}


	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int[] w = new int[n];
		for(int i=0; i<n; i++) w[i] = in.nextInt();
		int[][] sp = new int[m][3];
		for(int i=0; i<m; i++) {
			int p = in.nextInt()-1;
			int q = in.nextInt()-1;
			int ww = in.nextInt();
			sp[i] = new int[] {p, q, ww};
		}
		P1095F task = new P1095F(w, sp);
		System.out.println(task.minCost());
		

	}

}
