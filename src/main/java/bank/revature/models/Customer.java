package bank.revature.models;

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
	
	
	
}
		
	

