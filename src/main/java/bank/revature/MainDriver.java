package bank.revature;

import bank.revature.presentaion.UserInterface;
import bank.revature.repository.BankCore;

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
		BankCore bank = new BankCore();
		
		UserInterface.startEngine(bank);
	}
}

