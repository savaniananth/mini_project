package com.example.demo.Healthcare;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

interface DatabaseOperations {
    void saveToDatabase(Connection connection, int id, String name, int age, String phoneNumber, String medicalHistory) throws SQLException;
    Patient getById(Connection connection, int id) throws SQLException;
    List<Patient> getAllPatients() throws SQLException;
    void deleteById(Connection connection,int id);
}

abstract class Dao implements DatabaseOperations {

    protected static final String URL = "jdbc:mysql://localhost:3306/hospital?createDatabaseIfNotExists=true";
    protected static final String USER = "root";
    protected static final String PASSWORD = "Tiger@123";
    protected static final String JDBC_URL = "com.mysql.cj.jdbc.Driver";

    protected Connection connection;

    public Dao() {
        connection = DB.connect();
    }
}

public class PatientDao extends Dao {
    @Override
    public void saveToDatabase(Connection connection, int id, String name, int age, String phoneNumber, String medicalHistory) throws SQLException {
        String query = "INSERT INTO patient_details (patientID, patientName, patientAge, phoneno, medicalHistory) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setString(5, medicalHistory);

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }
            }
        }
    }

    @Override
    public Patient getById(Connection connection, int id) throws SQLException {
        String query = "SELECT * FROM patient_details WHERE patientID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                String phoneNumber = resultSet.getString(4);
                String medicalHistory = resultSet.getString(5);

                return new Patient(id, name, phoneNumber, age, medicalHistory);
            }
        }
        return null;
    }

    @Override
    public List<Patient> getAllPatients() throws SQLException {
        List<Patient> arr = new ArrayList<>();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM patient_details");
        while (rs.next()) {
            Patient patient = new Patient(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5));
            arr.add(patient);
        }
        return arr;
    }
    
    @Override
    public void deleteById(Connection connection, int id) { 
    	String query = "DELETE FROM patient_details WHERE patientID = ?"; 
    	try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                       preparedStatement.setInt(1, id);
                       int rowsAffected = preparedStatement.executeUpdate(); 
                       if (rowsAffected == 0){
                    	   System.out.println("Patient with ID " + id +" not found. No rows deleted."); 
                    	   }
                       else {
                          System.out.println("Patient with ID " + id + " deleted successfully."); 
                          } 
    	}catch (SQLException e) {
    		e.printStackTrace();
    		} 
     
     }
    
}