package bank.revature.repository;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import bank.revature.models.Customer;
import bank.revature.models.Employee;
import bank.revature.models.User;
import bank.revature.service.Account;

public class BankCore{
	
	
	// Holds the core private private data members used by this application.
	
	/*
	 *
	 * 
	 * 
	 * 
	 * 
	 */
	
// Variables
	
	private HashSet<Customer> customers;
	private HashSet<Employee> employees;
	private HashSet<Account> accounts;
	
	
// Methods
	// Constructors
	public BankCore() {
		customers = new HashSet<Customer>();
		employees = new HashSet<Employee>();
		accounts = new HashSet<Account>();
	}
	
	// Data File Methods
	public void loadData() {
		Object obj = null;
		boolean keepReading = true;
		
		// Read Customers
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/CustomerData.txt"))) {
			// Loop through file and load data
			while (keepReading) {
				obj = null;
				try {
					obj = ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (obj != null)
					customers.add((Customer) obj);
				else
					keepReading = false;
			}
			ois.close();
		} catch (EOFException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Read Employees
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/EmployeeData.txt"))) {
			// Loop through file and load data
			while (keepReading) {
				obj = null;
				try {
					obj = ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (obj != null)
					employees.add((Employee) obj);
				else
					keepReading = false;
			}
			ois.close();
		} catch (EOFException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Read Accounts
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/AccountData.txt"))) {
			// Loop through file and load data
			while (keepReading) {
				obj = null;
				try {
					obj = ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (obj != null)
					accounts.add((Account) obj);
				else
					keepReading = false;
			}
			ois.close();
		} catch (EOFException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveData() {
		// Write Customers
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/CustomerData.txt"))) {
			// Loop through data and write to file
			for (Customer cus : customers)
				try {
					oos.writeObject(cus);
				} catch (NotSerializableException e) {
					System.out.println("Object Not Serializable");
				}
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Write Employees
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/EmployeeData.txt"))) {
			// Loop through data and write to file
			for (Employee emp : employees)
				try {
					oos.writeObject(emp);
				} catch (NotSerializableException e) {
					System.out.println("Object Not Serializable");
				}
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Write Accounts
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/AccountData.txt"))) {
			// Loop through data and write to file
			for (Account acc : accounts)
				try {
					oos.writeObject(acc);
				} catch (NotSerializableException e) {
					System.out.println("Object Not Serializable");
				}
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	
	// Account Methods
	public void addAccount(Account a) {
		accounts.add(a);
	}
	
	public void removeAccount(Account a) {
		accounts.remove(a);
	}
	
	public Account getAccountByID(int id) {
		for (Account a : accounts) 
			if (a.getID() == id)
				return a;
		
		return null;
	}
	
	public ArrayList<Account> getAccountsByOwner(String username) {
		ArrayList<Account> toReturn = new ArrayList<Account>();
		
		for (Account a : accounts) // Loop through all accounts
			for (String s : a.getOwners()) // Loop through all account owners
				if (s.equals(username)) // If the usernames match
					toReturn.add(a); // add the account to accounts to return
			
		return toReturn;
	}
	
	public ArrayList<Account> getAccountsByType(String type) {
		ArrayList<Account> toReturn = new ArrayList<Account>();
		
		for (Account a : accounts) // Loop through all accounts
			if (a.getType().equals(type)) // If the usernames match
				toReturn.add(a); // add the account to accounts to return
		
		return toReturn;
	}
	
	public ArrayList<Account> getAccountsByFlag(String flag) {
		ArrayList<Account> toReturn = new ArrayList<Account>();
		
		for (Account a : accounts) // Loop through all accounts
			if (a.getFlag().equals(flag)) // If the usernames match
				toReturn.add(a); // add the account to accounts to return
		
		return toReturn;
	}
	
	// User Methods
	public void addUser(User user) {
		if (user.getUserType() == 'c')
			customers.add((Customer) user);
		else
			employees.add((Employee) user);
	}
	
	public User getUser(String username) {
		User toReturn = null;
		
		for (Customer c : customers) {
			if (c.getUserName().equals(username)) {
				toReturn = c;
			}
		}
		for (Employee e : employees) {
			if (e.getUserName().equals(username)) {
				toReturn = e;
			}
		}
		
		return toReturn;
	}

	public void removeCustomer(Customer cus) {
		customers.remove(cus);
	}

	public void removeEmployee(Employee emp) {
		employees.remove(emp);
	}

	


	// Getters
	public HashSet<Customer> getCustomers() {
		return customers;
	}
	public HashSet<Employee> getEmployees() {
		return employees;
	}
	public HashSet<Account> getAccounts() {
		return accounts;
	}

	



	// Setters
	protected void setCustomers(HashSet<Customer> c) {
		customers = c;
	}
	protected void setEmployees(HashSet<Employee> e) {
		employees = e;
	}
	protected void setAccounts(HashSet<Account> a) {
		accounts = a;
	}

	







	

}
