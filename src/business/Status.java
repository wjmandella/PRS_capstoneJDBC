package business;

public class Status {
	
	private int id;
	private String description;
	
	public Status() {
		id = 0;
		description ="";		
	}
	
	public Status (int id, String desc) {
		this.id = id;
		this.description = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		String msg =
				"STATUS:\n"
				+ "id: " + id + ", description: " + description;  
		return msg;
	}
	
}
