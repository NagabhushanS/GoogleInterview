package Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class P144D {
	private static class MinHeap<Key extends Comparable<Key>>{
		
		private Key[] minHeap;
		private int size;
		private int capacity;
		
		
		public MinHeap(Key[] keys) {
			size = capacity = keys.length;
			minHeap = Arrays.copyOf(keys, keys.length);
			preprocess();
		}
		
		public MinHeap() {
			capacity = 2;
			size=0;
			minHeap = (Key[]) new Comparable[capacity];
		}
		
		private void preprocess() {
			for(int i=(size-1)/2; i>=0; i--) {
				shiftDown(i);
			}
		}
		
		public Key poll() {
			if (size()==0) {
				throw new IllegalStateException();
			}
			Key ans = minHeap[0];
			minHeap[0] = minHeap[--size];
			shiftDown(0);
			return ans;
		}
		
		private void shiftDown(int k) {
			
			while(2*k+1<size) {
				int j = 2*k+1;
				if (j+1<size && minHeap[j+1].compareTo(minHeap[j])<0) j++;
				if (minHeap[j].compareTo(minHeap[k])>0) break;
				Key temp = minHeap[j];
				minHeap[j] = minHeap[k];
				minHeap[k] = temp;
				k = j;
			}
		}

		public Key peek() {
			return minHeap[0];
		}
		
		public boolean isEmpty() {
			return size()==0?true:false;
		}
		
		public int size() {
			return size;
		}
		
		public void add(Key key) {
			if (size==capacity) {
				Key[] temp = (Key[]) new Comparable[capacity*2];
				for(int i=0; i<size; i++) {
					temp[i] = minHeap[i];
				}
				minHeap = temp;
				capacity*=2;
			}
			
			minHeap[size] = key;
			shiftUp(size);
			size++;
		}
		
		private void shiftUp(int k) {
			
			while(k>0 && minHeap[(k-1)/2].compareTo(minHeap[k])>0) {
				Key temp = minHeap[(k-1)/2];
				minHeap[(k-1)/2] = minHeap[k];
				minHeap[k] = temp;
				k = (k-1)/2;
			}
			
			
		}
		
		public void remove(Key key) {
			int i;
			for(i = 0; i<size; i++) {
				if (key.compareTo(minHeap[i])==0) {
					break;
				}
			}
			if (i==size) {
				//does not exist
				throw new IllegalArgumentException();
			}
			minHeap[i] = minHeap[--size];
			shiftDown(i);
		}
		
	}

	
	private static class Edge{
		private int u, v, w;
		
		public Edge(int a, int b, int c) {
			u=a;
			v=b;
			w=c;
		}
		
		public int other(int x) {
			return (x==u?v:u);
		}
		
	}
	
	private static class Node implements Comparable<Node>{
		int u;
		long dist;
		
		public Node(int a, long b) {
			u=a;
			dist=b;
		}
		
		public int compareTo(Node node) {
			return Long.compare(dist, node.dist);
		}
	}

	static Scanner in = new Scanner(System.in);
	static int n, m, s, l;
	static HashMap<Integer, ArrayList<Edge>> g;
	static int[] lw;
	
	public static void main(String[] args) {
		n = in.nextInt();
		m = in.nextInt();
		s = in.nextInt()-1;
		g = new HashMap<>();
		for(int i=0; i<n; i++) g.put(i, new ArrayList<Edge>());
		
		for(int i=0; i<m; i++) {
			int p = in.nextInt()-1;
			int q = in.nextInt()-1;
			int w = in.nextInt();
			Edge e = new Edge(p, q, w);
			g.get(p).add(e);
			g.get(q).add(e);
		}
		
		l = in.nextInt();
		
		lw = new int[n];
		
		
		long[] dist = new long[n];
		Arrays.fill(dist, (long)1e15);
		dist[s]=0;
		
		MinHeap<Node> pq = new MinHeap<>();
		pq.add(new Node(s, 0));
		
		HashSet<Integer> relaxed = new HashSet<>();
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			int u = node.u;
			
			if (relaxed.contains(u)) continue;
			relaxed.add(u);
			
			for(Edge e: g.get(u)) {
				int v = e.other(u);
				int w = e.w;
				
				if (dist[v]>dist[u]+e.w) {
					lw[v] = w;
					dist[v] = dist[u]+e.w;
					pq.add(new Node(v, dist[v]));
				}
			}
		}
		
		double ans=0;
		for(int u=0; u<n; u++) {
			if (dist[u]==l) ans++;
			for(Edge e: g.get(u)) {
				int v = e.other(u);
				int w = e.w;
				long fs = l-dist[u];
				long ss = w-fs;
				if (fs>0 && ss>0) {
					if (dist[u]+fs<=dist[v]+ss) {
						ans++;
					} 
					if (dist[u]+fs==dist[v]+ss){
						ans-=0.5;
					}
				}
			}
		}
		System.out.println((long)ans);
		
	
	}

}
