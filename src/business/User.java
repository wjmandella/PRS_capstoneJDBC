package business;

import java.time.LocalDateTime;

public class User {
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private boolean reviewer;
	private boolean admin;
	private LocalDateTime dateCreated;
	
	public User() {
		id = 0;
		username ="";
		password = "";
		firstName = "";
		lastName = "";
		phone = "";
		email = "";
		reviewer = false;
		admin = false;		
	}
		
	public User(int id, String un, String pw, String fN, String lN, String pn, String e, boolean m, boolean a) {
		setId(id);
		setUsername(un);
		setPassword(pw);
		setFirstName(fN);
		setLastName(lN);
		setPhone(pn);
		setEmail(e);
		setReviewer(m);
		setAdmin(a);	
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isReviewer() {
		return reviewer;
	}
	public void setReviewer(boolean reviewer) {
		this.reviewer = reviewer;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime createDateT) {
		this.dateCreated = createDateT;
	}

	@Override  //helpful annotation
	public String toString() {
		String msg =
				  "-----------------------------------------------------\n"
		        + "---- User Information--------------------------------\n"
		        + "-----------------------------------------------------\n"
		        + "id: " + id + "\n"
		        + "username:\t" + getUsername() + "\n"
		        + "password:\t" + getPassword() + "\n"
				+ "Name:\t\t" + getFirstName() + " " + getLastName() + "\n"
		        + "Email Address:\t" + getEmail() + "\n"
		        + "Phone Number:\t" + getPhone() + "\n"
				+ "Reviewer: " + reviewer + "\n"
				+ "Admin: " + admin + "\n";
		return msg;				
	}	
	
}
