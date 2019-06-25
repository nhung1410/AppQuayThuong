package com.ant.Util;

import java.util.regex.Pattern;

import javax.swing.JLabel;

public class UsernameValidator {
	private Pattern pattern;
	private static final String USERNAME_PATTERN = "^[a-zA-Z0-9]{4,12}$";

	public UsernameValidator() {
		pattern = Pattern.compile(USERNAME_PATTERN);
	}

	public boolean Validate(final String username) {

		if (username != null) {
			return pattern.matcher(username).matches();
				
		} else {
			return false;
		}
	}
}
