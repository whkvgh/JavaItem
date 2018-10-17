package Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Bean.OutlistBean;

public class OutlistDao {
	static Connection conn = null;
	static PreparedStatement pstmt;
	static ResultSet rs;
	static CallableStatement pst;
	static OutlistBean bean;
	
	public OutlistDao() {
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
	public ResultSet InsertOutlist() {	//입력
	 String sql = "insert into out_list(sub_group, i_name, i_count, price, money, busin_code, store_code, out_date) " + 
			"  values(?,?,?,?,?,?,?, sysdate)";
	 
	 try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getSell_num());
			pstmt.setInt(2, bean.getSub_infor());
			pstmt.setString(3, bean.getSub_group());
			pstmt.setString(4, bean.getI_name());
			pstmt.setInt(6, bean.getI_count());
			pstmt.setInt(7, bean.getPrice());
			pstmt.setInt(8, bean.getMoney());
			pstmt.setString(9, bean.getBusin_code());
			pstmt.setString(10, bean.getStore_code());
			
			rs = pstmt.executeQuery();
			
	 } catch (SQLException e) {
			e.printStackTrace();
		}
	return rs;
		
	}
	
	public void editOutlist() {	//수정
//		String sql = "update Outlist set sub_group =?, i_name = ?, i_count = ?, pricdr = ?, money=?, out_date =?, busin_code=?, store_code=?"
		
	}
	
	public void  deleteOutlist() { //삭제
		String sql = " delete from out_list where trunc(out_date) = to_date('?', 'yy/mm/dd') and sell_num= ? and  sub_infor=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			// sysdate 우째 삭제함????????
			pstmt.setString(2, bean.getSell_num());
			pstmt.setInt(3, bean.getSub_infor());
			rs = pstmt.executeQuery();
			
			}catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	public ResultSet getOutlist() {	//조회
		String sql = "select * from out_list";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	

	public void outgoods(String sub_group, String i_name, int i_count, String store_code) {
		try {
			pst = conn.prepareCall("{call out_pro (?,?,?,?)}");
			pst.setString(1, sub_group);
			pst.setString(2, i_name);
			pst.setInt(3, i_count);
			pst.setString(4, store_code);
			
			pst.execute();
	} catch(SQLException e) {
		e.printStackTrace();
	}
		
	}
	
}
