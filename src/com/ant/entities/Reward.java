package com.ant.entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Reward {
	@Override
	public String toString() {
		return id + "," + clazz + "," + turns + "," + prize+","+t;
	}

	int id;
	String clazz;
	int turns;
	String prize;
	int t;

	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}

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

	public int getTurns() {
		return turns;
	}

	public void setTurns(int turns) {
		this.turns = turns;
	}

	public String getPrize() {
		return prize;
	}

	public void setPrize(String prize) {
		this.prize = prize;
	}

	public Reward(int id, String clazz, int turns, String prize) {
		super();
		this.id = id;
		this.clazz = clazz;
		this.turns = turns;
		this.prize = prize;
	}

	
	
	public Reward() {
		super();
	}

}
