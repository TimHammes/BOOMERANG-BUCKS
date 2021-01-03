package com.reimbursementapp.dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.reimbursementapp.model.Reimbursement;
import com.reimbursementapp.model.ReimbursementStatus;
import com.reimbursementapp.model.ReimbursementType;

public class ReimbursementDAOimpl implements ReimbursementDAO {

	public static String URL = "jdbc:postgresql://localhost:5432/" + System.getenv("Training_DB");
	private static final String USERNAME = System.getenv("Training_DB_Username");
	private static final String PASSWORD = System.getenv("Training_DB_Password");

	@Override
	public void addReimbursement(Reimbursement reimb) {
		
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			
			String sql = "INSERT INTO ers_reimbursement VALUES(DEFAULT,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement prepstmt = connection.prepareStatement(sql);
			
			prepstmt.setBigDecimal(1, reimb.getReimb_amount());
			prepstmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now())); //submitted time stamp
			prepstmt.setNull(3,Types.TIMESTAMP);
			prepstmt.setString(4, reimb.getReimb_description());
			prepstmt.setBinaryStream(5, null);
			prepstmt.setInt(6,  reimb.getReimb_author());
			prepstmt.setNull(7, Types.INTEGER);
			prepstmt.setInt(8, ReimbursementStatus.PENDING.ordinal());
			prepstmt.setInt(9, ReimbursementType.valueOf(reimb.getReimbType()).ordinal());
			
			prepstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	

	@Override
	public Reimbursement selectReimbursementById(int id) {
		Reimbursement rebursement = null;
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
		
			String sql = "SELECT * FROM ers_reimbursement " +
					     "JOIN ers_reimbursement_status " +
					     "USING(reimb_status_id) "+ 
					     "JOIN ers_reimbursement_type " + 
					 	 "USING(reimb_type_id) " +
					     "WHERE reimb_id=?";
			
			PreparedStatement prepstmt = connection.prepareStatement(sql);
			prepstmt.setInt(1, id);
			
			ResultSet rs = prepstmt.executeQuery();
			
			while(rs.next()) {
				rebursement = new Reimbursement(rs.getInt("reimb_id"),rs.getBigDecimal("reimb_amount"),rs.getTimestamp("reimb_submitted"),rs.getTimestamp("reimb_resolved"),
						rs.getString("reimb_description"), rs.getBytes("reimb_receipt"), rs.getInt("reimb_author"), rs.getInt("reimb_resolver"),
						rs.getString("reimb_status"), rs.getString("reimb_type"));
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rebursement;
	}
	@Override
	public List<Reimbursement> selectAllReimbursements() {
		List<Reimbursement> reimbursements = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String sql = "SELECT * FROM ers_reimbursement " + 
					"JOIN ers_reimbursement_status " + 
					"USING(reimb_status_id) "+ 
					"JOIN ers_reimbursement_type " + 
					"USING(reimb_type_id)";
			
			PreparedStatement prepstmt = connection.prepareStatement(sql);
			
			ResultSet rs = prepstmt.executeQuery();
			
			while(rs.next()) {
				reimbursements.add(new Reimbursement(rs.getInt("reimb_id"),rs.getBigDecimal("reimb_amount"),rs.getTimestamp("reimb_submitted"),rs.getTimestamp("reimb_resolved"),
						rs.getString("reimb_description"), rs.getBytes("reimb_receipt"), rs.getInt("reimb_author"), rs.getInt("reimb_resolver"),
						rs.getString("reimb_status"), rs.getString("reimb_type")));
				
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return reimbursements;
	}
	
	@Override
	public List<Reimbursement> selectAllReimbursementsByStatusType(String status) {
		List<Reimbursement> reimbursements = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String sql = "SELECT * FROM ers_reimbursement " + 
					"Inner Join ers_reimbursement_status s " + 
					"ON r.reimb_status_id=s.reimb_status_id " + 
					"WHERE reimb_status='approved'";
			
			PreparedStatement prepstmt = connection.prepareStatement(sql);
			
			ResultSet rs = prepstmt.executeQuery();
			
			while(rs.next()) {
				reimbursements.add(new Reimbursement(rs.getInt("reimb_id"),rs.getBigDecimal("reimb_amount"),rs.getTimestamp("reimb_submitted"),rs.getTimestamp("reimb_resolved"),
						rs.getString("reimb_description"), rs.getBytes("reimb_receipt"), rs.getInt("reimb_author"), rs.getInt("reimb_resolver"),
						rs.getString("reimb_status"), rs.getString("reimb_type")));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return reimbursements;
	}

	@Override
	public void updateReimbursementStatus(int reimbID, int statusID, int resolverId) {
		System.out.println("inside dao layer update method");
		System.out.println(reimbID);
		System.out.println(statusID);
		System.out.println(resolverId);
		
		
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			
			String sql = "UPDATE ers_reimbursement " +
						 "SET reimb_status_id=?, " +
						 "reimb_resolved=?, " +
						 "reimb_resolver=? " +
						 "WHERE reimb_id=?";
			
			PreparedStatement prestmt = connection.prepareStatement(sql);
			prestmt.setInt(1, statusID);
			prestmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
			prestmt.setInt(3, resolverId);
			prestmt.setInt(4, reimbID);
			
			prestmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
