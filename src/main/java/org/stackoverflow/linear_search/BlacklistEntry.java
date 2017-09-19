package org.stackoverflow.linear_search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BlacklistEntry {
	private String page;	
	private String ip;
	private String userAgent;	
}
