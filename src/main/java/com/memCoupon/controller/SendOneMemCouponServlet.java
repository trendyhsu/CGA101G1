package com.memCoupon.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.couponType.model.CouponTypeService;
import com.couponType.model.CouponTypeVO;
import com.memCoupon.model.MemCouponService;
import com.memCoupon.model.MemCouponVO;
import com.member.model.MemVO;

/**
 * Servlet implementation class SendOneMemCouponServlet
 */
@WebServlet("/memCoupon/SendOneMemCouponServlet")
public class SendOneMemCouponServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		Map<String, String> errorMsgs1 = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs1", errorMsgs1);
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		//要發放的會員帳號
		String memAccount= request.getParameter("memAccount");
		if("".equals(memAccount.trim())) {
			errorMsgs1.put("memAccount", "會員帳號不得為空白");
		}
		//發放優惠券的種類
		String couponTypeNoStr = request.getParameter("couponTypeNo");
		
		Integer couponTypeNo = null;
		try {
			couponTypeNo = Integer.parseInt(couponTypeNoStr);
		} catch (Exception e) {
			errorMsgs1.put("couponTypeNo", "格式不正確");
		}
		//查找該優惠券有的數量
		CouponTypeService couponTypeService = new CouponTypeService();
		CouponTypeVO couponTypeVO= couponTypeService.listOneCouponType(Integer.parseInt(couponTypeNoStr));
		Integer couponQuantity = couponTypeVO.getCouponQuantity();
		//發放出去該優惠券的數量
		String str = request.getParameter("couponQuantity1");
		Integer sendCouponQuantity = null;
		try {
			sendCouponQuantity = Integer.parseInt(str);
			if(sendCouponQuantity > couponQuantity) {
				errorMsgs1.put("couponQuantity", "發放數量大於您設定的該優惠券數量");
			}
		} catch (Exception e) {
			errorMsgs1.put("couponQuantity", "發放數量格式不正確");
		}
		
		if (!errorMsgs1.isEmpty()) {
			RequestDispatcher failureView = request.getRequestDispatcher("/backend/memCoupon/sendMemCoupon.jsp");
			failureView.forward(request, response);
			return;
		}
		/*************************** 2.開始處理資料 *****************************************/
		MemCouponService memCouponService = new MemCouponService();
		List<MemCouponVO> list =memCouponService.checkMemCoupon(couponTypeNo, memAccount);
		for(MemCouponVO checkMemCouponVO : list ) {
			if(checkMemCouponVO.getCouponState() == 0) {
				errorMsgs1.put("couponQuantity", "該會員已有此優惠券");
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/memCoupon/sendMemCoupon.jsp");
				failureView.forward(request, response);
				return;
			}
		}
		MemVO memVO = memCouponService.sendCouponToOneMem(couponQuantity, couponTypeNo, memAccount);
		if(memVO.getMemStatus() == 0 || memVO.getMemVrfed() == 0) {
			errorMsgs1.put("memAccount", "該會員已遭停權或尚未驗證帳號");
			RequestDispatcher failureView = request.getRequestDispatcher("/backend/memCoupon/sendMemCoupon.jsp");
			failureView.forward(request, response);
			return;
		}
		/***************************3.發放完成,準備轉交(Send the Success view)***********/
		errorMsgs1.clear();
		String url = "/backend/memCoupon/showMemCoupon.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交
		successView.forward(request, response);
	
	
	}

}