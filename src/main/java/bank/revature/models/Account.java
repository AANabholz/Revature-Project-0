package bank.revature.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class Account{
	
	// Customer object for user processing (Customers and First-Time-Users?)
	

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	// Variables
	private HashSet<String> owners; // usernames of all owners on the account
	private String id; // Account Number
	private String type;
	private String flag;
	private double balance;
	
	
// Methods
	public Account() {
		this.owners = new HashSet<String>();
	}
	
	public Account(String owner, String type, String flag, double balance) { // new account
		this();
		addOwner(owner);
		this.type = type;
		this.flag = flag;
		this.balance = balance;
	}
	
	public Account(HashSet<String> owners, String id, String type, String flag, double balance) { // load account
		this();
		this.id = id;
		this.type = type;
		this.flag = flag;
		this.balance = balance;
	}
		
		
	
	public void addOwner(String c) {
		owners.add(c);
	}

	public void removeOwner(String c) {
		owners.remove(c);
	}
	
	public boolean verify(String username) {
		boolean toReturn = false;
		
		for (String s : getOwners()) { 
			if (s.equals(username)) 
				toReturn = true; 
		}
		
		return toReturn;
	}
	
	public static String getDateTime() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String datetime = ft.format(dNow);
        return datetime;
    }
	
	// Getters
	public HashSet<String> getOwners() {
		return owners;
	}
	public String getID() {
		return id;
	}
	public String getType() {
		return type;
	}
	public double getBalance() {
		return balance;
	}
	public String getFlag() {
		return flag;
	}
	
	
	// Setters
	public void setOwners(HashSet<String> owners) {
		this.owners = owners;
	}
	public void setID(String id) {
		this.id = id;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
}
