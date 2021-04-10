package bank.revature.models;

public class Customer extends User {
	
	// Customer object for user processing (Customers and First-Time-Users?)
	

	/*
	 * 
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
		
	

