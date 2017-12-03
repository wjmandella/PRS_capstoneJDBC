package ui;

import java.util.ArrayList;

import business.Status;
import business.StatusDB;
import util.ConsolePRS;


public class StatusApp {

	public static void main(String[] args) {
		
		System.out.println("Welcome to the Status app");
		
		String choice = "";
		
		while (!choice.equalsIgnoreCase("exit")) {
			System.out.println("Options");
			System.out.println("all - Get all status");
			System.out.println("get - Get a single status by id");
			System.out.println("exit - exit app");
			choice = ConsolePRS.getString("Option?: ");
			if (choice.equalsIgnoreCase("all")) {
				getAllStatus();
			}
			else if (choice.equalsIgnoreCase("get")) {
				getStatus();
			}
		}
		System.out.println("Bye");
	}
	
	private static void getAllStatus() {
		StatusDB sdb = new StatusDB();
		ArrayList<Status> list = sdb.getAll();
		if (list != null && list.size() > 0) {
			for (Status s: list) {
				System.out.println(s);
			}			
		}
		else {
			System.out.println("No data returned.");
		}
	}
	
	private static void getStatus() {
		int statusID = ConsolePRS.getInt("Enter status ID: ");
		StatusDB sdb = new StatusDB();
		Status s =sdb.getStatus(statusID);
		if (s != null) {
				System.out.println(s);			
		}
		else {
			System.out.println("No status ID for " + statusID);
		}
	}

}
