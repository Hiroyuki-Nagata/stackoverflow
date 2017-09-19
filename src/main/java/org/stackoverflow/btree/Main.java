package org.stackoverflow.btree;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.TreeSet;

import org.csveed.api.CsvClient;
import org.csveed.api.CsvClientImpl;
import org.csveed.bean.BeanInstructionsImpl;
import org.csveed.bean.ColumnNameMapper;

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
	public static void main(String[] args) throws IOException, URISyntaxException {
		cache.add(new BlacklistEntry("www.google.com", "127.0.0.2", "*", "yahoo.com") );

		String text = new String(Files.readAllBytes(Paths.get(Main.class.getClass().getResource("/access.csv").toURI())));
		Reader reader = new StringReader(text);

		CsvClient<BlacklistEntry> csvClient = new CsvClientImpl<BlacklistEntry>(reader, new BeanInstructionsImpl(BlacklistEntry.class))
                .setMapper(ColumnNameMapper.class)
                .mapColumnNameToProperty("ip", "ip")
                .mapColumnNameToProperty("user_agent", "userAgent");

        final List<BlacklistEntry> beans = csvClient.readBeans();
        System.out.println("blacklist size: " + beans.size());

//        System.out.println(isBlacklisted("www.google.com", "127.0.0.1", "IE", "yahoo.com"));
//        System.out.println(isBlacklisted("www.google.com", "127.0.0.2", "Chrome", "yahoo.com"));
//        System.out.println(isBlacklisted("www.google.com", "127.0.0.3", "Edge", "yahoo.com"));
//        System.out.println(isBlacklisted("www.google.com", "127.0.0.4", "Mozilla", "yahoo.com"));
//        System.out.println(isBlacklisted("www.google.com", "127.0.0.5", "Ruby", "yahoo.com"));
	}

}
