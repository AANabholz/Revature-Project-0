package bank.revature;

import bank.revature.presentaion.UserInterface;
import bank.revature.repository.BankCore;

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
		
		
		
		
		bank.loadData();
		UserInterface.startEngine(bank);
		bank.saveData();
	}
}
