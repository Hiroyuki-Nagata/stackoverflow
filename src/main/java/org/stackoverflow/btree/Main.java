package org.stackoverflow.btree;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.TreeSet;

import com.orangesignal.csv.Csv;
import com.orangesignal.csv.CsvConfig;
import com.orangesignal.csv.CsvReader;
import com.orangesignal.csv.handlers.BeanListHandler;
import com.orangesignal.csv.handlers.ColumnNameMappingBeanListHandler;
import com.orangesignal.csv.handlers.CsvEntityListHandler;
import com.orangesignal.csv.handlers.StringArrayListHandler;
import com.orangesignal.csv.manager.CsvEntityManager;

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
		cache.add(new BlacklistEntry("www.google.com", "127.0.0.2", "*", "yahoo.com"));

		// file loading
		String text = new String(
				Files.readAllBytes(Paths.get(Main.class.getClass().getResource("/access.csv").toURI())));
		Reader reader = new StringReader(text);

		// orange-signal csv
		CsvConfig cfg = new CsvConfig(',');
		cfg.setQuoteDisabled(true);                           // デフォルトでは無効となっている囲み文字を有効にします。
		//cfg.setEscapeDisabled(false);                          // デフォルトでは無効となっているエスケープ文字を有効にします。
		cfg.setBreakString("\r\n");                              // 項目値中の改行を\nで置換えます。
		//cfg.setNullString("NULL");                           // null値扱いする文字列を指定します。
		cfg.setIgnoreLeadingWhitespaces(true);                 // 項目値前のホワイトスペースを除去します。
		cfg.setIgnoreTrailingWhitespaces(true);                // 項目値後のホワイトスペースを除去します。
		cfg.setIgnoreEmptyLines(true);                         // 空行を無視するようにします。
		//cfg.setIgnoreLinePatterns(Pattern.compile("^#.*$")); // 正規表現による無視する行パターンを設定します（この例では#で始まる行）。
		cfg.setSkipLines(0);                                   // 最初の1行目をスキップして読み込みます。		
		
		CsvReader csvReader = new CsvReader(reader);
		List<BlacklistEntry> beans = new BeanListHandler<BlacklistEntry>(BlacklistEntry.class)
				.includes("ip", "userAgent")
				.load(csvReader);
		
		System.out.println("blacklist size: " + beans.size());
		System.out.println(beans.get(4).toString());

	}

}
