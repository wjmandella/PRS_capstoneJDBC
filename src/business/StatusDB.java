package business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

import db.DBUtil;

public class StatusDB {
	
	public StatusDB() {
	
		
	}
	
	public ArrayList<Status> getAll(){
		ArrayList<Status> all = new ArrayList<>();
		String sql = "select * from status";
		try (Connection conn = DBUtil.getConnection();
			 Statement statement = conn.createStatement();
			 ResultSet rs = statement.executeQuery(sql)) {
			
			while (rs.next()) {			
				int id = rs.getInt(1);
				String desc = rs.getString(2);	
				Status status = new Status(id, desc);
				all.add(status);
			}
			
		} catch (SQLException sqle) {
			System.out.println("Error getting all status rows.");
			sqle.printStackTrace();
		}
		return all;		
	}
	
	public Status getStatus(int statusID){
		Status s = null;
		String sql = "SELECT * FROM status where id = ?";
		try (Connection conn = DBUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, statusID);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {			
				int id = rs.getInt(1);
				String desc = rs.getString(2);	
				s = new Status(id, desc);
			}
			rs.close();
			
		} catch (SQLException sqle) {
			System.out.println("Error getting status for id " + statusID);
			sqle.printStackTrace();
		}
		return s;		
	}

}
