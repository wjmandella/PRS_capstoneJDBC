package ui;

import java.time.LocalDate;
import java.util.ArrayList;

import business.Product;
import business.ProductDB;
import business.PurchaseRequest;
import business.PurchaseRequestDB;
import business.PurchaseRequestLineItem;
import business.PurchaseRequestLineItemDB;
import business.User;
import business.UserDB;
import util.ConsolePRS;

public class PRSConsoleApp {
	private static UserDB uDB = null;
	private static User validatedUser = null;
	private static PurchaseRequest purchaseRequest = null;
	private static PurchaseRequestDB prDB = null;
	private static ProductDB productDB = null; // this was previously initialized to "null"
	private static 	User loggedInUser = null;
	
	public static void main(String[] args) {
		uDB = new UserDB();
		productDB = new ProductDB();
		loggedInUser = new User();
		
		System.out.println("Welcome to the Purchase Request System (PRS)\n");

		String choice = "";

		enterNewPR();
		
		while (!choice.equalsIgnoreCase("exit")) {
			loggedInUser = null;
			System.out.println("To access the menu, please login.");
			loginUser();
				
				while (loggedInUser != null && !choice.equalsIgnoreCase("exit")) {
					displayMenu();
					choice = ConsolePRS.getString("\nOption?: ");

					if (choice.equalsIgnoreCase("newpr")) {
						enterNewPR();

					} else if (choice.equalsIgnoreCase("all")) {
						displayAllUsers();

					} else if (choice.equalsIgnoreCase("add")) {
						addUser();

					} else if (choice.equalsIgnoreCase("del")) {
						deleteUser();
					} 
					else if (choice.equalsIgnoreCase("logout")) {
						logout();
					}				
					else if (choice.equalsIgnoreCase("exit")) {
						System.out.println("\nExiting application....");
					}
					
				}	
		}
		System.out.println("\nBye! Come back soon!");
	}

	public static void displayMenu() {
		System.out.println("\nMENU\n(Please choose one of the following options.)");
		System.out.println("=============================================");
		System.out.println(" newpr - Enter a new PR");
		System.out.println("   all - Display all users");
		System.out.println("   add - Add a user");
		System.out.println("   del - Delete a user");
		System.out.println("logout - Logout current user");
		System.out.println("  exit - exit application");

	}

	public static void loginUser() {
		System.out.println("USER LOGIN");
		String username = ConsolePRS.getString("Enter username: ");
		String password = ConsolePRS.getString("Enter password: ");
		validatedUser = uDB.authenticateUser(username, password);

		while (validatedUser == null) {
			System.out.println("Login failed.....Invalid username and/or password. Please try again.");
			username = ConsolePRS.getString("Enter username: ");
			password = ConsolePRS.getString("Enter password: ");
			validatedUser = uDB.authenticateUser(username, password);

		}
		System.out.println("Username and password validated...login successful.");
		System.out.println("Welcome, " + validatedUser.getFirstName() + "!");
		loggedInUser = validatedUser;
	}

	public static void enterNewPR() {
		purchaseRequest = new PurchaseRequest();
		prDB = new PurchaseRequestDB();
		
		System.out.println("\nPlease enter the following details of your new purchase request...");
		int userId = ConsolePRS.getInt("Enter User ID: ");
		String descr = ConsolePRS.getString("Enter description (100 characters max): ", 100);
		String justif = ConsolePRS.getString("Enter justification (255 characters max): ", 255);
		String dateNeededStr = ConsolePRS.getString("Enter date needed by (format: YYYY-MM-DD): ");
		String dlvryMode = ConsolePRS.getString("Enter delivery mode (25 characters max): ", 25);
		
		LocalDate dateNeeded = LocalDate.parse(dateNeededStr);

		purchaseRequest.setUserID(userId);
		purchaseRequest.setDescription(descr);
		purchaseRequest.setJustification(justif);
		purchaseRequest.setDateNeeded(dateNeeded);
		purchaseRequest.setDeliveryMode(dlvryMode);
		purchaseRequest.setStatusID(1);
		purchaseRequest.setSubmittedDate(LocalDate.now());
		
		boolean success = prDB.addNewPRDB(purchaseRequest);
		if (success == true) {
			System.out.println("\nYour purchase request has been successfully created.");
		}
		else {
			System.out.println("Error. Purchase request was not created.");
			
		System.out.println("Now, you will need to add line items.\nYou will need to choose from the following products: ");
		}
		displayAllProducts();
		enterPRLineItems();			
	}

	public static void displayNewPRDetails() {
		String msg = "\nYour purchase request details:\n" + "Description:  " + purchaseRequest.getDescription() + "\n"
				+ "Justification: " + purchaseRequest.getJustification() + "\n" + "Date needed: "
				+ purchaseRequest.getDateNeeded() + "\n" + "Delivery mode: " + purchaseRequest.getDeliveryMode() + "\n";

		System.out.println(msg);
	}

	public static void enterPRLineItems() {
		String cont = "y";
		PurchaseRequestLineItem prli = new PurchaseRequestLineItem();
		prli = new PurchaseRequestLineItem();
		int prID = purchaseRequest.getId();
		
		while (cont.equalsIgnoreCase("y")) {
			prli.setId(prID);
			System.out.println("\nEnter the following for each PR line item:");
			int prodID = ConsolePRS.getInt("Product ID: ");
			prli.setProductID(prodID);			
			int qty = ConsolePRS.getInt("Quantity needed (of product ID: " + prodID + "): ");
			prli.setQuantity(qty);
			System.out.println(prli);
			addLineItem(prli);
			cont = ConsolePRS.getString("Do you need to enter another line item for this purchase request? (y/n): ",
					"y", "n");
		}

		PurchaseRequestLineItemDB.calculatePRLITotal(purchaseRequest, prli);
		PurchaseRequestDB.updatePurchaseRequestTotal();
		boolean success = PurchaseRequestLineItemDB.addNewPRLI(purchaseRequest);
			if (success == true) {
				System.out.println("\nYour purchase request line items have been successfully created.");
			}
			else {
				System.out.println("Error. Purchase request line item(s) was/were not created.");
			}

		System.out.println(purchaseRequest.toString());
	}

	public static void addLineItem(PurchaseRequestLineItem prli) {
		purchaseRequest.getPrLineItems().add(prli);
	}

	public static void displayAllUsers() {
		System.out.println("\nLIST OF CURRENT USERS:" + "\n");
		for (User user : uDB.getUsers()) {
			System.out.println(user);
		}
	}

	public static void addUser() {
		User u = new User ();	
		boolean success = false;
//		int id = ConsolePRS.getInt("Enter id: "); // id is auto-incremented in mySQL
		String username = ConsolePRS.getString("Enter username (20 characters max): ", 20);
		String password = ConsolePRS.getString("Create a password (10 characters max): ", "Password", 10);
		String firstName = ConsolePRS.getString("Enter user first name: ", "Your first name ", 20);
		String lastName = ConsolePRS.getString("Enter user last name: ", "Your last name ", 20);
		String phone = ConsolePRS.getString("Enter user phone number: (Ex. 504-555-5555): ", "Your phone number ", 12);
		String email = ConsolePRS.getString("Enter user email: ", "Your email ", 75);
		boolean reviewer = ConsolePRS.getBoolean("Reviewer (true or false): ");
		boolean admin = ConsolePRS.getBoolean("Admin (true or false): ");
		
		u.setUsername(username);
		u.setPassword(password);
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setPhone(phone);
		u.setEmail(email);
		u.setReviewer(reviewer);
		u.setAdmin(admin);
		System.out.println("username " +u.getUsername());
		
		success = uDB.addUser(u); 

		if (success == true) {
			System.out.println("User " + u.getUsername() + " has been successfully add to user database.\n");	
		}
		else {
			System.out.println("Error. User not added to database.");
		}
		
	}
		
	public static void deleteUser() {
		boolean success = false;
		
		String username = ConsolePRS.getString("Enter username of user to be deleted: ");
		User u = uDB.getUserByUserName(username);	
		if (u == null) {
			System.out.println("User " + username + " does not exist in database.\n");
		} else {		
			success = uDB.deleteUser(u);
			if (success == true) {
				System.out.println("User " + u.getUsername() + " has been successfully deleted from user database.\n");	
			}
			else {
				System.out.println("Error. User not deleted from database.");
			}
		}	
	}
		
	public static PurchaseRequest getPurchaseRequest() {
		return purchaseRequest;
	}

	public static void setPurchaseRequest(PurchaseRequest purchaseRequest) {
		PRSConsoleApp.purchaseRequest = purchaseRequest;
	}

	public static void displayAllProducts() {
		ArrayList<Product> list = productDB.getAllProducts();
		if (list != null && list.size() > 0) {
			for (Product p : list) {
				System.out.println(p);
			}
		} else {
			System.out.println("No data returned.");
		}
	}

	public static void setProducts(ProductDB products) {
		PRSConsoleApp.productDB = products;
	}
	
	public static void logout() {
		System.out.println("Logout successful.\n");
		loggedInUser = null;		
	}

}
