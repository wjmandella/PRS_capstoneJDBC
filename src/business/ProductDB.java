package business;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.DBUtil;

public class ProductDB {
	private ArrayList<Product> products = null;
	
	public ProductDB() {
	
	}

	public ArrayList<Product> getAllProducts(){
		ArrayList<Product> all = new ArrayList<>();
		String sql = "select * from product";
		
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {
			
			while (rs.next()) {			
				int id = rs.getInt(1);
				int vendorID = rs.getInt(2);
				String venPartNum = rs.getString(3);
				String vendName = rs.getString(4);
				double price = rs.getDouble(5);
				String unit = rs.getString(6);
				String photoPath = rs.getString(7);
				Product prod = new Product(id, vendorID, venPartNum, vendName, price, unit, photoPath);
				all.add(prod);
			}
			
		} catch (SQLException sqle) {
			System.out.println("Error getting all products.");
			sqle.printStackTrace();
		}
		return all;		
	}
	
	
	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
	public void displayProducts() {
		System.out.println("\nLIST OF CURRENT PRODUCT INFO");
		System.out.println("=============================");
		getAllProducts();			
	}
}
