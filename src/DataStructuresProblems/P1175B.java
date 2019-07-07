package DataStructuresProblems;

import java.util.Scanner;
import java.util.Stack;

public class P1175B {

	private static int lines;
	private static long MAX = 2L*Integer.MAX_VALUE+1;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		lines = in.nextInt();
		in.nextLine();
		
		long x = 0;
		Stack<Long> stack = new Stack<>();
		stack.push(1L);
		
		for(int i=0; i<lines; i++) {
			String op = in.nextLine();
			if (op.equals("add")) {
				if (stack.peek()==-1) {
					System.out.println("OVERFLOW!!!");
					return;
				}
				long temp = x+stack.peek();
				if (temp>MAX) {
					System.out.println("OVERFLOW!!!");
					return;
				} else {
					x = temp;
				}
			} else if (op.startsWith("for")) {
				int loop = Integer.parseInt(op.split("\\s+")[1]);
				if (stack.peek()==-1 || stack.peek()*loop>MAX) {
					stack.push(-1L);
				} else
					stack.push(stack.peek()*1L*loop);
			} else if (op.equals("end")) {
				stack.pop();
			}
		}
		
		System.out.println(x);

	}

}

//for 100
//for 100
//for 100
//for 100
//for 100
//for 100
//for 100
//for 100
//for 100
//for 100
//add
//end
//end
//end
//end
//end
//end
//end
//end
//end
//end
