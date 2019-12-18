package rs.ac.uns.ftn.db.jdbc.connection_pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Zadatak1 {

	public static void main(String[] args) {
		String query = "select mbr,ime,prz from radnik where mbr in(select mbr from radproj where spr = 10) and mbr not in(select mbr from radproj where spr = 30)";
		
		
		try( 
			 Connection connection = ConnectionUtil_HikariCP.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query);){
			
			selectRadnik(preparedStatement);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
      
	}

	private static void selectRadnik(PreparedStatement preparedStatement) throws SQLException {
		
		try(ResultSet resultSet = preparedStatement.executeQuery()){
			if(!resultSet.isBeforeFirst()) {
				System.out.println("No data found!");
			}else {
				System.out.printf("%-4s %-10s %-4s\n" , "MBR", "IME", "PRZ");
				
				while(resultSet.next()) {
					int mbr = resultSet.getInt(1);
					String ime = resultSet.getString(2);
					String prezime = resultSet.getString(3);
					System.out.printf("%-4s %-10s %-4s\n" , mbr, ime, prezime);
					
				}
			}
		}
		
	}

}
