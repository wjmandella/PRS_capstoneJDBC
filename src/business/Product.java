package business;

public class Product {
	
	private int id;
	private int vendorID;
	private String vendorPartNumber;
	private String name;
	private double price;
	private String unit;
	private String photoPath;
	
	public Product() {
		id = 0;
		vendorID = 0;
		vendorPartNumber = "";
		name = "";
		price = 0.0;
		unit = "";
		photoPath = "";
		
	}
	
	public Product(int id, int vendorID, String vendorPartNum, String name, 
									double price, String unit, String photoPath) {
		this.id = id;
		this.vendorID = vendorID;
		this.vendorPartNumber = vendorPartNum;
		this.name = name;
		this.price = price;
		this.unit = unit;
		this.photoPath = photoPath;		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVendorID() {
		return vendorID;
	}

	public void setVendorID(int vendorID) {
		this.vendorID = vendorID;
	}

	public String getVendorPartNumber() {
		return vendorPartNumber;
	}

	public void setVendorPartNumber(String vendorPartNumber) {
		this.vendorPartNumber = vendorPartNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
	public String toString() {
		String msg =
				"PRODUCT: [ID: " + id + ", Vendor ID: " + vendorID + ", "
				+ "Vendor Part Number: " + vendorPartNumber + ", Name: " + name
				+ ", Price: " +price + ", Unit: " + unit + ", Photopath: " + photoPath +"]";
		return msg;		
	}
	
//	public String toString() {
//		String msg =
//				"PRODUCT:\n"
//				+ "ID: " + id + ", Vendor ID: " + vendorID + "\n"
//				+ "Vendor Part Number: " + vendorPartNumber + ", Name: " + name + "\n"
//				+ "Price: " +price + ", Unit: " + unit + "\n"
//				+"Photopath: " + photoPath;
//		return msg;		
//	}
	
}


