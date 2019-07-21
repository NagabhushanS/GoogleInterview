package Java;

import java.io.FileInputStream;
import java.io.IOException;

public class FIleStreams {

	public static void main(String[] args) {
		
		FileInputStream in = null;
		
		try {
			in = new FileInputStream("hello.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (in!=null)
				try {
					in.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			
		}
		
		
		
		
		
		

	}

}
