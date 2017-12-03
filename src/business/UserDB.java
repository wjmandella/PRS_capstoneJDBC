package business;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import db.DBUtil;

public class UserDB {
	
	public UserDB() {
	
	}
			

	public ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<>();
		String sql = "select * from user";
		
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {
			
			while (rs.next()) {			
				int id = rs.getInt(1);
				String uName = rs.getString(2);
				String pWord = rs.getString(3);
				String fName = rs.getString(4);
				String lName = rs.getString(5);
				String phone = rs.getString(6);
				String email = rs.getString(7);
				boolean review = rs.getBoolean(8);
				boolean admin = rs.getBoolean(9);
				User u = new User(id, uName, pWord, fName, lName, phone, email, review, admin);
				users.add(u);
			}
			
		} catch (SQLException sqle) {
			System.out.println("Error getting all users.");
			sqle.printStackTrace();
		}		
		
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.getUsers();
	}
	
	public boolean addUser(User user) {
		 boolean success = false;
		
		String sql = "INSERT INTO user (UserName, Password, FirstName, LastName, Phone, Email, IsReviewer, IsAdmin) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			 ps.setString(1, user.getUsername());
			 ps.setString(2, user.getPassword());
			 ps.setString(3, user.getFirstName());			 
			 ps.setString(4, user.getLastName());
			 ps.setString(5, user.getPhone());
			 ps.setString(6, user.getEmail());
			 ps.setBoolean(7, user.isReviewer());
			 ps.setBoolean(8, user.isAdmin());
			 int rowsInserted = ps.executeUpdate();
			 if (rowsInserted > 0) {
			    System.out.println("A new user was inserted successfully!");
		 
			success = true;	 

			}
			
			
		} catch (SQLException sqle) {
	
			sqle.printStackTrace();
			success = false;
		}	
		
		return success;
	}
	

	public boolean deleteUser(User user) {
		
		boolean success = false;
		
		String sql = "DELETE FROM user WHERE UserName = ?";
		
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1,  user.getUsername());
			ps.executeUpdate();
			success = true;	
		
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			success = false;
		}
		
		return success;
	}	

	public User getUserByUserName(String uName) {
		User usr = null;
		String sql = "Select * FROM User where UserName = ?";
		try(Connection connection = DBUtil.getConnection();
	       		PreparedStatement ps = connection.prepareStatement(sql))
		{
			ps.setString(1, uName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				usr = getUserFromResultSet(rs);
			}
			ps.close();
		}
		catch (SQLException sqle) {
			System.out.println("Error. No such username as '"+uName+"'.");
			sqle.printStackTrace();
		}
		
		return usr;
	}	
	
	public User authenticateUser(String uName, String pwd) {
		User usr = null;
		String sql = "Select * FROM User where UserName = ? and password = ?";
		try(Connection connection = DBUtil.getConnection();
	       		PreparedStatement ps = connection.prepareStatement(sql))
		{
			ps.setString(1, uName);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				usr = getUserFromResultSet(rs);
			}
			ps.close();
		}
		catch (SQLException sqle) {
			System.out.println("Error logging in user for username '"+uName+"' and pwd = '"+pwd+"'!");
			sqle.printStackTrace();
		}
		
		return usr;
	}	
	
	private User getUserFromResultSet(ResultSet rs) throws SQLException{
		User u = new User();
		u.setId(rs.getInt(1));
		u.setUsername(rs.getString(2));
		u.setPassword(rs.getString(3));
		u.setFirstName(rs.getString(4));
		u.setLastName(rs.getString(5));
		u.setPhone(rs.getString(6));
		u.setEmail(rs.getString(7));
		u.setReviewer(rs.getBoolean(8));
		u.setAdmin(rs.getBoolean(9));
			
		String dateTimeString = rs.getString(11);
		StringBuilder dateTimeStringT = new StringBuilder(dateTimeString);
		dateTimeStringT.setCharAt(10, 'T');
		LocalDateTime createDateT = LocalDateTime.parse(dateTimeStringT);
		u.setDateCreated(createDateT);		
		
		return u;
	}	
	
}
