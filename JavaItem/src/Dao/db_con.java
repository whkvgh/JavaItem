package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class db_con {
	
	static Connection conn = null;
	static PreparedStatement pstmt;
	static ResultSet rs;
	
	public db_con() {
		try {
			
			String user ="item";
			String pw = "item";
			String url = "jdbc:oracle:thin:@192.168.0.56:1521:xe";

		    Class.forName("oracle.jdbc.driver.OracleDriver");        
		    conn = DriverManager.getConnection(url, user, pw);
		         
		    System.out.println("Database에 연결되었습니다.\n");
		}catch (Exception e) {
	         e.printStackTrace();
	    }
	}
}
