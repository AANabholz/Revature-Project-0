package bank.revature.models;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Customer extends User {
	
	// Customer object for user processing (Customers and First-Time-Users?)
	

	/*
	 * 
	 * 
	 * Maybe save account amounts as strings so the double limit is negated?
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7727566681551649626L;
	// Variables
	

// Methods
	public Customer() {
		flag = "";
	}
	public Customer(User user) {
		this();
		this.copyUser(user, this);
	}
	
	
	//changeUsername() {}
	//changePassword() {}
	//changeName() {}
	//changeAddress() {}
	//changePhone() {}
	//changeEmail() {}
	
	
	
	// Read/Write
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		ois.defaultReadObject();
		Customer readCust = (Customer) ois.readObject();
		username = readCust.username; 
		password = readCust.password;
		firstName = readCust.firstName;
		lastName = readCust.lastName;
		address = readCust.address;
		phone = readCust.phone;
		email = readCust.email;
		userType = readCust.userType; 
		flag = readCust.flag; 
	}
	
	private void writeObject(ObjectOutputStream oos)throws IOException {
		oos.defaultWriteObject();
	}
}
		
	

