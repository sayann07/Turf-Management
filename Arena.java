package TurfManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Time;

public class Arena {

	private Connection connection;
	
	public Arena(Connection connection) {
		this.connection=connection;
		
	}
	

	public void viewArena() {	
		String query="select * from ARENA";
		
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			ResultSet resultSet= preparedStatement.executeQuery();
			 System.out.println("Arenas: ");
			 System.out.println("+-------------+------------------------+----------------+-------------------|");
			 System.out.println("| Arena ID    | Timing                 | Price           | Name             |");
			 System.out.println("+-------------+------------------------+-----------------+------------------+");
			 while(resultSet.next()) {
				 int id=resultSet.getInt("ID"); 
				 Time timing=resultSet.getTime("TIMING");
				 int price=resultSet.getInt("PRICE");
				 String name=resultSet.getString("NAME");
				 System.out.printf("|%-14s|%-25s|%-18s|%-19s|\n,id,timing,price,name");
				 System.out.println("+-------------+------------------------+-----------------+------------------+");
				 }
		
		
		}catch(SQLException e) {
			e.printStackTrace();
	}
	}
		public boolean getArenaById(int ID) {
			String query="SELECT * FROM ARENA WHERE ID= ?";
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
