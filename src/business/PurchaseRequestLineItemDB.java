package business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DBUtil;

public class PurchaseRequestLineItemDB {

	public PurchaseRequestLineItemDB() {
		
	}
	
	public static void calculatePRLITotal(PurchaseRequest prRequest, PurchaseRequestLineItem prli) {
		double total = 0;
		ProductDB prodDB = new ProductDB();
		for (PurchaseRequestLineItem prli1 : prRequest.getPrLineItems()) {
			for (Product product : prodDB.getAllProducts()) {
				if (prli1.getProductID() == product.getId()) {
					total += prli1.getQuantity() * product.getPrice();
				}
			}
		}
		prRequest.setTotal(total);
	}
		
	
	public static boolean addNewPRLI(PurchaseRequest pr) {
		boolean success = false;
			
		String sql = "INSERT INTO purchaserequestlineitem (PurchaseRequestID, ProductID, Quantity) "
					+ "VALUES (?, ?, ?)";
		for (PurchaseRequestLineItem prli: pr.getPrLineItems()) {
			prli.setPurchaseRequestID(pr.getId());
		
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			 ps.setInt(1, prli.getPurchaseRequestID());
			 ps.setInt(2, prli.getProductID());
			 ps.setInt(3, prli.getPurchaseRequestID());	
			 ps.executeUpdate();
			 		 
			success = true;
		   try (ResultSet generatedKey = ps.getGeneratedKeys()) {
			   if (generatedKey.next()) {
				   prli.setId(generatedKey.getInt(1));
			   }
		   }	
		} catch (SQLException sqle) {
			System.out.println(sqle);
			success = false;
		}	
	}
		return success;
	}
	
	
//	
//	public static boolean addNewPRLI(PurchaseRequest pr) {
//		boolean success = false;
//			
//		String sql = "INSERT INTO purchaserequestlineitem (PurchaseRequestID, ProductID, Quantity) "
//					+ "VALUES (?, ?, ?)";
//		for (PurchaseRequestLineItem prli: pr.getPrLineItems()) {
//			prli.setPurchaseRequestID(pr.getId());
//		
//		try (Connection conn = DBUtil.getConnection();
//			 PreparedStatement ps = conn.prepareStatement(sql)) {
//			 ps.setInt(1, prli.getPurchaseRequestID());
//			 ps.setInt(2, prli.getProductID());
//			 ps.setInt(3, prli.getPurchaseRequestID());	
//			 ps.executeUpdate();
//			 		 
//			success = true;
//		   try (ResultSet generatedKey = ps.getGeneratedKeys()) {
//			   if (generatedKey.next()) {
//				   prli.setId(generatedKey.getInt(1));
//			   }
//		   }	
//		} catch (SQLException sqle) {
//			System.out.println(sqle);
//			success = false;
//		}	
//	}
//		return success;
//	}
	
}
//	public boolean addNewPRLI(PurchaseRequestLineItem prli) {
//		boolean success = false;
//		
//		String sql = "INSERT INTO purchaserequestlineitem (PurchaseRequestID, ProductID, Quantity) "
//					+ "VALUES (?, ?, ?)";
//		
//		try (Connection conn = DBUtil.getConnection();
//			 PreparedStatement ps = conn.prepareStatement(sql)) {
//			 ps.setInt(1, prli.getPurchaseRequestID());
//			 ps.setInt(2, prli.getProductID());
//			 ps.setInt(3, prli.getPurchaseRequestID());	
//			 ps.executeUpdate();
//			 		 
//			success = true;
//			
//		} catch (SQLException sqle) {
//			System.out.println(sqle);
//			success = false;
//		}	
//		
//		return success;
//	}
		

