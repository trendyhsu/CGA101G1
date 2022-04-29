package com.order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.couponType.model.CouponTypeService;
import com.couponType.model.CouponTypeVO;
import com.google.gson.Gson;
import com.memCoupon.model.MemCouponService;
import com.memCoupon.model.MemCouponVO;
import com.member.model.MemVO;

@WebServlet("/order/showMemAllC")
public class ShowMemAllC extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowMemAllC() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession session =request.getSession();
		MemVO memVO = (MemVO) (session.getAttribute("memVO"));
		Integer memNo = memVO.getMemNo();
//		Integer memNo = 11003;

		MemCouponService memCouponService = new MemCouponService();
		List<MemCouponVO> memCouponList = memCouponService.listOneMemCoupon(memNo);
		List<Object> list = new ArrayList<Object>();

		
		for (MemCouponVO memCouponVO : memCouponList) {
			
			Map<String, Object> map = new HashMap<String, Object>();
			CouponTypeService CouponTypeService = new CouponTypeService();
			map.put("memNo", memNo);
			Integer couponTypeNo = memCouponVO.getCouponTypeNo();

			map.put("couponTypeNo", couponTypeNo);
			Integer memCouponNo = memCouponVO.getMemCouponNo();
			map.put("memCouponNo", memCouponNo);
			Integer couponState = memCouponVO.getCouponState();
			map.put("couponState", couponState);
			CouponTypeVO couponTypeVO = CouponTypeService.listOneCouponType(couponTypeNo);
			Integer discountPrice = couponTypeVO.getDiscountPrice();
			map.put("discountPrice", discountPrice);
			System.out.println(discountPrice);
			list.add(map);
		}

		Gson gson = new Gson();
		String json = gson.toJson(list);
		out.write(json);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
