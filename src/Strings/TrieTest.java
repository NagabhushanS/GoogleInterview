package Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

class Trie {
	private class Node {
		private boolean ends;
		private HashMap<Character, Node> children;
		
		public Node() {
			ends = false;
			children = new HashMap<Character, Node>();
		}
		
	}
	
	private Node root;
	
	public Trie() {
		root = new Node();
	}
	
	public void add(String s) {
		Node node = root;
		for(int i=0; i<s.length(); i++) {
			Node temp = node.children.get(s.charAt(i));
			if (temp==null) {
				node.children.put(s.charAt(i), new Node());
				temp = node.children.get(s.charAt(i));
			}
			node = temp;
		}
		node.ends = true;
	}
	
	public boolean contains(String s) {
		Node node = root;
		for(int i=0; i<s.length(); i++) {
			Node temp = node.children.get(s.charAt(i));
			if (temp==null) {
				return false;
			}
			node = temp;
		}
		return node.ends;
	}
	
	public void delete(String s) {
		Node node = root;
		for(int i=0; i<s.length(); i++) {
			Node temp = node.children.get(s.charAt(i));
			if (temp==null) {
				return;
			}
			node = temp;
		}
		node.ends = false;
	}
	
	public Iterable<String> getAll(){
		ArrayList<String> allStrings = new ArrayList<>();
		getAll(root, allStrings, "");
		return allStrings;
	}
	
	private void getAll(Node node, ArrayList<String> allStrings, String prefix) {
		
		if (node.ends==true) {
			allStrings.add(prefix);
		}
		
		for(Entry<Character, Node> e: node.children.entrySet()) {
			char c = e.getKey();
			Node childNode = e.getValue();
			getAll(childNode, allStrings, prefix+c);
		}
	}
	
}

public class TrieTest {

	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		Trie tree = new Trie();
		int n = in.nextInt();
		while(n-->0) {
			String s = in.next();
			tree.add(s);
		}
		
		for(String s: tree.getAll()) {
			System.out.print(s+" ");
		}
		System.out.println();
		
		int d = in.nextInt();
		while(d-->0) {
			String s = in.next();
			tree.delete(s);
		}
		
		for(String s: tree.getAll()) {
			System.out.print(s+" ");
		}
		System.out.println();
		
		int q = in.nextInt();
		while(q-->0) {
			String s = in.next();
			System.out.println(tree.contains(s));
		}
	}

}
