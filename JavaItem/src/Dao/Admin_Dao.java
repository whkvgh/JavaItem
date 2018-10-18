package Dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Bean.ItemListBean;
import Bean.StoreListBean;

public class Admin_Dao {

	static Connection conn = null;
	static PreparedStatement pstmt;
	static ResultSet rs;
	static ItemListBean be;
	static StoreListBean sb;

	public Admin_Dao() {

		try {
			String user = "item";
			String pw = "item";
			String url = "jdbc:oracle:thin:@192.168.0.56:1521:xe";

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pw);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ResultSet in_busin_show() { // 구매업체

		String sql = "select * from in_busin";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet out_busin_show() { // 판매업체

		String sql = "select * from out_busin";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet store_list_show() { // 창고정보

		String sql = "select * from store_list";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet upstore() {
// 창고 수정	
		String sql = "update store_list set store_code = nvl(?,store_code), store_name=nvl(?,store_name), expla=nvl(?,expla)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sb.getStore_code());
			pstmt.setString(2, sb.getStore_name());
			pstmt.setString(3, sb.getExpla());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet insertitem(ItemListBean be) { // 입력
		String sql = "insert into item_list values(?,?,?,?,?,?,?)";

		int cnt = Integer.valueOf(be.getUnit());
		int i = Integer.valueOf(be.getPrice());

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, be.getI_name());
			pstmt.setString(2, be.getSub_group());
			pstmt.setString(3, be.getI_name());
			pstmt.setString(4, be.getI_size());
			pstmt.setInt(5, be.getUnit());
			pstmt.setString(6, be.getStore_code());
			pstmt.setInt(7, be.getPrice());
			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;

	}

	public ResultSet item_list_show(String icode) { // 품목정보
		String sql = "select * from item_list where i_name = nvl(?, i_name)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, icode);
			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	public void edititem(ItemListBean be) { // 아이템 정보 수정
		String sql = "update item_list set i_group = nvl(?,i_group), sub_group=nvl(?,sub_group), "
				+ " i_size=nvl(?, i_size), unit=nvl(?, unit), store_code=nvl(?,store_code), price=nvl(?, price)"
				+ " where i_name = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, be.getI_group());
			pstmt.setString(2, be.getSub_group());
			pstmt.setString(3, be.getI_size());
			pstmt.setInt(4, be.getUnit());
			pstmt.setString(5, be.getStore_code());
			pstmt.setInt(6, be.getPrice());
			pstmt.setString(7, be.getI_name());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteitem(ItemListBean be) {
		// 아이템 삭제
		String sql = "delete from item_list where i_name =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, be.getI_name());
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	public ResultSet initem_list_show() { // 품목정보
		String sql = "insert into item_list vales(?,?,?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet inout_list_show() { // 입출고내역

		String sql = "select sub_group, i_name, nvl(in_count,0) in_count, nvl(out_count,0) out_count, date_list, store_code, bigo "
				+ " from inout_list";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet good_list_show() { // 재고정보

		String sql = "select * from good_list";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}
}
