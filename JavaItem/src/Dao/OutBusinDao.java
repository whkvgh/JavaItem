package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Bean.OutbusinBean;

public class OutBusinDao {
	private Connection conn = null;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private OutbusinBean bean;

	public OutBusinDao() {
		try {

			String user = "item";
			String pw = "item";
			String url = "jdbc:oracle:thin:@192.168.0.56:1521:xe";

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pw);

			System.out.println("Database에 연결되었습니다.\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ResultSet viewOutBusin() {
		String sql = "select * from out_busin";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void InsertOutBusin() {
		String sql = "insert into out_busin" + "values(?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getBusin_code());
			pstmt.setString(2, bean.getBusin_name());
			pstmt.setString(3, bean.getBusin_addr());
			pstmt.setString(4, bean.getBusin_num());
			pstmt.setString(5, bean.getCeo());

			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void editOutBusin() {
		String sql = "update out_ busin set busin_code=nvl(?,busin_code), busin_name=nvl(?,busin_name)"+
				"busin_addr=nvl(?,busin_addr), busin_num=nvl(?,busin_num), ceo=nvl(?,ceo)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getBusin_code());
			pstmt.setString(2, bean.getBusin_name());
			pstmt.setString(3, bean.getBusin_addr());
			pstmt.setString(4, bean.getBusin_num());
			pstmt.setString(5, bean.getCeo());
			pstmt.executeQuery();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	public void deleteOutlist() {
		
	}
}
