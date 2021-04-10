package bank.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import bank.revature.models.Employee;
import bank.revature.models.User;
import bank.revature.utility.ConnectionFactory;

public class EmployeeDAO {

	
	
	
	
	
// SELECT
	public HashSet<Employee> selectAllEmployees() {
		PreparedStatement ps = null;
		HashSet<Employee> toReturn = new HashSet<Employee>();
				
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("SELECT * FROM employees VALUES = (?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?);");
			ResultSet empResults = ps.executeQuery();
			while (empResults.next()) {
				String username = empResults.getString(1);
				String password = empResults.getString(2);
				String firstName = empResults.getString(3);
				String lastName = empResults.getString(4);
				String address = empResults.getString(5);
				String phone = empResults.getString(6);
				String email = empResults.getString(7);
				String flag = empResults.getString(8);
				char userType = empResults.getString(9).charAt(0);
				String position = empResults.getString(10);
				String branch = empResults.getString(10);
				double salary = empResults.getDouble(10);
				double wage = empResults.getDouble(10);
				double hours = empResults.getDouble(10);
				User u = new User(username, password, firstName, lastName, address, phone, email, flag, userType);
				Employee e = new Employee(u, position, branch, salary, wage, hours);
				toReturn.add(e);
			}
		} catch (SQLException err) {
			// 
		}
		return toReturn;
	}
	
	public Employee selectEmployee(String userName) {
		PreparedStatement ps = null;
		Employee toReturn = new Employee();
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("SELECT * FROM employees WHERE username = " + userName + " VALUES = (?),(?),(?),(?);");
			ResultSet empResults = ps.executeQuery();
			String username = empResults.getString(1);
			String password = empResults.getString(2);
			String firstName = empResults.getString(3);
			String lastName = empResults.getString(4);
			String address = empResults.getString(5);
			String phone = empResults.getString(6);
			String email = empResults.getString(7);
			String flag = empResults.getString(8);
			char userType = empResults.getString(9).charAt(0);
			String position = empResults.getString(10);
			String branch = empResults.getString(10);
			double salary = empResults.getDouble(10);
			double wage = empResults.getDouble(10);
			double hours = empResults.getDouble(10);
			User u = new User(username, password, firstName, lastName, address, phone, email, flag, userType);
			toReturn = new Employee(u, position, branch, salary, wage, hours);
		} catch (SQLException err) {
			// 
		}
		return toReturn;
	}
	
	
// INSERT
	public boolean insertEmployee(Employee e) {
		PreparedStatement ps = null;
		boolean toReturn = false;
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("INSERT INTO employees (username, password, firtName, lastName, address, phone, email, flag, userType, position, branch, salary, wage, hours) VALUES = (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, e.getUserName());
			ps.setString(2, e.getPassword());
			ps.setString(3, e.getFirstName());
			ps.setString(4, e.getLastName());
			ps.setString(5, e.getAddress());
			ps.setString(6, e.getPhone());
			ps.setString(7, e.getEmail());
			ps.setString(8, e.getFlag());
			ps.setString(9, String.valueOf(e.getUserType()));
			ps.setString(8, e.getPosition());
			ps.setString(8, e.getBranch());
			ps.setDouble(8, e.getSalary());
			ps.setDouble(8, e.getWage());
			ps.setDouble(8, e.getHours());
			ps.execute();
			toReturn = true;
		} catch (SQLException err) {
			//
		}
		
		return toReturn;
	}
	
// UPDATE
	public boolean updateEmployee(Employee e) {
		PreparedStatement ps = null;
		boolean toReturn = false;
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("UPDATE accounts SET (username, password, firtName, lastName, address, phone, email, flag, userType, position, branch, salary, wage, hours) VALUES = (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, e.getUserName());
			ps.setString(2, e.getPassword());
			ps.setString(3, e.getFirstName());
			ps.setString(4, e.getLastName());
			ps.setString(5, e.getAddress());
			ps.setString(6, e.getPhone());
			ps.setString(7, e.getEmail());
			ps.setString(8, e.getFlag());
			ps.setString(9, String.valueOf(e.getUserType()));
			ps.setString(8, e.getPosition());
			ps.setString(8, e.getBranch());
			ps.setDouble(8, e.getSalary());
			ps.setDouble(8, e.getWage());
			ps.setDouble(8, e.getHours());
			ps.execute();
			toReturn = true;
		} catch (SQLException err) {
			//
		}
		
		return toReturn;
	}

// DELETE
	public boolean deleteEmployee(String id) {
		PreparedStatement ps = null;
		boolean toReturn = false;
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			ps = conn.prepareStatement("DELETE FROM accounts WHERE acc_id = " + id);
			ps.execute();
			toReturn = true;
		} catch (SQLException err) {
			//
		}
		
		return toReturn;
	}
		
}
