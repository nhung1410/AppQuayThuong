package com.ant.entities;

public class RewardDetail {
	int id;
	String name;
	int number;
	String prize;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getPrize() {
		return prize;
	}
	public void setPrize(String prize) {
		this.prize = prize;
	}
	public RewardDetail(int id, String name, int number, String prize) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.prize = prize;
	}
	
	public RewardDetail() {
		super();
	}
}
