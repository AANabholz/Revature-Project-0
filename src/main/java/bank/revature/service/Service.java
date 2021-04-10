package bank.revature.service;

import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bank.revature.models.Account;
import bank.revature.models.Customer;
import bank.revature.models.Employee;
import bank.revature.models.User;
import bank.revature.repository.AccountDAO;
import bank.revature.repository.CustomerDAO;
import bank.revature.repository.EmployeeDAO;

public class Service{

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * To Do: 
	 * 		- Shouldn't parameters passed into these methods all be strings so that account
	 * 			sensitive objects can be encapsulated as much as possible?
	 * 		- 
	 * 
	 * 
	 */
	
	
	
// Variables
	private static Logger log = LogManager.getLogger(Service.class.getName());
	private AccountDAO accDAO;
	private CustomerDAO cusDAO;
	private EmployeeDAO empDAO;
	
	
	
// Methods
	public Service() {
		accDAO = new AccountDAO();
		cusDAO = new CustomerDAO();
		empDAO = new EmployeeDAO();
	}
	
	// Account Methods
	public HashSet<Account> getAccounts() {
		HashSet<Account> toReturn = accDAO.selectAllAccounts();
		return toReturn;
	}
	
	public void addAccount(Account a) {
		accDAO.insertAccount(a);
	}
	
	public void removeAccount(Account a) {
		accDAO.deleteAccount(a.getID());
	}
	
	public HashSet<Account> getAccountsByOwner(String username) {
		HashSet<Account> toReturn = accDAO.selectAccountsByOwner(username);
		return toReturn;
	}
	
	public HashSet<Account> getAccountsByType(String type) {
		HashSet<Account> toReturn = accDAO.selectAccountsByType(type);		
		return toReturn;
	}
	
	public HashSet<Account> getAccountsByFlag(String flag) {
		HashSet<Account> toReturn = accDAO.selectAccountsByFlag(flag);
		return toReturn;
	}
	
	public Account getAccountByID(String id) {
		Account toReturn = accDAO.selectAccount(id);		
		return toReturn;
	}
	
	// User Methods
	public HashSet<Customer> getCustomers() {
		HashSet<Customer> toReturn = cusDAO.selectAllCustomers();
		return toReturn;
	}

	public HashSet<Employee> getEmployees() {
		HashSet<Employee> toReturn = empDAO.selectAllEmployees();
		return toReturn;
	}
	
	public void addUser(User user) {
		if (user.getUserType() == 'c')
			cusDAO.insertCustomer((Customer) user);
		else
			empDAO.insertEmployee((Employee) user);
	}
	
	public User getUser(String username) {
		User toReturn = null;
		
		for (Customer c : cusDAO.selectAllCustomers()) {
			if (c.getUserName().equals(username)) {
				toReturn = c;
			}
		}
		for (Employee e : empDAO.selectAllEmployees()) {
			if (e.getUserName().equals(username)) {
				toReturn = e;
			}
		}
		
		return toReturn;
	}

	public void removeCustomer(Customer cus) {
		cusDAO.deleteCustomer(cus.getUserName());
	}

	public void removeEmployee(Employee emp) {
		empDAO.deleteEmployee(emp.getUserName());
	}

	
	
	
	
	
	// Account Transactions
	// Inquiry
	protected static double getBalance(Account account) {
		log.info("Balance - Account: " + account.getID() + ", Balance: $" + account.getBalance());
		return account.getBalance();
	}
	
	// Withdraw
	protected static boolean withdraw(Account account, double amount) {
		boolean toReturn = false;
		
		if (account.getBalance() >= amount) {
			log.info("Withdraw - Account: " + account.getID() + ", Old balance: $" + account.getBalance() + ", New Balance: $" + (account.getBalance() - amount));
			account.setBalance(account.getBalance() - amount);
			toReturn = true;
		}
		else
			log.warn("Withdraw - Account: " + account.getID() + ", Old balance: $" + account.getBalance() + ", Transaction Failed: Not enough money to complete transaction");
			
		return toReturn;
	}

	// Deposit
	protected static void deposit(Account account, double amount) {
		log.info("Deposit - Account: " + account.getID() + ", Old balance: $" + account.getBalance() + ", New Balance: $" + (account.getBalance() + amount));
		account.setBalance(account.getBalance() + amount);
	}
	
	
}
