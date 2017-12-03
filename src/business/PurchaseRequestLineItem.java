package business;

public class PurchaseRequestLineItem {
	
	private int id;
	private int purchaseRequestID;
	private int productID;
	private int quantity;
	
	public PurchaseRequestLineItem() {
		id = 0;
		purchaseRequestID = 0;
		productID = 0;
		quantity = 0;			
	}

	public PurchaseRequestLineItem(int id, int prID, int prodID, int qty) {
		this.id = id;
		this.purchaseRequestID = prID;
		this.productID = prodID;
		this.quantity = qty;		
	}
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPurchaseRequestID() {
		return purchaseRequestID;
	}

	public void setPurchaseRequestID(int purchaseRequestID) {
		this.purchaseRequestID = purchaseRequestID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String toString() {
		String msg =
				" Product ID: " + productID +  ", Quantity: " + quantity
				+ "  (ID: " + id + ", PR ID: " + purchaseRequestID + ") ";
		return msg;
	}	
}
