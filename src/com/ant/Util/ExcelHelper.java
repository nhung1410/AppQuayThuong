package com.ant.Util;

import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.*;
import java.lang.reflect.*;


import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.omg.CORBA.FieldNameHelper;


public class ExcelHelper {
	
	private List<String> fieldNames = new ArrayList<String>();
	private Workbook workbook = null;
	private String workbookName = "";
	 public ExcelHelper(String workbookName) {
		 this.workbookName = workbookName;
		initialize();
	 }
	 
	 private void initialize() {
		 setWorkbook(new HSSFWorkbook());
	 }
	 
	 public void closeWorkSheet() {
		 FileOutputStream fileOut;
		 try {
			fileOut = new FileOutputStream(getWorkbookName());
			getWorkbook().write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	 
	 private boolean setupFieldsForClass(Class<?> clazz) throws Exception{
		 Field[] fields = clazz.getDeclaredFields();
		 for(int i =0;i< fields.length;i++) {
			 fieldNames.add(fields[i].getName());
		 }
		 return true;
	 }
	 
	 private Sheet getSheetWithName(String name) {
		 Sheet sheet = null;
		 for(int i = 0; i<workbook.getNumberOfSheets();i++) {
			 if(name.compareTo(workbook.getSheetName(i))==0) {
				 sheet = workbook.getSheetAt(i);
				 break;
			 }
		 }
		 return sheet;
	 }
	 
	 private void initializeForRead() throws InvalidFormatException, IOException{
		 InputStream inp = new FileInputStream(getWorkbookName());
		 workbook = WorkbookFactory.create(inp);
	 }
	 
	 @SuppressWarnings( { "unchecked","rawtypes" })
	 public <T> List <T> readData(String classname) throws Exception{
		 initializeForRead();
		 Sheet sheet = getSheetWithName(classname);
		 Class clazz = Class.forName(workbook.getSheetName(0));
		 setupFieldsForClass(clazz);
		 List<T> result=new ArrayList<T>();
		 Row row;
		 for(int rowCount = 1; rowCount < sheet.getLastRowNum()+1; rowCount++) {
			 T one = (T) clazz.newInstance();
			 row = sheet.getRow(rowCount);
			 int columnCount = 0;
			 result.add(one);
			 for(Cell cell : row) {
				 CellType type = cell.getCellType();
				 String fieldName = fieldNames.get(columnCount++);
				 Method method = constructMethod(clazz, fieldName);
				 if(type == CellType.STRING) {
					 String value = cell.getStringCellValue();
					 Object[] values = new Object[1];
					 values[0] =value;
					 method.invoke(one, values);
				 }
				 else if (type == CellType.NUMERIC) {
					 Double num = cell.getNumericCellValue();
					 Class<?> returnType = getGetterReturnClass(clazz, fieldName);
					 if(returnType == int.class || returnType == Integer.class) {
						 method.invoke(one, num.intValue());
					 }
					 else if(returnType == double.class || returnType == Double.class) {
						 method.invoke(one, num);
					 }
					 else if(returnType == float.class || returnType == Float.class) {
						 method.invoke(one, num.floatValue());
					 }
					 else if(returnType == long.class || returnType == Long.class) {
						 method.invoke(one, num.longValue());
					 }
					 else if (returnType == short.class || returnType == Short.class) {
                         method.invoke(one, num.shortValue());
					 }
					 else if(returnType == Date.class) {
						 Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
						 method.invoke(one, date);
					 }
					 else if(type == CellType.BOOLEAN) {
						 boolean b = cell.getBooleanCellValue();
						 Object[] values = new Object[1];
						 values[0] = b;
						 method.invoke(one, values);
					 }
				 }
			 }
		 }
		return result;
	 }
	 
	private Class<?> getGetterReturnClass(Class clazz, String fieldName) {
		String methodName = "get" + capitalize(fieldName);
		String methodIsName = "is" + capitalize(fieldName);
		Class<?> returnType = null;
		for(Method method : clazz.getMethods()) {
			if(method.getName().equals(methodName) || method.getName().equals(methodIsName)) {
				returnType = method.getReturnType();
				break;
			}
		}
		return returnType ;
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	private Method constructMethod(Class clazz, String fieldName) throws SecurityException, NoSuchMethodException {
		// TODO Auto-generated method stub
		Class<?> fieldClass = getGetterReturnClass(clazz, fieldName);
		return clazz.getMethod(capitalize(fieldName), fieldClass);
	}

	public List<String> getFileName() {
		return fieldNames;
	}
	
	private String capitalize(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setFileName(List<String> fileName) {
		this.fieldNames = fieldNames;
	}
	public Workbook getWorkbook() {
		return workbook;
	}
	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}
	public String getWorkbookName() {
		return workbookName;
	}
	public void setWorkbookName(String workbookName) {
		this.workbookName = workbookName;
	}
	
}
