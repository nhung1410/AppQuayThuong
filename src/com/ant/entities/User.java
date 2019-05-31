package com.ant.entities;

import com.toedter.calendar.JCalendar;
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
	private String dateOfBirth;

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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public User(int id, String userName, String name, String password, String address, String dateOfBirth) {
		super();
		this.id = id;
		this.userName = userName;
		this.name = name;
		this.password = password;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return id + "," + userName + "," + password + "," + name + "," + address + "," + dateOfBirth;
	}

}
