package com.reimbursementapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.reimbursementapp.model.User;

public class UserDAOimpl implements UserDAO {

	public static String URL = "jdbc:postgresql://localhost:5432/" + System.getenv("Training_DB");
	private static final String USERNAME = System.getenv("Training_DB_Username");
	private static final String PASSWORD = System.getenv("Training_DB_Password");
	
	

	@Override
	public void addUser(User user) {
		List<User> users = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String sql = "INSERT INTO ers_users VALUES(DEFAULT,?,?,?,?,?,?);";
			// (DEFAULT, 'Gl25','gregspassword','Greg','Logan','gregl@gmail.com', 0)";
			PreparedStatement prepstmt = connection.prepareStatement(sql);
			prepstmt.setString(1, user.getUserName());
			prepstmt.setString(2, user.getPassword());
			prepstmt.setString(3, user.getFirstName());
			prepstmt.setString(4, user.getLastName());
			prepstmt.setString(5, user.getEmail());
			prepstmt.setInt(6, user.getRoleId());

			prepstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}



	@Override
	public List<User> selectAllUsers() {
		List<User> users = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String sql = "SELECT * FROM ers_users eu LEFT JOIN ers_user_roles er ON "
					+ "eu.user_role_id=ers_user_role_id;";
			

			PreparedStatement prepstmt = connection.prepareStatement(sql);

			ResultSet rs = prepstmt.executeQuery();

			while (rs.next()) {

				users.add(new User(rs.getInt("ers_users_id"),rs.getString("ers_username"), rs.getString("ers_password"),
						rs.getString("user_first_name"), rs.getString("user_last_name"), rs.getString("user_email"),
						rs.getInt("user_role_id"), rs.getString("user_role")));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();		}
		return users;
	}

	@Override
	public HashMap<String,String> selectUserNamesAndPasswords() {
		System.out.println("fetching user names and passwords");
		HashMap<String,String> credentials = new HashMap<>();
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)){
			String sql = "SELECT ers_username, ers_password FROM ers_users";
			
			PreparedStatement pstmt = connection.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				credentials.put(rs.getString("ers_username"), rs.getString("ers_password"));
//				System.out.println(rs.getString("ers_username") +","+ rs.getString("ers_password") );
//				if(rs.getString("ers_username").contentEquals(u) && rs.getString("ers_password").contentEquals(p)) {
//					
//					return true;
					
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return credentials;
		
	}
	
	@Override
	public User selectUserByUserName(String username) {
		User user = null;
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String sql = "SELECT * FROM ers_users eu LEFT JOIN ers_user_roles er ON "
					+ "eu.user_role_id=ers_user_role_id WHERE ers_username=?";
		
			PreparedStatement prepstmt = connection.prepareStatement(sql);
			prepstmt.setString(1, username);
			
			ResultSet rs = prepstmt.executeQuery();

			while (rs.next()) {

				user = new User(rs.getInt("ers_users_id"),rs.getString("ers_username"), rs.getString("ers_password"),
						rs.getString("user_first_name"), rs.getString("user_last_name"), rs.getString("user_email"),
						rs.getInt("user_role_id"), rs.getString("user_role"));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();		}
		return user;
	}

	
	

}
