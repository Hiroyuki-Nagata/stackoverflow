package btree;

import static org.junit.Assert.*;

import org.junit.Test;
import org.stackoverflow.btree.BlacklistEntry;

public class BlacklistEntryTest {

	//@Test
	//public void testHashCode() {
	//	fail("まだ実装されていません");
	//}

	@Test
	public void testEqualsObject() {

		// referrer, ip, userAgent, email
		BlacklistEntry emptyLhs = new BlacklistEntry("", "", "", "");
		BlacklistEntry emptyRhs = new BlacklistEntry("", "", "", "");
		assertTrue(emptyLhs .equals (emptyRhs));

		// referrer, ip, userAgent, email
		BlacklistEntry lhs = new BlacklistEntry("google.com", "127.0.0.2", "Mozilla", "yahoo.com");
		BlacklistEntry rhs = new BlacklistEntry("google.com", "127.0.0.2", "Mozilla", "yahoo.com");
		assertTrue(lhs .equals( rhs ));
	}

	@Test
	public void testWildcardEqualsObject() {
		// referrer, ip, userAgent, email
		BlacklistEntry lhs  = new BlacklistEntry("www.google.com", "127.0.0.2", "*", "yahoo.com");
		BlacklistEntry rhs1 = new BlacklistEntry("www.google.com", "127.0.0.2", "Mozilla", "yahoo.com");
		BlacklistEntry rhs2 = new BlacklistEntry("www.google.com", "127.0.0.2", "Chrome", "yahoo.com");
		BlacklistEntry rhs3 = new BlacklistEntry("www.google.com", "127.0.0.2", "IE", "yahoo.com");

		assertTrue(lhs .equals( rhs1 ));
		assertTrue(lhs .equals( rhs2 ));
		assertTrue(lhs .equals( rhs3 ));
	}

	@Test
	public void testWildcardNotEqualsObject() {
		// referrer, ip, userAgent, email
		BlacklistEntry lhs  = new BlacklistEntry("www.google.com", "127.0.0.2", "*", "yahoo.com");
		BlacklistEntry nrhs1 = new BlacklistEntry("www.google.com", "127.0.0.1", "*", "yahoo.com");
		BlacklistEntry nrhs2 = new BlacklistEntry("www.google.com", "127.0.0.3", "foo", "yahoo.com");
		BlacklistEntry nrhs3 = new BlacklistEntry("www.google.com", "127.0.0.4", "bar", "yahoo.com");

		assertFalse(lhs .equals( nrhs1 ));
		assertFalse(lhs .equals( nrhs2 ));
		assertFalse(lhs .equals( nrhs3 ));
	}
}
