package com.ant.Util;

import java.util.regex.Pattern;

public class PasswordValidator {
	 private Pattern pattern; 
	 private static final String PASSWORD_PATTERN= "^[a-zA-Z0-9!@#$%^&*()]{8,16}$";
	public PasswordValidator() {
		 pattern = Pattern.compile(PASSWORD_PATTERN); 
	}
	public boolean validate(final String password) {
		if(password != null) {
        return pattern.matcher(password).matches(); 
		}else {
			return false;
		}
    } 
}
