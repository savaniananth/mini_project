package com.example.demo.Healthcare;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDao {

	public static Prescription getById(Connection connection, int id) {
		String query = "SELECT * FROM prescription_details WHERE presID = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String healthcauses = resultSet.getString(2);
				String solutions = resultSet.getString(3);
				String reappointment = resultSet.getString(4);
				String patientName = resultSet.getString(5);

				return new Prescription(id,healthcauses,solutions,reappointment,patientName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void saveToDatabase(Connection connection,int id,String healthcauses,String solutions,
			String reappointment,String patientName) {
		String query = "INSERT INTO prescription_details(presID,healthcauses,solutions,reappointment,patientName) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query,
				Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, healthcauses);
			preparedStatement.setString(3, solutions);
			preparedStatement.setString(4, reappointment);
			preparedStatement.setString(5, patientName);

			preparedStatement.executeUpdate();

			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					id = generatedKeys.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Prescription> getPresDetails() throws SQLException{
			
		Connection connection = DB.connect();
		
		Statement st = connection.createStatement();
		
		
		ResultSet rs = st.executeQuery("SELECT * FROM prescription_details");
		
		List<Prescription> arr = new ArrayList<>();
		
		while(rs.next()) {
			
			Prescription p = new Prescription(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5));
			arr.add(p);
			
		}
		
		return arr;
	}
	
	 public static void deleteById(Connection connection, int id) {
	        String query = "DELETE FROM prescription_details WHERE presID = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setInt(1, id);

	            int rowsAffected = preparedStatement.executeUpdate();
	            if (rowsAffected == 0) {
	                System.out.println("Prescription with ID " + id + " not found. No rows deleted.");
	            } else {
	                System.out.println("Prescription with ID " + id + " deleted successfully.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	 }
}
