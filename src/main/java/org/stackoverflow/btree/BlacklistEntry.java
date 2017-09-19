package org.stackoverflow.btree;

import java.util.Comparator;

import com.orangesignal.csv.annotation.CsvColumn;
import com.orangesignal.csv.annotation.CsvEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@CsvEntity
@ToString
public class BlacklistEntry implements Comparator<BlacklistEntry> {

	private String page;	
	private String ip;
	private String userAgent;	

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BlacklistEntry))
			return false;

		BlacklistEntry other = (BlacklistEntry) obj;

		if (page == null) {
			if (other.page != null) {
				return false;
			}
		} else if (!page.equals(other.page) && !(page.equals("*") || other.page.equals("*")) ) {
			return false;
		}

		if (ip == null) {
			if (other.ip != null) {
				return false;
			}
		} else if (!ip.equals(other.ip) && !(ip.equals("*") || other.ip.equals("*")) ) {
			return false;
		}

		if (userAgent == null) {
			if (other.userAgent != null) {
				return false;
			}
		} else if (!userAgent.equals(other.userAgent) && !(userAgent.equals("*") || other.userAgent.equals("*")) ) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((page == null) ? 0 : page.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((userAgent == null) ? 0 : userAgent.hashCode());
		return result;
	}

	public int compare(BlacklistEntry o1, BlacklistEntry o2) {
		if (o1.equals(o2)) {
			return 0;
		} else {
			return (o1.hashCode() < o2.hashCode()) ? -1 : 1;
		}
	}

}
