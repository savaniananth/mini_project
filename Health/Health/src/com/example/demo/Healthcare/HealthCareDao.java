package com.example.demo.Healthcare;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HealthCareDao {

	public static HealthCareProvider getById(Connection connection, int id) {
		String query = "SELECT * FROM health_provider WHERE doctorID = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String name = resultSet.getString(2);
				String speciality = resultSet.getString(3);

				return new HealthCareProvider(id, name, speciality);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void saveToDatabase(Connection connection, int id, String name,
			String speciality) {
		String query = "INSERT INTO health_provider(doctorID,name,speciality) VALUES (?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query,
				Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, speciality);

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
	
	public static List<HealthCareProvider> getAlldoctors() throws SQLException{
			
		Connection connection = DB.connect();
		
		Statement st = connection.createStatement();
		
		
		ResultSet rs = st.executeQuery("SELECT * FROM health_provider");
		
		List<HealthCareProvider> arr = new ArrayList<>();
		
		while(rs.next()) {
			
			HealthCareProvider hc = new HealthCareProvider(rs.getInt(1), rs.getString(2), rs.getString(3));
			arr.add(hc);
			
		}
		
		return arr;
	}

}
