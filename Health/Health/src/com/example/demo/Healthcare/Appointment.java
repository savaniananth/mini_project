package com.example.demo.Healthcare;

import java.sql.*;

public class Appointment {
	private int appID;
	private String start_time;
	private String end_time;
	private String speciality;
	
	
	public Appointment(int appID, String start_time, String end_time,String speciality) {
		super();
		this.appID = appID;
		this.start_time = start_time;
		this.end_time = end_time;
		this.speciality=speciality;
	}
	
	public int getAppID() {
		return appID;
	}
	public void setAppID(int appID) {
		this.appID = appID;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
}
