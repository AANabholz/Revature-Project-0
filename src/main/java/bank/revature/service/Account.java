package bank.revature.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

import bank.revature.repository.BankCore;

public class Account extends BankCore implements Serializable{
	
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
	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 3180618345834838474L;
	// Variables
	private static final AtomicInteger idCounter = new AtomicInteger(0);
	private int id; // Account Number
	private HashSet<String> owners; // usernames of all owners on the account
	private String type;
	private String flag;
	private double balance;
	
	
// Methods
	public Account() {
		this.id = idCounter.incrementAndGet();
		this.owners = new HashSet<String>();
	}
	
	public Account(String owner, String type, double balance, String flag) {
		this();
		addOwner(owner);
		this.type = type;
		this.balance = balance;
		this.flag = flag;
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
	
	
	// Read/Write
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		Account readAcct = (Account) ois.readObject();
		for (String s : readAcct.owners)
			this.owners.add(s);
		id = readAcct.id;
		type = readAcct.type;
		flag = readAcct.flag;
		balance = readAcct.balance;
	}
	
	private void writeObject(ObjectOutputStream oos)throws IOException {
		
	}
	
	
	// Getters
	public HashSet<String> getOwners() {
		return owners;
	}
	public int getID() {
		return id;
	}
	public String getType() {
		return type;
	}
	protected double getBalance() {
		return balance;
	}
	public String getFlag() {
		return flag;
	}
	
	
	// Setters
	protected void setOwners(HashSet<String> owners) {
		this.owners = owners;
	}
	protected void setID(int id) {
		this.id = id;
	}
	public void setType(String type) {
		this.type = type;
	}
	protected void setBalance(double balance) {
		this.balance = balance;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
}
