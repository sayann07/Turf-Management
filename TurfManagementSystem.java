package TurfManagementSystem;

import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TurfManagementSystem {

		private static final String url="jdbc:mysql://localhost:3306/TURF";
		private static final String username="root";
		private static final String password="root";
		
		public static void main(String args[]) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		
		Scanner scanner= new Scanner(System.in);
		try {
			Connection connection = DriverManager.getConnection(url,username,password);
		    Customer customer= new Customer(connection,scanner);
		    Arena arena=new Arena(connection);
		    while(true) {
		    	System.out.println("TURF MANAGEMENT SYSTEM");
		    	System.out.println("1. Add Customer");
		    	System.out.println("2. View Customer");
		    	System.out.println("3.View Arena");
		    	System.out.println("4.Book Arena");
		    	System.out.println("5. Exit");
		    	System.out.println("Enter your choice: ");
		    	int choice=scanner.nextInt();
		    	
		    	switch(choice) {		    
		    	case 1:
		    		customer.newCustomer();
		    		System.out.println();
		    	case 2:
		    		customer.viewCustomer();
		    		System.out.println();
		    	case 3:
		    		arena.viewArena();
		    		System.out.println();
		    	case 4:
		    		bookArena(customer,arena,connection,scanner);
		    		System.out.println();
		    	case 5:
		    		return;
		    		default:
		    			System.out.println("Enter valid choice");
		    		
		    	}
		    	
		
		
		    }
		    }catch(SQLException e) {
			e.printStackTrace();
		}
		}

public static void bookArena(Customer customer, Arena arena,Connection connection,Scanner scanner) {
	System.out.println("Enetr customer ID: ");
	int customerID=scanner.nextInt();
	System.out.println("Enter Arena ID: ");
	int arenaID=scanner.nextInt();
	System.out.println("Enter the timings");
	String timing=scanner.next();
	System.out.println("Enter booking date (YYYY-MM-DD): ");
	String bookingDate=scanner.next();
	if(customer.getCustomerById(customerID) && arena.getArenaById(arenaID)) {
		
		if(checkArenaAvailability(arenaID,timing,bookingDate,connection)){
			String bookingQuery="INSERT INTO BOOKINGS(timing,booking_date) VALUES(?,?)";
		try {
			PreparedStatement preparedStatement= connection.prepareStatement(bookingQuery);
			preparedStatement.setString(1, timing);
			preparedStatement.setString(2, bookingDate);
			int rowsAffected=preparedStatement.executeUpdate();
			if(rowsAffected>0) {
				System.out.println("Booking Confirmend!");
			}
			else {
				System.out.println("Failed to book!");
			}
		}catch(SQLException e) {
				e.printStackTrace();
		
		}}
		
			else {
		System.out.println("Wrong input!!! ");
		}}
	}
	
	public static boolean checkArenaAvailability(int arenaID,String timing,String bookingDate,Connection connection ) {
		String query="SELECT COUNT(*) FROM BOOKINGS WHERE TIMING=? AND BOOKING_DATE=?";
		try {
			PreparedStatement preparedStatement= connection.prepareStatement(query);
			preparedStatement.setString(1, timing);
			preparedStatement.setString(2, bookingDate);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				int count=resultSet.getInt(1);
				if(count==0) {
					return true;
				}
				else {
					return false;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
		}