package bank.revature.models;

public class Employee extends User{

	
	// Employee object for user processing (Employee and Administrators)
	

	/*
	 * 
	 * 
	 * 
	 * To change a user's username or password an Employee must create a new object
	 *  because constructors are the only thing with access to those variables.
	 * 
	 * Method to view all accounts by flag, for finding all new account applications
	 * 
	 * 
	 */


/**
	 * 
	 */
	private static final long serialVersionUID = -8638798023180256130L;
	// Variables
	protected String position;
	protected String branch;
	
	protected double salary;
	protected double wage;
	protected double hours;
	
// Methods
	public Employee() {
		position = "undefined";
		branch = "undefined";
		salary = 0.0;
		wage = 0.0;
		hours = 0.0;
	}
	
	public Employee(User user) {
		this();
		this.copyUser(user, this);
	}
	
	public Employee(User user, String p, String b, double s, double w) {
		this();
		this.copyUser(user, this);
		position = p;
		branch = b;
		salary = s;
		wage = w;
	}
	
	public Employee(User user, String p, String b, double s, double w, double h) {
		this();
		this.copyUser(user, this);
		position = p;
		branch = b;
		salary = s;
		wage = w;
		hours = h;
	}

	
	
	//
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Getters
	public String getPosition() {
		return position;
	}

	public String getBranch() {
		return branch;
	}

	public double getSalary() {
		return salary;
	}

	public double getWage() {
		return wage;
	}

	public double getHours() {
		return hours;
	}


	
	
	// Setters
	public void setPosition(String position) {
		this.position = position;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setWage(double wage) {
		this.wage = wage;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

	public void setFlag(String string) {
		// TODO Auto-generated method stub
		
	}
	
	
}
