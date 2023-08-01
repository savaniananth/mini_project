package com.example.demo.Healthcare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDao {

	public static Appointment getById(Connection connection, int id) {
		String query = "SELECT * FROM appointment_details WHERE appID = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String start_time = resultSet.getString(2);
				String end_time = resultSet.getString(3);
				String speciality=resultSet.getString(4);

				return new Appointment(id, start_time, end_time,speciality);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void saveToDatabase(Connection connection, int id, String start_time,
			String end_time,String speciality) {
		String query = "INSERT INTO appointment_details(appID,start_time,end_time,speciality) VALUES (?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query,
				Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, start_time);
			preparedStatement.setString(3, end_time);
			preparedStatement.setString(4, speciality);

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
	
	public static List<Appointment> getAppointmentDetails() throws SQLException{
			
		Connection connection = DB.connect();
		
		Statement st = connection.createStatement();
		
		
		ResultSet rs = st.executeQuery("SELECT * FROM appointment_details");
		
		List<Appointment> arr = new ArrayList<>();
		
		while(rs.next()) {
			
			Appointment hc = new Appointment(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4));
			arr.add(hc);
			
		}
		
		return arr;
	}
	

    public static void deleteById(Connection connection, int id) {
        String query = "DELETE FROM appointment_details WHERE appID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Appointment with ID " + id + " not found. No rows deleted.");
            } else {
                System.out.println("Appointment with ID " + id + " deleted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
