package com.ant.Util;

import javax.swing.JLabel;


public class CheckNull {
	public boolean checkText(String txt, JLabel lblwarn , String str) {
		if(txt.isEmpty()) {
			lblwarn.setText("Enter " + str );
			return false;
		}
		else {
			return true;
		}
	}
}
