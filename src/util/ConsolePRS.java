package util;
//Console class for PRS capstone

import java.util.Scanner;

public class ConsolePRS {

	private static Scanner sc = new Scanner(System.in);

	public static void printToConsole(String str) {
		System.out.println(str);
	}

	public static String getString(String prompt) {
		System.out.print(prompt);
		String s = sc.nextLine();
//		sc.nextLine();
		return s;
	}

	
	public static String getString(String prompt, int maxLength) {
		boolean isValid = false;
		String s = "";
		while (!isValid) {
			s = getString(prompt);
			if (s.length() > maxLength) {
				System.out.println("Invalid input! Length must be <= " + maxLength);
			}
			else
				isValid = true;
		}
		return s;
	}
	
	public static String getString(String prompt, String a, String b) {
		String entry = "";
		boolean isValid = false;

		while (!isValid) {
			entry = "";
			System.out.print(prompt);
			entry = sc.nextLine();
			if (entry.equals("")) {
				System.out.println("Error! This entry is required. Try again.");
			} else if (!entry.equalsIgnoreCase(a) && !entry.equalsIgnoreCase(b)) {
				System.out.println("Error! Entry must be \"" + a + "\" or \"" + b + "\". Try again.");
			} else {
				isValid = true;
			}
		}
		return entry;
	}
	
	public static String getString(String prompt1, String prompt2, int max) {
		String entry = "";
		boolean isValid = false;

		while (!isValid) {
			entry = "";
//			System.out.println();
			System.out.print(prompt1);
			entry = sc.nextLine();
			if (entry.equals("")) {
				System.out.println("Error! This entry is required. Please try again.");
			} else if (entry.length() > max ) {
				System.out.println(prompt2 + "must be less than " + max + " characters long. Please try again.");
			} else {
				isValid = true;
			}
		}	
		return entry;	
	}


	
	
	public static boolean getBoolean(String prompt)	 {
		boolean b = false;
		boolean isValid = false;
		while(!isValid) {
			String s = getString(prompt);
			if (s.equalsIgnoreCase("t") || s.equalsIgnoreCase("true")) {
				b = true;
				isValid = true;
			}	
			else if (s.equalsIgnoreCase("f") || s.equalsIgnoreCase("false")) {
				b = false;
				isValid = true;
			}
			else
				System.out.println("Invalid input. True or False expected.");
		}
		return b;
	}
	
	public static int getInt(String prompt) {
		int i = 0;
		boolean isValid = false;
		while (!isValid) {
			System.out.print(prompt);
			if (sc.hasNextInt()) {
				i = sc.nextInt();
				isValid = true;
			} else {
				System.out.println("Error! Invalid integer. Try again.");
			}
			sc.nextLine(); // discard any other data entered on the line
		}
		return i;
	}

	public static int getInt(String prompt, int min, int max) {
		int i = 0;
		boolean isValid = false;
		while (!isValid) {
			i = getInt(prompt);
			if (i < min) {
				System.out.println("Error! Number must be greater than or equal to " + min + ".");
			} else if (i > max) {
				System.out.println("Error! Number must be less than or equal to" + max + ".");
			} else {
				isValid = true;
			}
		}
		return i;
	}


	public static String promptToCreateUsername(Scanner sc) {
		String username = "";
		boolean isValid = false;

		while (!isValid) {
			username = "";
//			System.out.println();
			System.out.print("Enter a username (20 characters maximum): ");
			username = sc.nextLine();
			if (username.equals("")) {
				System.out.println("Error! This entry is required. Please try again.");
			} else if (username.length() > 20) {
				System.out.println("Username must be less than 20 characters long. Please try again.");
			} else {
				isValid = true;
			}
		}
		return username;
	}

	public static String promptToCreatePasword(Scanner sc) {
		String password = "";
		boolean isValid = false;

		while (!isValid) {
			password = "";
//			System.out.println();
			System.out.print("Enter a password (10 characters maximum): ");
			password = sc.nextLine();
			if (password.equals("")) {
				System.out.println("Error! This entry is required. Please try again.");
			} else if (password.length() > 10) {
				System.out.println("Username must be less than 10 characters long. Please try again.");
			} else {
				isValid = true;
			}
		}
		return password;
	}

	public static String promptFirstName(Scanner sc) {
		String firstName = "";
		boolean isValid = false;

		while (!isValid) {
			firstName = "";
//			System.out.println();
			System.out.print("Enter your first name: ");
			firstName = sc.nextLine();
			if (firstName.equals("")) {
				System.out.println("Error! This entry is required. Please try again.");
			} else {
				isValid = true;
			}
		}	
		return firstName;
	}
	
	public static String promptLastName(Scanner sc) {
		String lastName = "";
		boolean isValid = false;

		while (!isValid) {
			lastName = "";
//			System.out.println();
			System.out.print("Enter your last name: ");
			lastName = sc.nextLine();
			if (lastName.equals("")) {
				System.out.println("Error! This entry is required. Please try again.");
			} else {
				isValid = true;
			}
		}	
		return lastName;
	}
		
	public static String promptPhoneNumber(Scanner sc) {
		String phone = "";
		boolean isValid = false;

		while (!isValid) {
			phone = "";
//			System.out.println();
			System.out.print("Enter your phone number (format ex: 555-555-5555): ");
			phone = sc.nextLine();
			if (phone.equals("")) {
				System.out.println("Error! This entry is required. Please try again.");
			} else if (phone.length() != 12) {
				System.out.println("Please enter phone number again using the proper format.");
			} else {
				isValid = true;
			}
		}	
		return phone;	
	}

	public static String promptEmail(Scanner sc) {
		String email = "";
		boolean isValid = false;

		while (!isValid) {
			email = "";
//			System.out.println();
			System.out.print("Enter your email: ");
			email = sc.nextLine();
			if (email.equals("")) {
				System.out.println("Error! This entry is required. Please try again.");
			} else {
				isValid = true;
			}
		}	
		return email;
	}	
	
//	public static void checkNameSpelling (String nameType, String name, Scanner sc) {
//		boolean isValid = false;
//		while(!isValid) {
//			System.out.println("Is your " + nameType + " spelled correctly?: ");
//			
//		}
//		}
//		return firstName;
//	}
	public static double getDouble(String prompt) {
		double d = 0;
		boolean isValid = false;
		while (!isValid) {
			System.out.print(prompt);
			if (sc.hasNextDouble()) {
				d = sc.nextDouble();
				isValid = true;
			} else {
				System.out.println("Error! Invalid number. Try again.");
			}
			sc.nextLine(); // discard any other data entered on the line
		}
		return d;
	}

	public static double getDouble(String prompt, double min, double max) {
		double d = 0;
		boolean isValid = false;
		while (!isValid) {
			d = getDouble(prompt);
			if (d <= min) {
				System.out.println("Error! Number must be greater than " + min + ".");
			} else if (d >= max) {
				System.out.println("Error! Number must be less than " + max + ".");
			} else {
				isValid = true;
			}
		}
		return d;
	}
	
	public static String askToContinue(Scanner sc) {
		String choice = "";
		boolean isValid = false;

		while (!isValid) {
			choice = "";
//			System.out.println();
			System.out.print("What you like to enter another user? (y/n): ");
			choice = sc.nextLine();
			if (choice.equals("")) {
				System.out.println("Error! This entry is required. Try againd.");
			} else if (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")) {
				System.out.println("Error! Entry must be \"y\" or \"n\". Try again.");
			} else {
				isValid = true;
			}
			System.out.println();
		}
		return choice;
	}
}
	


