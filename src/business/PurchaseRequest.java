package business;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class PurchaseRequest {
	
	private int id;
	private int userID;
	private String description;
	private String justification;
	private LocalDate dateNeeded;
	private String deliveryMode;
	private int statusID;
	private double total;
	private LocalDate submittedDate;
	private ArrayList<PurchaseRequestLineItem> prLineItems;
	
	private static NumberFormat currency = NumberFormat.getCurrencyInstance();
	
	public PurchaseRequest() {
		
		id = 0;
		userID = 0;
		description = "";
		justification = "";
		dateNeeded = null;
		deliveryMode = "";
		statusID = 1;
		total = 0.0;
		submittedDate = null;
		setPrLineItems(new ArrayList<>());	
	}

	public PurchaseRequest(int id, int userID, String descr, String justif, 
		     LocalDate dateNeeded, String dlvryMode, int statusID, double tot, LocalDate submitDate) {
		
		this.id = id;
		this.userID = userID;
		this.description = descr;
		this.justification = justif;
		this.dateNeeded = dateNeeded;
		this.deliveryMode = dlvryMode;
		this.statusID = statusID;
		this.total = tot;
		this.submittedDate = submitDate;
		this.setPrLineItems(new ArrayList<>());	
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
	}

	public LocalDate getDateNeeded() {
		return dateNeeded;
	}
	public void setDateNeeded(LocalDate dateNeeded) {
		this.dateNeeded = dateNeeded;
	}
	public int getStatusID() {
		return statusID;
	}
	public void setStatusID(int status) {
		this.statusID = status;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public LocalDate getSubmittedDate() {
		return submittedDate;
	}
	public void setSubmittedDate(LocalDate submittedDate) {
		this.submittedDate = submittedDate;
	}
	
	public String getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public ArrayList<PurchaseRequestLineItem> getPrLineItems() {
		return prLineItems;
	}

	public void setPrLineItems(ArrayList<PurchaseRequestLineItem> prLineItems) {
		this.prLineItems = prLineItems;
	}
	
	public String toString() {
		String msg =
				"\nPURCHASE REQUEST SUMMARY\n"
				+ "ID: " + id + ", UserID: " + userID + "\n"
				+ "Description: " + description + "\n"
				+ "Justification: " + justification + "\n"
				+ "Date Needed: " + dateNeeded + "\n"
				+ "Date Submitted: " + submittedDate + "\n"
				+ "Delivery Mode: " + deliveryMode + "\n"
				+ "Status ID: " + statusID + "\n"
				+ "TOTAL: " + currency.format(total) + "\n"
				+ "PR LINE ITEMS:\n" + prLineItems;
		
		return msg;		
	}	
}
