package com.ant.entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Reward {
	@Override
	public String toString() {
		return id + "," + clazz + "," + turns + "," + prize;
	}

	int id;
	String clazz;
	int turns;
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

	public Reward(String clazz, int turns, String prize) {
		File f = new File("Reward.txt");
		try {
			FileWriter fw = new FileWriter(f, true);
			FileReader fr = new FileReader(f);
			int i = 1;
			BufferedWriter bw = new BufferedWriter(fw);
			BufferedReader br = new BufferedReader(fr);
			while (br.readLine() != null) {

				i++;
			}

			this.id = i;
			this.clazz = clazz;
			this.turns = turns;
			this.prize = prize;
			bw.flush();
			bw.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public Reward() {
		super();
	}

}
