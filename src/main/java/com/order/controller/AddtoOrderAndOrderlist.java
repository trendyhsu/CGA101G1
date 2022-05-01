package com.order.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.core.utils.MailService;
import com.memCoupon.model.MemCouponJDBCDAO;
import com.memCoupon.model.MemCouponVO;
import com.memCoupon.model.MemCoupon_interface;
import com.member.model.MemVO;
import com.order.model.OrderService;
import com.order.model.OrderVO;
import com.orderdetail.model.OrderDetailDAO;
import com.orderdetail.model.OrderDetailService;
import com.orderdetail.model.OrderDetailVO;
import com.test.Cartdetail;

@WebServlet("/order/addtoOrderAndOrderlist")
public class AddtoOrderAndOrderlist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddtoOrderAndOrderlist() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		HttpSession session = request.getSession();

		MemVO memVO = (MemVO) (session.getAttribute("memVO"));
		Integer memNo = memVO.getMemNo();

		System.out.println("現在下訂單的會員編號是：memNo");
//		Integer memNo = 11001;
		String memCNoString = request.getParameter("MemCouponNo");
//		System.out.println("會員專屬優惠券號碼" + memCNoString);
		Integer memCouponNo = Integer.valueOf(request.getParameter("MemCouponNo"));

		String couponTypeNoStr = request.getParameter("couponTypeNo");
		Integer couponTypeNo = Integer
				.valueOf(request.getParameter("MemCouponNo") == null ? "0" : request.getParameter("MemCouponNo"));
//		System.out.println("優惠券類型代碼：" + couponTypeNo);

		Integer orderTotalPrice = Integer.valueOf(request.getParameter("OrderTotalPrice"));
//		System.out.println("計算後總金額" + orderTotalPrice);

		Integer pickupMethod = Integer.valueOf(request.getParameter("PickupMethod"));
//		System.out.println("運費方式代碼" + pickupMethod);

		Integer shippingFee = (pickupMethod == 1 ? 1 : 0);
//		System.out.println("運費代碼" + shippingFee);

		String receiverAddressCity = request.getParameter("city");
//		System.out.println("縣市：" + receiverAddressCity);
		String receiverAddressDist = request.getParameter("dist");
//		System.out.println("鄉鎮市：" + receiverAddressDist);
		String receiverAddressRod = request.getParameter("Rod");
//		System.out.println("路號：" + receiverAddressRod);
		String receiverAddress = receiverAddressCity + receiverAddressDist + receiverAddressRod;
//		System.out.println("完整地址：" + receiverAddress);

		String receiverName = request.getParameter("ReceiverName");
//		System.out.println("收件人姓名：" + receiverName);

		String receiverPhone = request.getParameter("ReceiverPhone");
//		System.out.println("收件人姓名：" + receiverPhone);

		/******** step1.判斷有沒有使用優惠券，並新增到orderTable ************/
		if (!(memCouponNo == 0)) {
			OrderService orderService = new OrderService();
			orderService.AddNewOrderWithC(memNo, memCouponNo, orderTotalPrice, pickupMethod, shippingFee, receiverName,
					receiverAddress, receiverPhone);
			/**** 更改會員的優惠券狀態為1 在memcouponTable *******/
			MemCouponVO memCouponVO = new MemCouponVO();
			memCouponVO.setCouponState(1);
			memCouponVO.setCouponTypeNo(couponTypeNo);
			memCouponVO.setMemCouponNo(memCouponNo);
			memCouponVO.setMemNo(memNo);

			MemCouponJDBCDAO dao = new MemCouponJDBCDAO();
			dao.changestate(memCouponVO);
			System.out.println("更改優惠券狀態為已經使用");
			
		} else {
			OrderService orderService = new OrderService();
			orderService.AddNewOrderWithoutC(memNo, orderTotalPrice, pickupMethod, shippingFee, receiverName,
					receiverAddress, receiverPhone);

		}

		/******* step2.新增訂單明細 ************/

		/**** a)先查訂單編號 *****/
		OrderService orderService = new OrderService();
		OrderVO orderVO = orderService.FindNewetOrderInMem(memNo);
		Integer orderNo = orderVO.getOrderNo();

		/**** b)新增訂單項目 ****/
		List<Cartdetail> orderList = (List<Cartdetail>) session.getAttribute("shoppingCart");
		OrderDetailService orderDetailService = new OrderDetailService();
//		System.out.println("開始新增訂單項目");
		for (Cartdetail item : orderList) {
			OrderDetailVO orderDetailVO = new OrderDetailVO();
			Integer productNo = Integer.valueOf(item.getProductNo());
			Integer productSales = item.getProductSales();
			Integer productTotalPrice = item.getProductTotalPrice();
			orderDetailVO.setOrderNo(orderNo);
			orderDetailVO.setProductNo(productNo);
			orderDetailVO.setProductSales(productSales);
			orderDetailVO.setProductTotalPrice(productTotalPrice);
			orderDetailService.addNew(orderDetailVO);
		}
//		System.out.println("新增結束");

		/************  新增成功寄mail通知   **************/
		System.out.println("開始寄信");
		MailService mailService = new MailService();
//		String receiverMailAddress=memVO.getMemEmail();
		String targetName =memVO.getMemName();
		String receiverMailAddress = "openthedidi2004@gmail.com";
		String subject = "親愛的會員："+ targetName+"女士/先生您好，您的訂單成立囉";
		StringBuffer messageText = new StringBuffer();
		messageText.append("本次訂單編號為"+orderNo);
		messageText.append(System.getProperty("line.separator"));
		messageText.append("金額為"+orderTotalPrice);
		messageText.append(System.getProperty("line.separator"));
		
		
		
		mailService.sendMail(receiverMailAddress, subject, messageText.toString());
		System.out.println("寄信完畢");
		
		
		
		session.removeAttribute("shoppingCart");

		response.sendRedirect("/CGA101G1/frontend/Product/account-order.html");

		/******* step3.依照結帳方式轉到合適畫面 ************/
	}
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
