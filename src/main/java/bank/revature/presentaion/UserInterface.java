package bank.revature.presentaion;

import java.text.DecimalFormat;
import java.util.Scanner;

import bank.revature.models.Account;
import bank.revature.models.Customer;
import bank.revature.models.Employee;
import bank.revature.models.User;
import bank.revature.service.Service;

public abstract class UserInterface extends Service{
	
	// Holds methods that handle user interaction
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 		
	 * 
	 */
	


	// Variables
	private static Service bank;
	private static Scanner io;
	private static DecimalFormat dollarFormat = new DecimalFormat("#.00");
	
// Methods
	
	
	
	
	// Engine Methods
	public static void startEngine() {
		bank = new Service();
		io = new Scanner(System.in);
		String inputString = "";
		boolean keepRunning = true;
		int switcher = -1;
		
		do {
			// Display Prompt
			System.out.println("------------------------------");
			System.out.println("---- Banking Service 1000 ----");
			System.out.println("------------------------------");
			System.out.println();
			System.out.println(" [1] New User");
			System.out.println(" [2] Existing User");
			System.out.println();
			System.out.println(" [0] Exit");
			System.out.println();
			System.out.println("Enter the number corresponding to your selection");
			// Get Input
			inputString = io.nextLine();
			try {
				switcher = Integer.parseInt(inputString);
		    } catch (NumberFormatException e) {
		        switcher = -1;
		    }
			clearScreen();
			
			switch (switcher) {
			case 1: // New User
				userCreation();
				break;
			case 2: // Existing User
				logIn();
				break;
			case 0: // Exit
				keepRunning = false;
				System.out.println("\n\tExiting...\n");
				System.out.println("\tThank you for using Banking Service 1000!");
				break;
			default:
				System.out.println("\nInput not recognized\n");
				break;
			}
		} while (keepRunning);
	}
	
	public static void customerEngine(Customer cus) {
		Account[] acc = new Account[2];
		String inputString = null;
		int switcher = 0;
		boolean keepRunning = true;
		int numOfAccounts = bank.getAccountsByOwner(cus.getUserName()).size();
		
		if (numOfAccounts == 0) { // If the customer has no accounts and no account applications
			System.out.println("You do not have any open accounts, please apply for an account.");
			applyForAccount(cus);
			System.out.println("Your application will be processed as soon as possible.");
		}
		else if // If the customer has no accounts but has pending account applications
		(numOfAccounts == 1 && ((Account) bank.getAccountsByOwner(cus.getUserName()).toArray()[0]).getFlag().equals("App"))
			System.out.println("Your account has not been approved yet, please try again later.");
		else if // If the customer has 1 or more accounts and at least 1 has already been approved
		(numOfAccounts >= 1) {
			do {
				clearScreen();
				System.out.println("------------------------------");
				System.out.println("---- Banking Service 1000 ----");
				System.out.println("------------------------------");
				System.out.println();
				System.out.println("\tWelcome " + cus.getFirstName() + "!");
				System.out.println();
				System.out.println(" [1] Inquiry");
				System.out.println(" [2] Withdraw");
				System.out.println(" [3] Deposit");
				System.out.println(" [4] Transfer");
				System.out.println(" [5] Apply for new account");
				System.out.println();
				System.out.println(" [0] Logout");
				System.out.println();
				System.out.println("Enter the number corresponding to your selection:");
				
				inputString = io.nextLine();
				clearScreen();
				try {
					switcher = Integer.parseInt(inputString);
			    } catch (NumberFormatException e) {
			        switcher = -1;
			    }

				clearScreen();
				System.out.println();
				switch (switcher) {
				case 1: // Inquiry
					System.out.println("Which account would you like to view?");
					acc[0] = chooseAccount(cus);
					if (acc[0] != null)
						inquiry(acc[0]);
					clearScreen();
					break;
				case 2: // Withdraw
					System.out.println("Which account would you like to withdraw from?");
					acc[0] = chooseAccount(cus);
					if (acc[0] != null)
						withdraw(acc[0]);
					clearScreen();
					break;
				case 3: // Deposit
					System.out.println("Which account would you like to deposit into?");
					acc[0] = chooseAccount(cus);
					if (acc[0] != null)
						deposit(acc[0]);
					clearScreen();
					break;
				case 4: // Transfer
					System.out.println("Which account would you like to transfer from?");
					acc[0] = chooseAccount(cus);
					System.out.println("Which account would you like to transfer into?");
					acc[1] = chooseAccount(cus);
					if (acc[0] != null && acc[1] != null )
						transfer(acc[0], acc[1]);
					clearScreen();
					break;
				case 5: // Apply for new account
					clearScreen();
					System.out.println("\tAccount Application - ");
					applyForAccount(cus);
					clearScreen();
					break;
				case 0: // Logout
					clearScreen();
					System.out.println("\tLogging Out...\n");
					logOut(cus);
					keepRunning = false;
					break;
				default: //Invalid Input
					keepRunning = true; 
					System.out.println("\tInput not recognized\n");
					break;
				}
			} while (keepRunning);
		}
	}
	
	public static void employeeEngine(Employee emp) {
		String inputString = null;
		int switcher = 0;
		boolean keepRunning = true;
		
		if (emp.getUserType() == 'e') { // If employee is not admin
			do {
				System.out.println("------------------------------");
				System.out.println("---- Banking Service 1000 ----");
				System.out.println("------------------------------");
				System.out.println();
				System.out.println("Employee: " + emp.getUserName());
				System.out.println();
				System.out.println(" [1] View Accounts"); // Can view all accounts or query based on account info
				System.out.println(" [2] View Customers"); // Can view all customers or query based on user info
				System.out.println(" [3] Access Account");
				System.out.println(" [4] Access Customer");
				System.out.println();
				System.out.println(" [0] Logout");
				System.out.println();
				System.out.println("Enter the number corresponding to your selection:");
				
				inputString = io.nextLine();
				clearScreen();
				try {
					switcher = Integer.parseInt(inputString);
			    } catch (NumberFormatException e) {
			        switcher = -1;
			    }
				
				switch (switcher) {
				case 1: // View Accounts
					viewAllAccounts();
					break;
				case 2: // View Customers
					viewAllCustomers();
					break;
				case 3: // Access Account
					viewAllAccounts();
					accessAccount(chooseAccount());
					break;
				case 4: // Access Customer
					accessCustomer(chooseCustomer());
					break;
				case 0: // Logout
					System.out.println("\tLogging Out...\n");
					logOut(emp);
					keepRunning = false;
					break;
				default: //Invalid Input
					System.out.println("\tInput not recognized\n");
					break;
				}
			} while (keepRunning);
		}
		else if (emp.getUserType() == 'a') { // If Employee is Admin
			do {
				System.out.println("------------------------------");
				System.out.println("---- Banking Service 1000 ----");
				System.out.println("------------------------------");
				System.out.println();
				System.out.println("Admin: " + emp.getUserName());
				System.out.println();
				System.out.println(" [1] View Accounts"); // Can view all accounts or query based on account info
				System.out.println(" [2] View Customers"); // Can view all customers or query based on customer info
				System.out.println(" [3] View Employees"); // Can view all employees or query based on employee info
				System.out.println(" [4] Access Account");
				System.out.println(" [5] Access Customer");
				System.out.println(" [6] Access Employee");
				System.out.println(" [7] Terminate Account");
				System.out.println(" [8] Terminate Customer");
				System.out.println(" [9] Terminate Employee");
				System.out.println();
				System.out.println(" [0] Logout");
				System.out.println();
				System.out.println("Enter the number corresponding to your selection:");
				
				inputString = io.nextLine();
				clearScreen();
				try {
					switcher = Integer.parseInt(inputString);
			    } catch (NumberFormatException e) {
			        switcher = -1;
			    }
				
				switch (switcher) {
				case 1: // View Accounts
					viewAllAccounts();
					break;
				case 2: // View Customers
					viewAllCustomers();
					break;
				case 3: // View Employees
					viewAllEmployees();
					break;
				case 4: // Access Account
					viewAllAccounts();
					accessAccount(chooseAccount());
					break;
				case 5: // Access Customer
					accessCustomer(chooseCustomer());
					break;
				case 6: // Access Employee
					accessEmployee(chooseEmployee());
					break;
				case 7: // Terminate Account
					viewAllAccounts();
					bank.removeAccount(chooseAccount());
					break;
				case 8: // Terminate Customer
					bank.removeCustomer(chooseCustomer());
					break;
				case 9: // Terminate Employee
					bank.removeEmployee(chooseEmployee());
					break;
				case 0: // Logout
					System.out.println("\tLogging Out...\n");
					logOut(emp);
					keepRunning = false;
					break;
				default: //Invalid Input
					System.out.println("\tInput not recognized\n");
					break;
				}
			} while (keepRunning);
		}
	}
	
	
	
	
	// User Methods
	public static void userCreation() {
		User user = null;
		String username = null;
		String password = null;
		String inputString = "";
		boolean keepLooping = true;
		
		// New customer users can create their own Customer object
		// New employees must be added by an admin? 
		// Admin is special employee, must be made admin by existing admin
		
		System.out.println("------------------------------");
		System.out.println("---------- New User ----------");
		System.out.println("------------------------------");
		System.out.println();
		
		// Creating Username
		do {
			System.out.println("Please enter a username:");
			inputString = io.nextLine();
			username = inputString;
			System.out.println("Please verify username:");
			inputString = io.nextLine();
			if (!username.equals(inputString)) {
				System.out.println("These usernames do not match");
				keepLooping = true;
			}
			else if (bank.getUser(inputString) != null) {
				System.out.println("That username is already in use");
				keepLooping = true;
			}
			else {
				keepLooping = false;
			}
			
		} while (keepLooping);
		
		// Creating Password
		do {
			System.out.println("Please enter a password:");
			inputString = io.nextLine();
			password = inputString;
			System.out.println("Please verify password:");
			inputString = io.nextLine();
			if (!password.equals(inputString)) {
				System.out.println("These passwords do not match");
				keepLooping = false;
			}
			else {
				keepLooping = false;
			}
		} while (keepLooping);
			
		// Create User object with given info
		user = new User(username, password);
		
		// Setting User Information
		System.out.println("Please enter your first name: ");
		user.setFirstName(io.nextLine());
		System.out.println("Please enter your last name: ");
		user.setLastName(io.nextLine());
		System.out.println("Please enter your address: ");
		user.setAddress(io.nextLine());
		System.out.println("Please enter your phone: ");
		user.setPhone(io.nextLine());
		System.out.println("Please enter your email: ");
		user.setEmail(io.nextLine());
		
		// Setting User Type
		do {
			System.out.println("Are you a new customer or employee? [C] or [E]");
			inputString = io.nextLine();
			if (inputString.toUpperCase().equals("C")) {
				user.setUserType('c');
				Customer cus = new Customer(user);
				bank.addUser(cus);
				keepLooping = false;
				System.out.println("Customer user created");
			}
			else if (inputString.toUpperCase().equals("E")) {
				user.setUserType('e');
				Employee emp = new Employee(user);
				bank.addUser(emp);
				keepLooping = false;
				System.out.println("Employee user created");
			}
			else {
				System.out.println("Invalid Input");
				keepLooping = true;
			}
		} while (keepLooping);
		clearScreen();
	}
	
	public static void logIn() {
		User loggedInUser = null;
		boolean keepLooping = true;
		String username = null;
		String password = null;
		
		User temp = new User();
		
//		viewAllCustomers(); // FOR TESTING
//		viewAllEmployees(); // FOR TESTING
		
		System.out.println("------------------------------");
		System.out.println("-------- User Log In ---------");
		System.out.println("------------------------------");
		System.out.println();
		
		System.out.println("Please enter your username: ");
		username = io.nextLine();
		if (bank.getUser(username) == null) {
			clearScreen();
			System.out.println("No user found with that username\n");
		}
		else {
			temp = bank.getUser(username);
			do {
				System.out.println("Please enter your password: ");
				password = io.nextLine();
				if (temp.verify(username, password) == true) {
					loggedInUser = temp;
					clearScreen();
					System.out.println("\tLogin Successful");
					keepLooping = false;
				}
				else if (temp.verify(username, password) == false) {
					System.out.println("\tIncorrect Password");
					keepLooping = true;
				}
			} while (keepLooping);
			
			if (loggedInUser.getUserType() == 'c') 
				customerEngine((Customer)loggedInUser);
			else if (loggedInUser.getUserType() == 'e' || loggedInUser.getUserType() == 'a') 
				employeeEngine((Employee)loggedInUser);
		}
	}
	
	public static void logOut(User u) {
		clearScreen();
		System.out.println("\tGoodbye " + u.getFirstName() + "!");
	}
	
	
	
	// Modular Methods
	private static void viewCustomerAccounts(Customer cus) {
		for (Account a : bank.getAccountsByOwner(cus.getUserName())) {
			if (!a.getFlag().equals("") && !a.getFlag().equals(null))
				System.out.print("{" + a.getFlag() + "} ");
			System.out.println("\t[" + a.getID() + "] " + a.getType() + " - " + a.getOwners());
		}
	}
	
	private static void viewAllAccounts() {
		for (Account a : bank.getAccounts()) {
			if (!a.getFlag().equals("") && !a.getFlag().equals(null))
				System.out.print("{" + a.getFlag() + "} ");
			System.out.println("\t[" + a.getID() + "] " + a.getType() + " - " + a.getOwners());
		}
	}
	
	private static void viewAllCustomers() {
		for (Customer c : bank.getCustomers())
			System.out.println("\t[" + c.getUserName() + "] " + c.getFirstName() + " " + c.getLastName());
	}
	
	private static void viewAllEmployees() {
		for (Employee e : bank.getEmployees())
			System.out.println("\t[" + e.getUserName() + "] {" + Character.toUpperCase(e.getUserType()) +"} " + e.getFirstName() + " " + e.getLastName() + " - " + e.getPosition() + " at " + e.getBranch());
	}
	
	private static Customer chooseCustomer() {
		Customer toReturn = null;
		String inputString = null;
		boolean looper = true;
		
		do {
			viewAllCustomers();
			System.out.println("Enter the username of the customer you wish to choose: ");
			inputString = io.nextLine();

			if (bank.getUser(inputString) != null) {
				toReturn = (Customer) bank.getUser(inputString);
				looper = false;
			}
			else if (inputString.equals("0"))
				looper = false;
			else
				System.out.println("Error: could not find a customer with that username...");
				
		} while (looper);
		
		return toReturn;
	}
	
	private static Employee chooseEmployee() {
		Employee toReturn = null;
		String inputString = null;
		boolean looper = true;
		
		do {
			viewAllEmployees();
			System.out.println("Enter the username of the employee you wish to choose: ");
			inputString = io.nextLine();

			if (bank.getUser(inputString) != null) {
				toReturn = (Employee) bank.getUser(inputString);
				looper = false;
			}
			else if (inputString.equals("0"))
				looper = false;
			else
				System.out.println("Error: could not find an employee with that username...");
				
		} while (looper);
		
		return toReturn;
	}
	
	private static Account chooseAccount() {
		Account toReturn = null;
		String inputString = null;
		boolean looper = true;
		
		do {
			System.out.println("Enter the number of the account you wish to choose: ");
			inputString = io.nextLine();
			if (bank.getAccountByID(inputString) != null) {
				toReturn = bank.getAccountByID(inputString);
				looper = false;
			}
			else if (inputString.equals("0"))
				looper = false;
			else
				System.out.println("Error: could not find an account with that number...");
				
		} while (looper);
		
		return toReturn;
	}

	private static Account chooseAccount(Customer cus) {
		Account toReturn = new Account();
		int i = 0;
		int in = -1;
		
		String inputString = null;
		boolean looper = true;
		
		do { // Choose account to withdraw from
			for (Account a : bank.getAccountsByOwner(cus.getUserName())) {
				if (!a.getFlag().equals("App")) {
					i++;
					System.out.println("\t[" + i + "] " + a.getType() + " - " + a.getID());
				}
			}
			inputString = io.nextLine();
			try {
				in = Integer.parseInt(inputString);
		    } catch (NumberFormatException e) {
		        in = 0;
		    }
			if (in > 0 && in <= i) { // If input is between 1 and the total number of this users accounts
				toReturn = (Account) bank.getAccountsByOwner(cus.getUserName()).toArray()[in - 1];
				looper = false;
			}
			else if (in == 0)
				looper = false;
			else {
				System.out.println("Invalid Input");
				looper = true;
			}
		} while (looper);
		
		return toReturn;
	}
	
	private static void inquiry(Account a) {
		clearScreen();
		if (!a.getFlag().equals("App")) {
			System.out.println("Account: " + a.getType());
			System.out.println("Balance: $" + dollarFormat.format(Service.getBalance(a)));
			System.out.println("Press 'Enter' to continue.");
			io.nextLine();
		}
		else
			System.out.println("This account has not been approved yet. Try again later...");
	}
	
	private static void withdraw(Account acc) {
		String inputString = null;
		double amount = 0.0;
		boolean looper = true;

		clearScreen();
		if (!acc.getFlag().equals("App"))
		withdrawLoop : do { // Choose amount to withdraw
			System.out.println("Account: " + acc.getType());
			System.out.println("Balance: $" + dollarFormat.format(Service.getBalance(acc)));
			System.out.println("Our system only processes withdrawals of up to $10,000.");
			System.out.println("Enter '0' to exit without withdrawing.");
			System.out.println("Enter the amount you would like to withdraw: ");
			inputString = io.nextLine();
			try {
				amount = Double.parseDouble(inputString);
		    } catch (NumberFormatException e) {
		    	amount = 0;
				System.out.println("\tInput Error - Backing Out");
		    }
			if (amount == 0) {
				looper = false;
				break withdrawLoop;
			}
			else if (amount > 0 || amount <= 10000) {
				if (Service.withdraw(acc, amount)) {
					clearScreen();
					System.out.println("\t$\t\t     $");
					System.out.println("\t\t$" + amount);
					System.out.println("\t$\t\t     $");
					System.out.println();
					System.out.println("\nPlease do not forget to retrieve your money!");
				}
				System.out.println("Press 'Enter' to continue.");
				io.nextLine();
				looper = false;
			}
			else if (amount < 0) {
				System.out.println("You entered a negative number.");
				System.out.println("If you want to deposit money please use that feature...");
				looper = true;
			}
			else if (amount > 10000) {
				System.out.println("The amount requested was over $10,000 which is too much.");
				System.out.println("Try a lesser amount...");
				looper = true;
			}
			else {
				System.out.println("Invalid Input");
				looper = true;
			}
		} while (looper);
		else
			System.out.println("This account has not been approved yet. Try again later...");
	}
	
	private static void deposit(Account acc) {
		String inputString = null;
		double amount = 0.0;
		boolean looper = true;

		clearScreen();
		if (!acc.getFlag().equals("App"))
		depositLoop : do { // Choose amount to deposit
			System.out.println("Account: " + acc.getType());
			System.out.println("Balance: $" + dollarFormat.format(Service.getBalance(acc)));
			System.out.println("Our system only processes deposits of up to $10,000.");
			System.out.println("Enter '0' to exit without depositing.");
			System.out.println("Enter the amount you would like to deposit: ");
			inputString = io.nextLine();
			try {
				amount = Double.parseDouble(inputString);
		    } catch (NumberFormatException e) {
				System.out.println("\tInput Error - Backing Out");
		    	amount = 0;
		    }
			if (amount == 0) {
				looper = false;
				break depositLoop;
			}
			else if (amount > 0 || amount <= 10000) {
				System.out.println("\t$\t\t     $");
				System.out.println("\t\t$" + amount);
				System.out.println("\t$\t\t     $");
				Service.deposit(acc, amount);
				System.out.println("\nYour deposit has been received!");
				System.out.println("Press 'Enter' to continue.");
				io.nextLine();
				looper = false;
			}
			else if (amount < 0) {
				System.out.println("You entered a negative number.");
				System.out.println("If you want to withdraw money please use that feature...");
				looper = true;
			}
			else if (amount > 10000) {
				System.out.println("The amount entered was over $10,000 which is too much.");
				System.out.println("Try a lesser amount...");
				looper = true;
			}
			else {
				System.out.println("Invalid Input");
				looper = true;
			}
		} while (looper);
		else
			System.out.println("This account has not been approved yet. Try again later...");
	}
	
	private static void transfer(Account from, Account to) {
		String inputString = null;
		double amount = 0.0;
		boolean looper = true;

		clearScreen();
		if (!from.getFlag().equals("App") && !to.getFlag().equals("App"))
		transferLoop : do { // Choose amount to transfer
			System.out.println("Account: " + from.getType());
			System.out.println("Balance: $" + dollarFormat.format(Service.getBalance(from)));
			System.out.println("Account: " + to.getType());
			System.out.println("Balance: $" + dollarFormat.format(Service.getBalance(to)));
			System.out.println("Our system only processes transfers of up to $10,000.");
			System.out.println("Enter '0' to exit without transfering.");
			System.out.println("Enter the amount you would like to transfer: ");
			inputString = io.nextLine();
			try {
				amount = Double.parseDouble(inputString);
		    } catch (NumberFormatException e) {
				System.out.println("\tInput Error - Backing Out");
		    	amount = 0;
		    }
			if (amount == 0) {
				looper = false;
				break transferLoop;
			}
			else if (amount > 0 || amount <= 10000) {
				if (Service.withdraw(from,amount)) {
					clearScreen();
					System.out.println(">>>>>\t\t$\t\t     $\t\t>>>>>");
					System.out.println("\t\t\t$" + amount);
					System.out.println(">>>>>\t\t$\t\t     $\t\t>>>>>");
					System.out.println();
					System.out.println("\nTransfer complete!");
					System.out.println("Press 'Enter' to continue.");
					io.nextLine();
				}
				Service.deposit(to, amount);
				looper = false;
			}
			else if (amount < 0) {
				System.out.println("You entered a negative number.");
				System.out.println("Try a greater amount...");
				looper = true;
			}
			else if (amount > 10000) {
				System.out.println("The amount entered was over $10,000 which is too much.");
				System.out.println("Try a lesser amount...");
				looper = true;
			}
			else {
				System.out.println("Invalid Input");
				looper = true;
			}
		} while (looper);
		else
			System.out.println("One of these accounts has not been approved yet. Try again later...");
	}
	
	private static void applyForAccount(Customer cus) {
		Account acc = null;
		String inputString = null;
		String type = null;
		boolean looper = true;
		
		do { // Choose account type
			System.out.println("What type of account would you like?");
			System.out.println("\t[C] Checking");
			System.out.println("\t[S] Saving");
			inputString = io.nextLine();
			if (inputString.toUpperCase().equals("C")) {
				type = "Checking";
				looper = false;
			}
			else if (inputString.toUpperCase().equals("S")) {
				type = "Saving";
				looper = false;
			}
			else {
				System.out.println("Invalid Input");
				looper = true;
			}
		} while (looper);
		
		acc = new Account(cus.getUserName(), type, "App", 0.0);
		bank.addAccount(acc);
	}
	
	private static void accessAccount(Account acc) {
		String inputString = null;
		int switcher = -1;
		int inputInt = -1;
		boolean keepRunning = true;
		boolean looper = true;
		
		viewAccLoop : do {
			clearScreen();
			System.out.println("------------------------------");
			System.out.println("---- Banking Service 1000 ----");
			System.out.println("------------------------------");
			System.out.println();
			System.out.println("Account: [" + acc.getID() + "] " + acc.getType() + " - " + acc.getFlag());
			System.out.println("Owners: " + acc.getOwners());
			System.out.println();
			System.out.println("Balance: " + dollarFormat.format(Service.getBalance(acc)));
			System.out.println();
			System.out.println(" [1] Add or Remove Owner");
			System.out.println(" [2] Withdraw");
			System.out.println(" [3] Deposit");
			System.out.println(" [4] Transfer");
			if (acc.getFlag().equals("App"))
				System.out.println(" [5] Process Application");
			System.out.println();
			System.out.println(" [0] Back");
			System.out.println();
			System.out.println("Enter the number corresponding to your selection:");
			
			inputString = io.nextLine();
			clearScreen();
			try {
				switcher = Integer.parseInt(inputString);
		    } catch (NumberFormatException e) {
		    	switcher = 0;
		    }
			
			switch (switcher) {
			case 1: // Modify
				clearScreen();
				System.out.println("Account: [" + acc.getID() + "] " + acc.getType() + " - " + acc.getFlag());
				System.out.println("Owners: " + acc.getOwners());
				System.out.println();
				System.out.println(" [1] Add Owner");
				System.out.println(" [2] Remove Owner");
				System.out.println();
				System.out.println(" [0] Back");
				System.out.println("Enter the number corresponding to your selection:");
				inputString = io.nextLine();
				try {
					inputInt = Integer.parseInt(inputString);
			    } catch (NumberFormatException e) {
			    	inputInt = -1;
			    }
				if (inputInt == 1) {
					System.out.println("Enter the username of the user to be added: ");
					inputString = io.nextLine();
					if (!bank.getUser(inputString).equals(null))
						acc.getOwners().add(inputString);
					else
						System.out.println("No user found with that username!");
				}
				else if (inputInt == 2) {
					System.out.println("Enter the username of the user to be removed: ");
					inputString = io.nextLine();
					if (!bank.getUser(inputString).equals(null))
						acc.getOwners().remove(inputString);
					else
						System.out.println("No user found with that username!");
				}
				else if (inputInt == 0) {
					looper = false;
				}
				else { //Invalid Input
					looper = true; 
					System.out.println("\nInput not recognized\n");
					break;
				}
				break;
			case 2: // Withdraw
				withdraw(acc);
				break;
			case 3: // Deposit
				deposit(acc);
				break;
			case 4: // Transfer
				System.out.println("Which account would you like to transfer into?");
				transfer(acc, chooseAccount());
				break;
			case 5: // Process Application
				while (looper) {
					System.out.println(" [1] Approve Application");
					System.out.println(" [2] Terminate Application");
					System.out.println();
					System.out.println(" [0] Back");
					System.out.println("Enter the number corresponding to your selection:");
					inputString = io.nextLine();
					try {
						inputInt = Integer.parseInt(inputString);
				    } catch (NumberFormatException e) {
				    	inputInt = -1;
				    }
					if (inputInt == 1) {
						System.out.println("\tApplication approved.");
						acc.setFlag("");
						looper = false;
					}
					else if (inputInt == 2) {
						System.out.println("\tApplication terminated.");
						bank.removeAccount(acc);
						acc = null;
						looper = false;
						break viewAccLoop;
					}
					else if (inputInt == 0) {
						looper = false;
					}
					else { //Invalid Input
						looper = true; 
						System.out.println("\nInput not recognized\n");
						break;
					}
				}
				break;
			case 0: // Back
				clearScreen();
				keepRunning = false;
				break;
			default: //Invalid Input
				keepRunning = true; 
				System.out.println("\nInput not recognized\n");
				break;
			}
			clearScreen();
		} while (keepRunning);
	}
	
	private static void accessCustomer(Customer cus) {
		String inputString = null;
		int switcher = -1;
		int inputInt = -1;
		boolean keepRunning = true;
		boolean looper = true;
		
		do {
			clearScreen();
			System.out.println("------------------------------");
			System.out.println("---- Banking Service 1000 ----");
			System.out.println("------------------------------");
			System.out.println();
			System.out.println("Customer: [" + cus.getUserName() + "] " + cus.getFirstName() + " " + cus.getLastName() + " - " + cus.getFlag());
			System.out.print("Accounts: ");
			for (Account a : bank.getAccountsByOwner(cus.getUserName()))
				System.out.print("[" + a.getID() + "] ");
			System.out.println();
			System.out.println();
			System.out.println(" [1] Add or Remove Account");
			System.out.println(" [2] Change Information");
			System.out.println();
			System.out.println(" [0] Back");
			System.out.println();
			System.out.println("Enter the number corresponding to your selection:");
			
			inputString = io.nextLine();
			clearScreen();
			try {
				switcher = Integer.parseInt(inputString);
		    } catch (NumberFormatException e) {
		    	switcher = 0;
		    }
			
			switch (switcher) {
			case 1: // Add or Remove Account
				clearScreen();
				System.out.println("Customer: [" + cus.getUserName() + "] " + cus.getFirstName() + " " + cus.getLastName() + " - " + cus.getFlag());
				System.out.print("Accounts: ");
				for (Account acc : bank.getAccountsByOwner(cus.getUserName()))
					System.out.print("[" + acc.getID() + "] ");
				System.out.println();
				System.out.println();
				System.out.println(" [1] Add Account");
				System.out.println(" [2] Remove Account");
				System.out.println();
				System.out.println(" [0] Back");
				System.out.println();
				System.out.println("Enter the number corresponding to your selection:");
				inputString = io.nextLine();
				try {
					inputInt = Integer.parseInt(inputString);
			    } catch (NumberFormatException e) {
			    	inputInt = -1;
			    }
				if (inputInt == 1) {
					System.out.println("Enter the account number of the account to be added: ");
					inputString = io.nextLine();
					if (!bank.getAccountByID(inputString).equals(null)) {
						bank.getAccountByID(inputString).addOwner(cus.getUserName());
					}
					else
						System.out.println("No user found with that username!");
				}
				else if (inputInt == 2) {
					System.out.println("Enter the account number of the account to be removed: ");
					inputString = io.nextLine();
					if (!bank.getAccountByID(inputString).equals(null)) {
						bank.getAccountByID(inputString).removeOwner(cus.getUserName());
					}
					else
						System.out.println("No user found with that username!");
				}
				else if (inputInt == 0) {
					looper = false;
				}
				else { //Invalid Input
					looper = true; 
					System.out.println("\nInput not recognized\n");
					break;
				}
				clearScreen();
				break;
			case 2: // Change Information
				while (looper) {
					System.out.println(" [1] Reset Password");
					System.out.println(" [2] Change First Name");
					System.out.println(" [3] Change Last Name");
					System.out.println(" [4] Change Street Address");
					System.out.println(" [5] Change Phone Number");
					System.out.println(" [6] Change Email Address");
					System.out.println();
					System.out.println(" [0] Back");
					System.out.println("Enter the number corresponding to your selection:");
					inputString = io.nextLine();
					try {
						inputInt = Integer.parseInt(inputString);
				    } catch (NumberFormatException e) {
				    	inputInt = -1;
				    }
					
					switch (inputInt) {
					case 1 : // Reset Password
						cus.setFlag("ResetPassword");
						break;
					case 2 : // Change First Name
						System.out.println("Current First Name: " + cus.getFirstName());
						System.out.println();
						System.out.print("New First Name: ");
						inputString = io.nextLine();
						cus.setFirstName(inputString);
						break;
					case 3 : // Change Last Name
						System.out.println("Current Last Name: " + cus.getLastName());
						System.out.println();
						System.out.print("New Last Name: ");
						inputString = io.nextLine();
						cus.setLastName(inputString);
						break;
					case 4 : // Change Street Address
						System.out.println("Current Street Address: " + cus.getAddress());
						System.out.println();
						System.out.print("New Street Address: ");
						inputString = io.nextLine();
						cus.setAddress(inputString);
						break;
					case 5 : // Change Phone Number
						System.out.println("Current Phone Number: " + cus.getPhone());
						System.out.println();
						System.out.print("New Phone Number: ");
						inputString = io.nextLine();
						cus.setPhone(inputString);
						break;
					case 6 : // Change Email Address
						System.out.println("Current Email Address: " + cus.getEmail());
						System.out.println();
						System.out.print("New Email Address: ");
						inputString = io.nextLine();
						cus.setEmail(inputString);
						break;
					case 0: // Back
						clearScreen();
						keepRunning = false;
						break;
					default: //Invalid Input
						keepRunning = true; 
						System.out.println("\nInput not recognized\n");
						break;
					}
					clearScreen();
				}
				break;
			case 0: // Back
				clearScreen();
				keepRunning = false;
				break;
			default: //Invalid Input
				keepRunning = true; 
				System.out.println("\nInput not recognized\n");
				break;
			}
			clearScreen();
		} while (keepRunning);
	}
	
	private static void accessEmployee(Employee emp) {
		String inputString = null;
		int switcher = -1;
		int inputInt = -1;
		double inputDouble = -1.0;
		boolean keepRunning = true;
		boolean looper = true;
		
		do {
			clearScreen();
			System.out.println("------------------------------");
			System.out.println("---- Banking Service 1000 ----");
			System.out.println("------------------------------");
			System.out.println();
			System.out.println("Employee: [" + emp.getUserName() + "] " + emp.getFirstName() + " " + emp.getLastName() + " - ");
			System.out.println("Position: " + emp.getPosition());
			System.out.println("Branch: " + emp.getBranch());
			System.out.println();
			System.out.println(" [1] Manage Employee");
			System.out.println(" [2] Change Information");
			System.out.println();
			System.out.println(" [0] Back");
			System.out.println();
			System.out.println("Enter the number corresponding to your selection:");
			
			inputString = io.nextLine();
			clearScreen();
			try {
				switcher = Integer.parseInt(inputString);
		    } catch (NumberFormatException e) {
		    	switcher = 0;
		    }
			
			switch (switcher) {
			case 1: // Manage Employee
				while (looper) {
					System.out.println(" [1] Toggle Admin Status");
					System.out.println(" [2] Change Position");
					System.out.println(" [3] Change Branch");
					System.out.println(" [4] Change Salary");
					System.out.println(" [5] Change Wage");
					System.out.println(" [6] Change Hours");
					System.out.println();
					System.out.println(" [0] Back");
					System.out.println("Enter the number corresponding to your selection:");
					inputString = io.nextLine();
					try {
						inputInt = Integer.parseInt(inputString);
				    } catch (NumberFormatException e) {
				    	inputInt = -1;
				    }
					
					switch (inputInt) {
					case 1 : // Toggle Admin Status
						if (emp.getUserType() == 'e')
							emp.setUserType('a');
						else if (emp.getUserType() == 'a')
							emp.setUserType('e');
						break;
					case 2 : // Change Position
						System.out.println("Current Position: " + emp.getPosition());
						System.out.println();
						System.out.print("New Position: ");
						inputString = io.nextLine();
						emp.setPosition(inputString);
						break;
					case 3 : // Change Branch
						System.out.println("Current Branch: " + emp.getBranch());
						System.out.println();
						System.out.print("New Branch: ");
						inputString = io.nextLine();
						emp.setBranch(inputString);
						break;
					case 4 : // Change Salary
						System.out.println("Current Salary: " + emp.getSalary());
						System.out.println();
						System.out.print("New Salary: ");
						inputString = io.nextLine();
						try {
							inputDouble = Integer.parseInt(inputString);
					    } catch (NumberFormatException e) {
					    	inputDouble = -1;
					    }
						emp.setSalary(inputDouble);
						break;
					case 5 : // Change Wage
						System.out.println("Current Wage: " + emp.getWage());
						System.out.println();
						System.out.print("New Wage: ");
						inputString = io.nextLine();
						try {
							inputDouble = Integer.parseInt(inputString);
					    } catch (NumberFormatException e) {
					    	inputDouble = -1;
					    }
						emp.setWage(inputDouble);
						break;
					case 6 : // Change Hours
						System.out.println("Current Hours: " + emp.getHours());
						System.out.println();
						System.out.print("New Hours: ");
						inputString = io.nextLine();
						try {
							inputDouble = Integer.parseInt(inputString);
					    } catch (NumberFormatException e) {
					    	inputDouble = -1;
					    }
						emp.setHours(inputDouble);
						break;
					case 0: // Back
						clearScreen();
						keepRunning = false;
						break;
					default: //Invalid Input
						keepRunning = true; 
						System.out.println("\nInput not recognized\n");
						break;
					}
					clearScreen();
				}
				break;
			case 2: // Change Information
				while (looper) {
					System.out.println(" [1] Reset Password");
					System.out.println(" [2] Change First Name");
					System.out.println(" [3] Change Last Name");
					System.out.println(" [4] Change Street Address");
					System.out.println(" [5] Change Phone Number");
					System.out.println(" [6] Change Email Address");
					System.out.println();
					System.out.println(" [0] Back");
					System.out.println("Enter the number corresponding to your selection:");
					inputString = io.nextLine();
					try {
						inputInt = Integer.parseInt(inputString);
				    } catch (NumberFormatException e) {
				    	inputInt = -1;
				    }
					
					switch (inputInt) {
					case 1 : // Reset Password
						emp.setFlag("ResetPassword");
						break;
					case 2 : // Change First Name
						System.out.println("Current First Name: " + emp.getFirstName());
						System.out.println();
						System.out.println("New First Name: ");
						inputString = io.nextLine();
						emp.setFirstName(inputString);
						break;
					case 3 : // Change Last Name
						System.out.println("Current Last Name: " + emp.getLastName());
						System.out.println();
						System.out.println("New Last Name: ");
						inputString = io.nextLine();
						emp.setLastName(inputString);
						break;
					case 4 : // Change Street Address
						System.out.println("Current Street Address: " + emp.getAddress());
						System.out.println();
						System.out.println("New Street Address: ");
						inputString = io.nextLine();
						emp.setAddress(inputString);
						break;
					case 5 : // Change Phone Number
						System.out.println("Current Phone Number: " + emp.getPhone());
						System.out.println();
						System.out.println("New Phone Number: ");
						inputString = io.nextLine();
						emp.setPhone(inputString);
						break;
					case 6 : // Change Email Address
						System.out.println("Current Email Address: " + emp.getEmail());
						System.out.println();
						System.out.println("New Email Address: ");
						inputString = io.nextLine();
						emp.setEmail(inputString);
						break;
					case 0: // Back
						clearScreen();
						keepRunning = false;
						break;
					default: //Invalid Input
						keepRunning = true; 
						System.out.println("\nInput not recognized\n");
						break;
					}
					clearScreen();
				}
				break;
			case 0: // Back
				clearScreen();
				keepRunning = false;
				break;
			default: //Invalid Input
				keepRunning = true; 
				System.out.println("\nInput not recognized\n");
				break;
			}
			clearScreen();
		} while (keepRunning);
	}
	
	
	
	// Misc Methods
	public static void clearScreen() {
		for (int i = 0; i < 10; i++) System.out.print("\n");
	}

}
