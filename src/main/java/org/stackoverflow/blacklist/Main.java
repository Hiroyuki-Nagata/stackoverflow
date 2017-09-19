package org.stackoverflow.blacklist;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
	public static void main(String[] args) throws IOException, URISyntaxException {
		
		System.out.println("Linear Search test ! O(n) ");
		org.stackoverflow.linear_search.Main.main(null);
		
		System.out.println("Binary Search test ! O(log(n))");
		org.stackoverflow.btree.Main.main(null);
	}
}
