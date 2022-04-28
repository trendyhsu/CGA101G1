package com.order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;


@WebServlet("/order/ShowMemAllC")
public class ShowMemAllC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ShowMemAllC() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		HttpSession session =request.getSession();
//		String memno = session.getAttribute("member").getMemNo();
		String memNo="11003";
		//                                1                2        3          4                5
		String memCoupons = "select a.MemCouponNo,a.CouponTypeNo,a.MemNo,a.CouponState,b.DiscountPrice "
				+ "from memcoupon a join coupontype b on a.CouponTypeNo = b.CouponTypeNo where a.CouponState = 0 and b.CouponDeadline>now() and MemNo = ? ;";

		List<MemCoupon> memCouponList = new ArrayList<>();
	
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(memCoupons, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setString(1, memNo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MemCoupon memCoupon = new MemCoupon();


				memCoupon.setMemCouponNo(rs.getInt(1));
				memCoupon.setCouponTypeNo(rs.getInt(2));
				memCoupon.setMemNo(rs.getInt(3));
				memCoupon.setCouponState(rs.getInt(4));
				memCoupon.setDiscountPrice(rs.getInt(5));

				
				memCouponList.add(memCoupon);
			}

			Gson gson = new Gson();
			String json = gson.toJson(memCouponList);
			out.print(json);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
