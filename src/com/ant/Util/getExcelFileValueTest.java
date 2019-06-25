package com.ant.Util;


import java.util.Vector;

import org.junit.Test;

import com.ant.entities.Employee;

public class getExcelFileValueTest {

	@Test
	public void test() {
		getExcelFileValue excelFileValue = new getExcelFileValue();
		Vector<Employee> data = excelFileValue.getData();
		System.out.println(data.size());
	}

}
