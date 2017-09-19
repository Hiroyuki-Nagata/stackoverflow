package org.stackoverflow.btree;

import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlacklistEntry implements Comparator<BlacklistEntry> {

	private String referrer, ip, userAgent, email;

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BlacklistEntry))
			return false;

		BlacklistEntry other = (BlacklistEntry) obj;

		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email) && !(email.equals("*") || other.email.equals("*")) ) {
			return false;
		}

		if (ip == null) {
			if (other.ip != null) {
				return false;
			}
		} else if (!ip.equals(other.ip) && !(ip.equals("*") || other.ip.equals("*")) ) {
			return false;
		}

		if (referrer == null) {
			if (other.referrer != null) {
				return false;
			}
		} else if (!referrer.equals(other.referrer) && !(referrer.equals("*") || other.referrer.equals("*")) ) {
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
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((referrer == null) ? 0 : referrer.hashCode());
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
