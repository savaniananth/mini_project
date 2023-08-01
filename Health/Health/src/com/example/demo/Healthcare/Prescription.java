package com.example.demo.Healthcare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Prescription {
	private int presID;
	private String healthcauses;
	private String solutions;
	private String reappointment;
	private String patientName;
	
	
	public Prescription(int presID,String healthcauses, String solutions, String reappointment,String patientName) {
		super();
		this.presID=presID;
		this.healthcauses = healthcauses;
		this.solutions = solutions;
		this.reappointment = reappointment;
		this.patientName=patientName;
	}
	
	
	public int getPresID() {
		return presID;
	}


	public void setPresID(int presID) {
		this.presID = presID;
	}


	public String getHealthcauses() {
		return healthcauses;
	}
	public void setHealthcauses(String healthcauses) {
		this.healthcauses = healthcauses;
	}
	public String getSolutions() {
		return solutions;
	}
	public void setSolutions(String solutions) {
		this.solutions = solutions;
	}
	public String getReappointment() {
		return reappointment;
	}
	public void setReappointment(String reappointment) {
		this.reappointment = reappointment;
	}


	public String getPatientName() {
		return patientName;
	}


	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	
}