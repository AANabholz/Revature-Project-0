package bank.revature;

import bank.revature.models.Customer;
import bank.revature.models.Employee;
import bank.revature.models.User;
import bank.revature.presentaion.UserInterface;
import bank.revature.repository.BankCore;
import bank.revature.service.Account;

public class MainDriver{
	
	/*
	 * 
	 * 
	 * 
	 * 
	 */
	
	public static void main(String[] args) {
		
	// Variables		
		BankCore bank = new BankCore();
		
		
		
	// Function
		
		
		// Temp data
		// hardcoded data for testing can be removed once read/write is functioning
		User a = new User("admin", "admin", "Admin", "Ofthebank", "", "", "", "", 'a');
		User e = new User("emp", "emp", "Employee", "Ofthebank", "", "", "", "", 'e');
		User c = new User("cus", "cus", "Customer", "Offthestreet", "", "", "", "", 'c');
		User t = new User("test", "test", "Chester", "Tester", "", "", "", "", 'c');
		Employee dadmin = new Employee(a, "CEO", "Headquarters", 1000000000.01, 100);
		Employee employee = new Employee(e, "Clerk", "Front St.", 0, 15.55);
		Customer cus = new Customer(c);
		Customer test = new Customer(t);
		Account accA = new Account("cus", "Checking", 100000.30, "");
		Account accB = new Account("cus", "Checking", 100000.30, "App");
		Account accC = new Account("cus", "Saving", 100000.30, "");
		Account accD = new Account("test", "Checking", 100000.30, "");
		Account accE = new Account("test", "Saving", 100000.30, "");
		bank.addUser(dadmin);
		bank.addUser(employee);
		bank.addUser(cus);
		bank.addUser(test);
		bank.addAccount(accA);
		bank.addAccount(accB);
		bank.addAccount(accC);
		bank.addAccount(accD);
		bank.addAccount(accE);
		// Temp data
		
		
		
		//bank.loadData();
		UserInterface.startEngine(bank);
		bank.saveData();
	}
}
