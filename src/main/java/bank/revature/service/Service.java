package bank.revature.service;

public abstract class Service{

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	
	
	
	
	
	
	
	
	// Inquiry
	public static double getBalance(Account account) {
		return account.getBalance();
	}
	
	// Withdraw
	public static boolean withdraw(Account account, double amount) {
		boolean toReturn = false;
		
		if (account.getBalance() >= amount) {
			account.setBalance(account.getBalance() - amount);
			toReturn = true;
		}
		else
			System.out.println("ERROR: There is not enough money in this account to process this transaction!");
		
		return toReturn;
	}

	// Deposit
	public static void deposit(Account account, double amount) {
		account.setBalance(account.getBalance() + amount);
	}
	
// Employee only options
	// copy user? as in account rescue
	
}
