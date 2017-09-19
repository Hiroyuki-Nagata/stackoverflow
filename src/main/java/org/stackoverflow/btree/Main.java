package org.stackoverflow.btree;

import java.util.TreeSet;

//
// https://stackoverflow.com/questions/46266632/java-blacklist-with-wildcard/46293771
//
public class Main {

	static TreeSet<BlacklistEntry> cache = new TreeSet<BlacklistEntry>(new BlacklistEntry());

	public static boolean isBlacklisted(String ref, String ip, String ue, String email) {
		return isBlacklisted(new BlacklistEntry(ref, ip, ue, email));
	}
	public static boolean isBlacklisted(BlacklistEntry e) {
		return cache.contains(e);
	}
	public static void main(String[] args) {
		cache.add(new BlacklistEntry("www.google.com", "127.0.0.2", "*", "yahoo.com") );

        System.out.println(isBlacklisted("www.google.com", "127.0.0.1", "IE", "yahoo.com"));
        System.out.println(isBlacklisted("www.google.com", "127.0.0.2", "Chrome", "yahoo.com"));
        System.out.println(isBlacklisted("www.google.com", "127.0.0.3", "Edge", "yahoo.com"));
        System.out.println(isBlacklisted("www.google.com", "127.0.0.4", "Mozilla", "yahoo.com"));
        System.out.println(isBlacklisted("www.google.com", "127.0.0.5", "Ruby", "yahoo.com"));
	}

}
