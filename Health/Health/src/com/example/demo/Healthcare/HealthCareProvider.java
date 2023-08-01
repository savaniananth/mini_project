package com.example.demo.Healthcare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HealthCareProvider {
	private int id;
	private String doctorname;
	private String specialization;
	
	
	
	public HealthCareProvider(int id, String doctorname, String specialization) {
		super();
		this.id = id;
		this.doctorname = doctorname;
		this.specialization = specialization;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDoctorname() {
		return doctorname;
	}
	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

}