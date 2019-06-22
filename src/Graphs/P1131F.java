package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


class Task{
	
	private class LinkedList{
		private class Node{
			int u;
			Node next;
			
			public Node(int a, Node n) {
				u = a;
				next=n;
			}
		}
		
		Node root;
		Node tail;
		public LinkedList() {
			root = null;
			tail = null;
		}
		
		public void add(int u) {
			if (root == null) {
				root = tail = new Node(u, null);
			} else {
				root = new Node(u, root);
			}
		}
		
		public void append(LinkedList second) {
			if (root == null) {
				root = tail = second.root;
			} else {
				tail.next = second.root;
				tail = second.tail;
			}
		}

		public Iterable<Integer> getAll(){
			Node node = root;
			ArrayList<Integer> ans = new ArrayList<>();
			while(node!=null) {
				ans.add(node.u);
				node = node.next;
			}
			return ans;
		}
		
	}
	
	private int n;
	private int[][] op;
	private int[] id;
//	private int[] size;
	private HashMap<Integer, LinkedList> groups;

	public Task(int[][] operations){
		op = operations;
		n = op.length+1;
		groups = new HashMap<>();
		for(int i=0; i<n; i++){
			groups.put(i, new LinkedList());
			groups.get(i).add(i);
		}
		id = new int[n];
//		size = new int[n];
//		for(int i=0; i<n; i++) size[i]=1;
		for(int i=0; i<n; i++) id[i] = i;
	}

	public Iterable<Integer> getInitialArrangement(){
		for(int i=0; i<n-1; i++){
			int u = op[i][0];
			int v = op[i][1];
			int p = root(u);
			int q = root(v);
			
			if (true) {//size[p]>size[q]){
				id[q]=p;
//				size[p]+=size[q];
				groups.get(p).append(groups.get(q));
			}
//			} else{
//				id[p]=q;
//				size[q]+=size[p];
//				groups.get(q).append(groups.get(p));
//			}

		}

		int oneRoot = root(0);
		return groups.get(oneRoot).getAll();
		
	}

	private int root(int u){
		while(u!=id[u]){
			id[u] = id[id[u]];
			u = id[u];
		}
		return u;
	}

}

public class P1131F {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[][] g = new int[n-1][2];
		for(int i=0; i<n-1; i++) {
			int p = in.nextInt()-1;
			int q = in.nextInt()-1;
			g[i] = new int[] {p, q};
		}
		Task task = new Task(g);
		for(int u: task.getInitialArrangement()) {
			System.out.print((u+1)+" ");
		}
		System.out.println();
		

	}

}
