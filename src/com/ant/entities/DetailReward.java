package com.ant.entities;

public class DetailReward {
	int id;
	int employeeId;
	int rewardId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getRewardId() {
		return rewardId;
	}
	public void setRewardId(int rewardId) {
		this.rewardId = rewardId;
	}
	@Override
	public String toString() {
		
		return id+","+employeeId+","+rewardId;
	}
}
