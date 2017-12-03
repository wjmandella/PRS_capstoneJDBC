package business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.time.LocalDate;

import db.DBUtil;

public class PurchaseRequestDB {

	public PurchaseRequestDB() {
		
	}
	
	public boolean addNewPRDB(PurchaseRequest prequest) {
		boolean success = false;
		
		String sql = "INSERT INTO purchaserequest (UserID, Description, Justification, DateNeeded, DeliveryMode, StatusID, Total, SubmittedDate) "
					+ "VALUES (?, ?, ?, ?, ?, ?,?,?)";
		
		try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			 ps.setInt(1, prequest.getUserID());
			 ps.setString(2, prequest.getDescription());
			 ps.setString(3, prequest.getJustification());	
			 ps.setString(4, prequest.getDateNeeded().toString());
			 ps.setString(5, prequest.getDeliveryMode());
			 ps.setInt(6, prequest.getStatusID());
			 ps.setDouble(7,  prequest.getTotal());
			 ps.setString(8,  prequest.getSubmittedDate().toString());
			 ps.executeUpdate();
			 		 
			success = true;
			
			try (ResultSet generatedKey = ps.getGeneratedKeys()) {
				if (generatedKey.next()) {
					prequest.setId(generatedKey.getInt(1));
				}
			}	
         }
			catch (SQLException sqle) {
			sqle.printStackTrace();
			success = false;
			}	
		
			return success;
	}
	
	public PurchaseRequest getNewPR() {
		
		ArrayList<PurchaseRequest> prList = new ArrayList<>();
		PurchaseRequest newpr = new PurchaseRequest();
		
		String sql = "select * from purchaserequest";
		
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {
			
			while (rs.next()) {			
				int id = rs.getInt(1);
				int userID = rs.getInt(2);
				String descr = rs.getString(3);
				String justif = rs.getString(4);
				String dateNeededStr = rs.getString(5);
				String dlvryMode = rs.getString(6);
				int statusID = rs.getInt(7);
				double tot = rs.getDouble(8);
				String submitDateStr = rs.getString(9);
				
				LocalDate dateNeeded = LocalDate.parse(dateNeededStr);
				LocalDate submitDate = LocalDate.parse(submitDateStr);

				newpr = new PurchaseRequest(id, userID, descr, justif, dateNeeded, dlvryMode, statusID, tot, submitDate);
						
				prList.add(newpr);
			}
			
		} catch (SQLException sqle) {
			System.out.println("Error retrieving new purchase request.");
			sqle.printStackTrace();
		}		
		
		return newpr;
	}
	
	public static boolean updatePurchaseRequestTotal() {
		PurchaseRequest newpr = new PurchaseRequest();
		boolean success = false;
			
		String sql = "UPDATE purchaserequest SET Total = ? ";
		
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			 ps.setDouble(1, newpr.getTotal());
			 int rowsInserted = ps.executeUpdate();
			 if (rowsInserted > 0) {
			    System.out.println("PR Total was successfully updated");	 
			    success = true;	 
			}			
			
		} catch (SQLException sqle) {
	
			sqle.printStackTrace();
			success = false;
		}			
		return success;	
	}
	
}
