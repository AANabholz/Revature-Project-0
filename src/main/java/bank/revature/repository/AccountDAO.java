package bank.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import bank.revature.models.Account;
import bank.revature.utility.ConnectionFactory;

public class AccountDAO{

	

// SELECT
	public HashSet<Account> selectAllAccounts() {
		PreparedStatement ps = null;
		HashSet<Account> toReturn = new HashSet<Account>();
		HashSet<String> owners = new HashSet<String>();
				
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("SELECT * FROM accounts VALUES = (?),(?),(?),(?);");
			ResultSet accResults = ps.executeQuery();
			while (accResults.next()) {
				String id = accResults.getString(1);
				String type = accResults.getString(2);
				String flag = accResults.getString(3);
				double balance = accResults.getDouble(4);
				ps = conn.prepareStatement("SELECT username FROM accountOwners WHERE acc_id = " + id + " VALUES = (?)");
				ResultSet ownerResults = ps.executeQuery();
				while (ownerResults.next())
					owners.add(ownerResults.getString(1));
				Account a = new Account(owners, id, type, flag, balance);
				toReturn.add(a);
			}
		} catch (SQLException e) {
			// 
		}
		return toReturn;
	}
	
	public HashSet<Account> selectAccountsByOwner(String owner) {
		PreparedStatement ps = null;
		HashSet<Account> toReturn = new HashSet<Account>();
		Account a = null;
		HashSet<String> accIDs = new HashSet<String>();
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("SELECT acc_id FROM accountOwners WHERE username = " + owner + " VALUES = (?);");
			ResultSet accIDResults = ps.executeQuery();
			while (accIDResults.next())
				accIDs.add(accIDResults.getString(1));
			for (String s : accIDs) {
				a = selectAccount(s);
				toReturn.add(a);
			}
		} catch (SQLException e) {
			// 
		}
		return toReturn;
	}
	
	public HashSet<Account> selectAccountsByType(String accType) {
		PreparedStatement ps = null;
		HashSet<Account> toReturn = new HashSet<Account>();
		Account a = null;
		HashSet<String> accIDs = new HashSet<String>();
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("SELECT acc_id FROM accounts WHERE acc_type = " + accType + " VALUES = (?);");
			ResultSet accIDResults = ps.executeQuery();
			while (accIDResults.next())
				accIDs.add(accIDResults.getString(1));
			for (String s : accIDs) {
				a = selectAccount(s);
				toReturn.add(a);
			}
		} catch (SQLException e) {
			// 
		}
		return toReturn;
	}
	
	public HashSet<Account> selectAccountsByFlag(String accFlag) {
		PreparedStatement ps = null;
		HashSet<Account> toReturn = new HashSet<Account>();
		Account a = null;
		HashSet<String> accIDs = new HashSet<String>();
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("SELECT acc_id FROM accounts WHERE acc_flag = " + accFlag + " VALUES = (?);");
			ResultSet accIDResults = ps.executeQuery();
			while (accIDResults.next())
				accIDs.add(accIDResults.getString(1));
			for (String s : accIDs) {
				a = selectAccount(s);
				toReturn.add(a);
			}
		} catch (SQLException e) {
			// 
		}
		return toReturn;
	}
	
	public Account selectAccount(String id) {
		PreparedStatement ps = null;
		Account toReturn = new Account();
		HashSet<String> owners = new HashSet<String>();
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("SELECT * FROM accounts WHERE acc_id = " + id + " VALUES = (?),(?),(?),(?);");
			ResultSet accResults = ps.executeQuery();
			String aID = accResults.getString(1);
			String type = accResults.getString(2);
			String flag = accResults.getString(3);
			double balance = accResults.getDouble(4);
			ps = conn.prepareStatement("SELECT username FROM accountOwners WHERE acc_id = " + aID + " VALUES = (?)");
			ResultSet ownerResults = ps.executeQuery();
			while (ownerResults.next())
				owners.add(ownerResults.getString(1));
			toReturn = new Account(owners, aID, type, flag, balance);
		} catch (SQLException e) {
			// 
		}
		return toReturn;
	}
	
	
// INSERT
	public boolean insertAccount(Account a) {
		PreparedStatement ps = null;
		boolean toReturn = false;
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("INSERT INTO accounts(acc_type, acc_flag, balance) VALUES = (?, ?, ?)");
			ps.setString(1, a.getType());
			ps.setString(2, a.getFlag());
			ps.setDouble(3, a.getBalance());
			ps.execute();
			if (insertAccountOwner(a))
				toReturn = true;
		} catch (SQLException e) {
			//
		}
		
		return toReturn;
	}
	
	public boolean insertAccountOwner(Account a) {
		PreparedStatement ps = null;
		boolean toReturn = false;
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("INSERT INTO accountOwners(acc_id, username) VALUES = (?, ?)");
			for (String s : a.getOwners()) {
				ps.setString(1, a.getID());
				ps.setString(2, s);
				ps.execute();
			}
			toReturn = true;
		} catch (SQLException e) {
			//
		}
		
		return toReturn;
	}
	
// UPDATE
	public boolean updateAccount(Account a) {
		PreparedStatement ps = null;
		boolean toReturn = false;
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("UPDATE accounts SET (acc_type, acc_flag, balance) VALUES = (?, ?, ?)");
			ps.setString(1, a.getType());
			ps.setString(2, a.getFlag());
			ps.setDouble(3, a.getBalance());
			ps.execute();
			toReturn = true;
		} catch (SQLException e) {
			//
		}
		
		return toReturn;
	}

// DELETE
	public boolean deleteAccount(String id) {
		PreparedStatement ps = null;
		boolean toReturn = false;
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("DELETE FROM accounts WHERE acc_id = " + id);
			ps.execute();
			toReturn = true;
		} catch (SQLException e) {
			//
		}
		
		return toReturn;
	}
	
	public boolean deleteAccountOwner(String id, String owner) {
		PreparedStatement ps = null;
		boolean toReturn = false;
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("DELETE FROM accountOwners WHERE username = " + owner + " VALUES = (?, ?, ?, ?)");
			ps.execute();
			toReturn = true;
		} catch (SQLException e) {
			//
		}
		
		return toReturn;
	}
	
	
	
	
	
	
	
}
