package com.ant.Util;

import java.util.regex.Pattern;

public class TurnsValidator {
	private Pattern pattern;
	private static final String TURN_PATTERN = "^[0-9]{1,}$";

	public TurnsValidator() {
		pattern = Pattern.compile(TURN_PATTERN);
	}

	public boolean Validate(final String turn) {

		if (turn != null) {
			return pattern.matcher(turn).matches();
		} else {
			return false;
		}
	}
}
