package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

	final private String DATABASE_URL = "jdbc:mysql://surfael.sytes.net:3307/chatdb";
	
	public void connect() {
		try{
			Connection c = DriverManager.getConnection(DATABASE_URL, "raphaelbgr", "tjq5uxt3");
			System.out.println("funcionou");
		} catch (SQLException e){
			e.printStackTrace();
		} finally{
			
		}
	}
}
