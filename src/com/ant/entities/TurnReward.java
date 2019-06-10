package com.ant.entities;

public class TurnReward {
	int id;
	String clazz;
	int turn;
	String prize;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	public String getPrize() {
		return prize;
	}
	public void setPrize(String prize) {
		this.prize = prize;
	}
	public TurnReward(int id, String clazz, int turn, String prize) {
	
		
		this.id = id;
		this.clazz = clazz;
		this.turn = turn;
		this.prize = prize;
	}
	
	public String toString() {
		return id+","+clazz+","+turn+","+prize;
	}
	public TurnReward() {
		super();
	}
	
	
}
