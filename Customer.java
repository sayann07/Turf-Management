package TurfManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.SQLException;

public class Customer {
	private Connection connection;
	private Scanner scanner;
	public Customer(Connection connection,Scanner scanner) {
		this.connection=connection;
		this.scanner=scanner;
	}
	public void newCustomer() {
		
		System.out.print("Enter Customer Name:");
		String name=scanner.next();
		System.out.print("Enter Customer Phone number:");
		int phone=scanner.nextInt();
		
		
		try {
			 String query= "INSERT INTO customer(name,phone) VALUES(?,?)";
			 PreparedStatement preparedStatement=connection.prepareStatement(query);
			 preparedStatement.setString(1, name);
			 preparedStatement.setInt(2, phone);
			 int affectedRows=preparedStatement.executeUpdate();
			 if(affectedRows>0) {
				 System.out.println("Customer added!");
			 }
			 else {
				 System.out.println("Failed to add Customer"); 
				 }
			 
		
		
		
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void viewCustomer() {	
		String query="select * from CUSTOMER";
		
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			ResultSet resultSet= preparedStatement.executeQuery();
			 System.out.println("Customers: ");
			 System.out.println("+-------------+------------------------+----------------+");
			 System.out.println("| Customer ID | Name                   | Phone          |");
			 System.out.println("+-------------+------------------------+----------------+");
			 while(resultSet.next()) {
				 int id=resultSet.getInt("ID");
				 String name=resultSet.getString("NAME");
				 int phone=resultSet.getInt("PHONE");
				 System.out.printf("|%-13s|%-24s|%-17s|\n,id,name,phone");
				 System.out.println("+-------------+------------------------+----------------+");
				 }
		
		
		}catch(SQLException e) {
			e.printStackTrace();
	}
	}
		public boolean getCustomerById(int ID) {
			String query="SELECT * FROM CUSTOMER WHERE ID= ?";
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(query);
				preparedStatement.setInt(1, ID);
				ResultSet resultSet=preparedStatement.executeQuery();
				if(resultSet.next()) {
					return true;
					}
				else {
					
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
	}



