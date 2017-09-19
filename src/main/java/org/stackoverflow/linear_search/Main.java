package org.stackoverflow.linear_search;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.time.StopWatch;

import com.orangesignal.csv.CsvConfig;
import com.orangesignal.csv.CsvReader;
import com.orangesignal.csv.handlers.BeanListHandler;

//
// https://stackoverflow.com/questions/46266632/java-blacklist-with-wildcard/46293771
//
public class Main {

	static List<BlacklistEntry> cache = new ArrayList<BlacklistEntry>();

	public static boolean isBlacklisted(String page, String ip, String ue) {
		return isBlacklisted(new BlacklistEntry(page, ip, ue));
	}

	public static boolean isBlacklisted(BlacklistEntry entry) {
		System.out.print("Searching " + entry.toString());
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		final String MATCH_ALL = "*";
		boolean r = cache.stream()
		    .filter(e ->
		        (MATCH_ALL.equals(e.getPage())      || e.getPage()     .equals(entry.getPage())) &&
		        (MATCH_ALL.equals(e.getIp())        || e.getIp()       .equals(entry.getIp()))   &&
		        (MATCH_ALL.equals(e.getUserAgent()) || e.getUserAgent().equals(entry.getUserAgent())))
		    .findFirst()
		    .isPresent();	
		
		stopWatch.stop();
		System.out.print(", Elasped Time:" + stopWatch.getNanoTime() / 1000000f + "ms");
		System.out.println(", Result: " + r);
		return r;
	}

	public static void main(String[] args) throws IOException, URISyntaxException {

		// file loading
		String text = new String(
				Files.readAllBytes(Paths.get(Main.class.getClass().getResource("/access.csv").toURI())));
		Reader reader = new StringReader(text);

		// orange-signal csv
		CsvConfig cfg = new CsvConfig(',');
		cfg.setQuoteDisabled(true); // デフォルトでは無効となっている囲み文字を有効にします。
		// cfg.setEscapeDisabled(false); // デフォルトでは無効となっているエスケープ文字を有効にします。
		cfg.setBreakString("\r\n"); // 項目値中の改行を\nで置換えます。
		// cfg.setNullString("NULL"); // null値扱いする文字列を指定します。
		cfg.setIgnoreLeadingWhitespaces(true); // 項目値前のホワイトスペースを除去します。
		cfg.setIgnoreTrailingWhitespaces(true); // 項目値後のホワイトスペースを除去します。
		cfg.setIgnoreEmptyLines(true); // 空行を無視するようにします。
		// cfg.setIgnoreLinePatterns(Pattern.compile("^#.*$")); //
		// 正規表現による無視する行パターンを設定します（この例では#で始まる行）。
		cfg.setSkipLines(0); // 最初の1行目をスキップして読み込みます。

		CsvReader csvReader = new CsvReader(reader);
		cache = new BeanListHandler<BlacklistEntry>(BlacklistEntry.class)
				.includes("page", "ip", "userAgent").load(csvReader);

		System.out.println("----------------------");
		System.out.println("Blacklist size: " + cache.size());
		System.out.println("----------------------");
		// for (int i = 0; i < 10; i++) {
		// System.out.println(beans.get(i).toString());
		// }

		// page,ip,ua
		isBlacklisted("*", "127.0.0.1", "*");
		isBlacklisted("*", "127.0.0.2", "*");
		isBlacklisted("*", "162.158.88.79", "*");
		isBlacklisted("*", "*", "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)");
	}

}
