package bank.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import bank.revature.models.Customer;
import bank.revature.models.User;
import bank.revature.utility.ConnectionFactory;

public class CustomerDAO {

	
	
	
// SELECT
	public HashSet<Customer> selectAllCustomers() {
		PreparedStatement ps = null;
		HashSet<Customer> toReturn = new HashSet<Customer>();
				
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("SELECT * FROM customers VALUES = (?),(?),(?),(?),(?),(?),(?),(?),(?);");
			ResultSet cusResults = ps.executeQuery();
			while (cusResults.next()) {
				String username = cusResults.getString(1);
				String password = cusResults.getString(2);
				String firstName = cusResults.getString(3);
				String lastName = cusResults.getString(4);
				String address = cusResults.getString(5);
				String phone = cusResults.getString(6);
				String email = cusResults.getString(7);
				String flag = cusResults.getString(8);
				char userType = cusResults.getString(9).charAt(0);
				User u = new User(username, password, firstName, lastName, address, phone, email, flag, userType);
				Customer c = new Customer(u);
				toReturn.add(c);
			}
		} catch (SQLException e) {
			// 
		}
		return toReturn;
	}
	
	public HashSet<Customer> selectCustomersByAccount(String id) {
		PreparedStatement ps = null;
		HashSet<Customer> toReturn = new HashSet<Customer>();
		Customer c = null;
		HashSet<String> customers = new HashSet<String>();
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("SELECT username FROM accountOwners WHERE acc_id = " + id + " VALUES = (?);");
			ResultSet cusAccResults = ps.executeQuery();
			while (cusAccResults.next())
				customers.add(cusAccResults.getString(1));
			for (String s : customers) {
				c = selectCustomer(s);
				toReturn.add(c);
			}
		} catch (SQLException e) {
			// 
		}
		return toReturn;
	}
	
	public Customer selectCustomer(String userName) {
		PreparedStatement ps = null;
		Customer toReturn = new Customer();
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("SELECT * FROM customers WHERE username = " + userName + " VALUES = (?),(?),(?),(?);");
			ResultSet cusResults = ps.executeQuery();
			String username = cusResults.getString(1);
			String password = cusResults.getString(2);
			String firstName = cusResults.getString(3);
			String lastName = cusResults.getString(4);
			String address = cusResults.getString(5);
			String phone = cusResults.getString(6);
			String email = cusResults.getString(7);
			String flag = cusResults.getString(8);
			char userType = cusResults.getString(9).charAt(0);
			User u = new User(username, password, firstName, lastName, address, phone, email, flag, userType);
			toReturn = new Customer(u);
		} catch (SQLException e) {
			// 
		}
		return toReturn;
	}
	
	
// INSERT
	public boolean insertCustomer(Customer c) {
		PreparedStatement ps = null;
		boolean toReturn = false;
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("INSERT INTO customers(username, password, firtName, lastName, address, phone, email, flag, userType) VALUES = (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, c.getUserName());
			ps.setString(2, c.getPassword());
			ps.setString(3, c.getFirstName());
			ps.setString(4, c.getLastName());
			ps.setString(5, c.getAddress());
			ps.setString(6, c.getPhone());
			ps.setString(7, c.getEmail());
			ps.setString(8, c.getFlag());
			ps.setString(9, String.valueOf(c.getUserType()));
			ps.execute();
			toReturn = true;
		} catch (SQLException e) {
			//
		}
		
		return toReturn;
	}
	
// UPDATE
	public boolean updateCustomer(Customer c) {
		PreparedStatement ps = null;
		boolean toReturn = false;
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("UPDATE customers SET (username, password, firtName, lastName, address, phone, email, flag, userType) VALUES = (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, c.getUserName());
			ps.setString(2, c.getPassword());
			ps.setString(3, c.getFirstName());
			ps.setString(4, c.getLastName());
			ps.setString(5, c.getAddress());
			ps.setString(6, c.getPhone());
			ps.setString(7, c.getEmail());
			ps.setString(8, c.getFlag());
			ps.setString(9, String.valueOf(c.getUserType()));
			ps.execute();
			toReturn = true;
		} catch (SQLException e) {
			//
		}
		
		return toReturn;
	}

// DELETE
	public boolean deleteCustomer(String username) {
		PreparedStatement ps = null;
		boolean toReturn = false;
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("DELETE FROM customers WHERE username = " + username);
			ps.execute();
			toReturn = true;
		} catch (SQLException e) {
			//
		}
		
		return toReturn;
	}
		
		
		
		
}
