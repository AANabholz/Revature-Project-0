package bank.revature;

import bank.revature.presentaion.UserInterface;
import bank.revature.utility.ConnectionFactory;

public class MainDriver{
	
	/*
	 * 
	 * Contains the only main method that instantiates a 
	 * BankCore object 'bank'. The data for the system is 
	 * loaded from .txt files into the bank, then the system 
	 * engine is started with that bank object. When the 
	 * engine is done running the data is saved back to 
	 * .txt files and the system concludes executuion.
	 * 
	 */
	
	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (ConnectionFactory.getConnection() != null) {
			System.out.println("Successful Connection!");
			UserInterface.startEngine();
		}
//		UserInterface.startEngine();
	}
}

