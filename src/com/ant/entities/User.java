package com.ant.entities;



import java.util.*;

/**
 * @author admin
 *
 */
public class User {
	private int id;
	private String userName;
	private String name;
	private String password;
	private String address;
	private Date dateOfBirth;
	private int age;

	
	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	@SuppressWarnings("deprecation")
	public int age() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int yearOfBirth = dateOfBirth.getYear() + 1900;
		return year - yearOfBirth;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date txtbirthDate) {
		this.dateOfBirth = txtbirthDate;
	}

	public User(int id, String userName, String name, String password, String address) {
		super();
		this.id = id;
		this.userName = userName;
		this.name = name;
		this.password = password;
		this.address = address;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return id + "," + userName + "," + password + "," + name  + "," + age()+ "," + address;
	}

}
