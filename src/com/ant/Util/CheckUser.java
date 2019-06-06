package com.ant.Util;

public class CheckUser {

	private final String username;
	private String warn = null;
	public CheckUser(String username, String warn) {
		this.username = username;
		this.warn = warn;
		
		 if (username.length() > 0) {
			 if (username.matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+") || username.matches("[1-9]")) {

					warn = "You can use letters(a-z, A_Z) & periods";

			} 
			 
		 }else {
			 warn = "Enter your username";
		 }
	}
	
	
}
