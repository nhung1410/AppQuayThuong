package com.ant.Util;

import javax.swing.JLabel;


public class CheckNull {
	public boolean checkText(String txt, JLabel lbl) {
		if(txt.isEmpty()) {
			lbl.setText("Enter blank");
			return false;
		}
		else {
			return true;
		}
	}
}
