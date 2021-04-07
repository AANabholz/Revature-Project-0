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
	public void loadData(String cusFile, String empFile, String accFile) {
		Object obj = null;
		boolean keepReading = true;
		
		// Read Customers
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cusFile))) {
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
			//
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Read Employees
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(empFile))) {
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
			//
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Read Accounts
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(accFile))) {
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
			//
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveData(String cusFile, String empFile, String accFile) {
		// Write Customers
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cusFile))) {
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
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(empFile))) {
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
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(accFile))) {
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
	
	public void loadTestData(String userFile, String accFile) {
		User u = null;
		Object obj = null;
		boolean keepReading = true;
		
		// Read Users
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userFile))) {
			// Loop through file and load data
			while (keepReading) {
				try {
					u = (User) ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (u.getUserType() == 'c')
					customers.add((Customer) u);
				else
					employees.add((Employee) u);
			}
			ois.close();
		} catch (EOFException e) {
			//
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Read Accounts
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(accFile))) {
			// Loop through file and load data
			while (keepReading) {
				obj = null;
				try {
					obj = ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				accounts.add((Account) obj);
			}
			ois.close();
		} catch (EOFException e) {
			//
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveTestData(String userFile, String accFile) {
		// Write Customers
				try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userFile))) {
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
				try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userFile))) {
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
				try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(accFile))) {
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

	public void testData() {
		User u = null;
		Customer c = null;
		Employee e = null;
		
		// Testing Data
		// Admin for if data is gone
		u = new User("admin", "admin", "CEO", "Ofthebank", "", "", "", "", 'a');
		e = new Employee(u);
		employees.add(e);
		u = new User("emp", "emp", "Employee", "Ofthebank", "", "", "", "", 'e');
		e = new Employee(u);
		employees.add(e);
		u = new User("test", "test", "Chester", "Tester", "", "", "", "", 'c');
		c = new Customer(u);
		customers.add(c);
		u = new User("cus", "cus", "Customer", "Offthestreet", "", "", "", "", 'c');
		c = new Customer(u);
		customers.add(c);
		// Account data for testing
		Account a1 = new Account("test", "Checking" , 5000.50, "");
		Account a2 = new Account("cus", "Checking" , 5000.50, "");
		Account a3 = new Account("test", "Checking" , 5000.50, "App");
		Account a4 = new Account("test", "Saving" , 5000.50, "");
		Account a5 = new Account("cus", "Saving" , 5000.50, "App");
		accounts.add(a1);
		accounts.add(a2);
		accounts.add(a3);
		accounts.add(a4);
		accounts.add(a5);
	}
	
	// Account Methods
	
	public void addAccount(Account a) {
		accounts.add(a);
	}
	
	public void removeAccount(Account a) {
		accounts.remove(a);
	}
	
	public Account getAccountByID(String id) {
		for (Account a : accounts) 
			if (a.getID().equals(id))
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
