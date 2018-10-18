package Dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.InBusinBean;
import Bean.InListBean;
import Bean.InOutBean;
import Bean.ItemListBean;
import Bean.OutbusinBean;
import Bean.OutlistBean;
import Bean.StoreListBean;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class RunPage
 */
@WebServlet("/RunPage")
public class RunPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RunPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter(); // 웹페이지에 데이터 출력해주는것
		String action = request.getParameter("action");
		String store_name = request.getParameter("store_name");
		String i_group = request.getParameter("i_group");
		String sub_group = request.getParameter("sub_group");
		String i_name = request.getParameter("i_name");
		String i_size = request.getParameter("i_size");
		String unit = request.getParameter("unit");
		String store_code = request.getParameter("store_code");
		String price = request.getParameter("price");

		if (action.equals("itemlist")) {
// 1.품목정보조회
			System.out.println("item list start");
			List<ItemListBean> list = new ArrayList<>();
			ItemListBean be = new ItemListBean();
			Admin_Dao da = new Admin_Dao();
			ResultSet rs = da.item_list_show(i_name);

			try {
				if (rs.next()) {
					do {
						be = new ItemListBean();
						be.setI_group(rs.getString("i_group"));
						be.setSub_group(rs.getString("sub_group"));
						be.setI_name(rs.getString("i_name"));
						be.setI_size(rs.getString("i_name"));
						be.setI_size(rs.getString("i_size"));
						be.setUnit(rs.getInt("unit"));
						be.setStore_code(rs.getString("store_code"));
						be.setPrice(rs.getInt("price"));

						list.add(be);

					} while (rs.next());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.write(JSONArray.fromObject(list).toString());

		} else if (action.equals("edititem")) {
// 1.1.1아이템 수정
			Admin_Dao da = new Admin_Dao();
			ItemListBean be = new ItemListBean();
			be.setI_group(i_group);
			be.setI_name(i_name);
			be.setI_size(i_size);
			be.setPrice(Integer.parseInt(price));
			be.setStore_code(store_code);
			be.setSub_group(sub_group);
			be.setUnit(Integer.parseInt(unit));
			da.edititem(be);
			
		} else if (action.equals("deleteitem")) {
//1.1.2아이템 삭제
			Admin_Dao da = new Admin_Dao();
			ItemListBean be = new ItemListBean();
			be.setI_name(i_name);
			da.deleteitem(be);

		} else if (action.equals("insertitem")) {
//1.1.2아이템 입력
			Admin_Dao da = new Admin_Dao();
			ItemListBean be = new ItemListBean();
			be.setI_group(i_group);
			be.setI_name(i_name);
			be.setI_size(i_size);
			be.setPrice(Integer.parseInt(price));
			be.setStore_code(store_code);
			be.setSub_group(sub_group);
			be.setUnit(Integer.parseInt(unit));
			
			da.insertitem(be);

		} else if (action.equals("storelist")) {
			// 1.2 창고정보 조회
			System.out.println("store list start");
			List<StoreListBean> list = new ArrayList<>();
			StoreListBean be = new StoreListBean();
			Admin_Dao da = new Admin_Dao();
			ResultSet rs = da.store_list_show();
			try {
				if (rs.next()) {
					do {
						be = new StoreListBean();
						be.setStore_code(rs.getString("store_code"));
						be.setStore_name(rs.getString("store_name"));
						be.setExpla(rs.getString("expla"));
						list.add(be);
					} while (rs.next());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.write(JSONArray.fromObject(list).toString());

		} else if (action.equals("upstore")) {
// 1.2 창고정보 수정
			System.out.println("upstore start");
			Admin_Dao da = new Admin_Dao();
			StoreListBean sb = new StoreListBean();
			da.upstore();

		} else if (action.equals("inoutlist")) {
// 1.3입출고 내역
			System.out.println("inoutlist start");
			List<InOutBean> list = new ArrayList<>();
			InOutBean be = new InOutBean();
			Admin_Dao da = new Admin_Dao();
			ResultSet rs = da.inout_list_show();
			try {
				if (rs.next()) {
					do {
						be = new InOutBean();
						be.setSub_group(rs.getString("sub_group"));
						be.setI_name(rs.getString("i_name"));
						be.setIn_count(rs.getInt("in_count"));
						be.setOut_count(rs.getInt("out_count"));
						be.setDate_list(rs.getString("date_list"));
						be.setStore_code(rs.getString("store_code"));
						be.setBigo(rs.getString("bigo"));
						list.add(be);
					} while (rs.next());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.write(JSONArray.fromObject(list).toString());

		} else if (action.equals("inbusinlist")) {
// 1.4구입업체 정보
			System.out.println("inbusinlist start");
			List<InBusinBean> list = new ArrayList<>();
			InBusinBean be = new InBusinBean();
			inBusinDao da = new inBusinDao();
			ResultSet rs = da.in_list_busin();
			try {
				if (rs.next()) {
					do {
						be = new InBusinBean();
						be.setBusin_code(rs.getString("busin_code"));
						be.setBusin_name(rs.getString("busin_name"));
						be.setBusin_addr(rs.getString("busin_addr"));
						be.setBusin_num(rs.getString("busin_num"));
						be.setCeo(rs.getString("ceo"));
						list.add(be);
					} while (rs.next());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.write(JSONArray.fromObject(list).toString());

		} else if (action.equals("outbusin")) { // 1.5판매업체정보
			System.out.println("outbusin start");
			List<OutbusinBean> list = new ArrayList<>();
			OutbusinBean be = new OutbusinBean();
			OutBusinDao da = new OutBusinDao();
			ResultSet rs = da.viewOutBusin();
			try {
				if (rs.next()) {
					do {
						be = new OutbusinBean();
						be.setBusin_code(rs.getString("busin_code"));
						be.setBusin_name(rs.getString("busin_name"));
						be.setBusin_addr(rs.getString("busin_addr"));
						be.setBusin_num(rs.getString("busin_num"));
						be.setCeo(rs.getString("ceo"));
						list.add(be);

					} while (rs.next());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.write(JSONArray.fromObject(list).toString());
		}

		else if (action.equals("outbusin")) { // 2.1 구매정보생성
			System.out.println("outbusin start");
			List<OutbusinBean> list = new ArrayList<>();
			OutbusinBean be = new OutbusinBean();
			OutBusinDao da = new OutBusinDao();
			ResultSet rs = da.viewOutBusin();
			try {
				if (rs.next()) {
					do {
						be = new OutbusinBean();
						be.setBusin_code(rs.getString("busin_code"));
						be.setBusin_name(rs.getString("busin_name"));
						be.setBusin_addr(rs.getString("busin_addr"));
						be.setBusin_num(rs.getString("busin_num"));
						be.setCeo(rs.getString("ceo"));
						list.add(be);

					} while (rs.next());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.write(JSONArray.fromObject(list).toString());
		}

		else if (action.equals("outbusin")) { // 2.1 판매정보생성
			System.out.println("outbusin start");
			List<OutbusinBean> list = new ArrayList<>();
			OutbusinBean be = new OutbusinBean();
			OutBusinDao da = new OutBusinDao();
			ResultSet rs = da.viewOutBusin();
			try {
				if (rs.next()) {
					do {
						be = new OutbusinBean();
						be.setBusin_code(rs.getString("busin_code"));
						be.setBusin_name(rs.getString("busin_name"));
						be.setBusin_addr(rs.getString("busin_addr"));
						be.setBusin_num(rs.getString("busin_num"));
						be.setCeo(rs.getString("ceo"));
						list.add(be);

					} while (rs.next());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.write(JSONArray.fromObject(list).toString());
		} else if (action.equals("inlist")) { // 2.1 구매정보 조회
			System.out.println("inlist start");
			List<InListBean> list = new ArrayList<>();
			InListBean be = new InListBean();
			inBusinDao da = new inBusinDao();
			ResultSet rs = da.inlist();
			try {
				if (rs.next()) {
					do {
						be = new InListBean();
						be.setBuy_num(rs.getString("buy_num"));
						be.setSub_info(rs.getInt("sub_info"));
						be.setSub_group(rs.getString("sub_group"));
						be.setI_name(rs.getString("i_name"));
						be.setI_count(rs.getInt("i_count"));
						be.setI_count(rs.getInt("price"));
						be.setMoney(rs.getInt("money"));
						be.setIn_date(rs.getString("in_date"));
						be.setBusin_code(rs.getString("busin_code"));
						be.setStore_code(rs.getString("store_code"));

						list.add(be);

					} while (rs.next());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.write(JSONArray.fromObject(list).toString());
		}

		else {
			System.out.println("잘못된 action입니다.");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
