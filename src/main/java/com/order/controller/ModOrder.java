package com.order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.core.utils.MailService;
import com.member.model.MemVO;
import com.order.model.OrderService;
import com.order.model.OrderVO;
import com.orderdetail.model.OrderDetailService;
import com.orderdetail.model.OrderDetailVO;

@WebServlet("/porduct/modOrder")
public class ModOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ModOrder() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");

		request.removeAttribute("errorMsgs");
		List<String> errorMsgs = new LinkedList<String>();

		request.setAttribute("errorMsgs", errorMsgs);
		OrderVO checkedOrderVO = new OrderVO();
		PrintWriter out = response.getWriter();

		/*--------------前端輸入資訊獲取--------------------*/

		/******* 收件人姓名 **********/
		String receiverName = request.getParameter("ReceiverName");
		String nameRule = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_:)(\\-\\)]{2,30}$";
		if (receiverName.trim().length() == 0 || receiverName == null) {
			errorMsgs.add("收件人姓名: 不能空白");
		} else if (!receiverName.trim().matches(nameRule)) {
			System.out.println("收件人姓名: 只能包含中文.....");
			errorMsgs.add("收件人姓名: 只能包含中文、英文大小寫、數字和底線及冒號 , 且長度須在2到30之間");
		}
		checkedOrderVO.setReceiverName(receiverName);
		System.out.print("收件人姓名: " + receiverName);

		/******* 收件人地址 **********/
		String receiverAddress = request.getParameter("ReceiverAddress");
		String addressRule = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)(\\-\\)]{2,30}$";
		if (receiverAddress.trim().length() == 0 || receiverAddress == null) {
			errorMsgs.add("收件人地址: 不能空白");
		} else if (!receiverAddress.trim().matches(addressRule)) {
			errorMsgs.add("收件人地址: 只能包含中文、英文大小寫、數字, 且長度須在2到30之間");
		}
		checkedOrderVO.setReceiverAddress(receiverAddress);
		System.out.print("收件人地址:" + receiverAddress);

		/******* 收件人電話 **********/
		String receiverPhone = request.getParameter("ReceiverPhone");
		String phoneRule = "^\\d{8,15}$";
		if (receiverPhone.trim().length() == 0 || receiverPhone == null) {
			errorMsgs.add("收件人電話: 不能空白");
		} else if (!receiverPhone.trim().matches(phoneRule)) {
			errorMsgs.add("收件人電話: 長度須在8到15之間");
		}
		checkedOrderVO.setReceiverPhone(receiverPhone);
		System.out.print("收件人電話:" + receiverPhone);

		/****** orderNo **********/
		String orderNo_Str = request.getParameter("OrderNo");
		System.out.println(orderNo_Str);
		Integer orderNo = Integer.valueOf(orderNo_Str);
		checkedOrderVO.setOrderNo(orderNo);

		/****** OrderTotalPrice **********/
		String orderTotalPrice_Str = request.getParameter("OrderTotalPrice");
		System.out.println(orderTotalPrice_Str);
		Integer orderTotalPrice = Integer.valueOf(orderTotalPrice_Str);
		checkedOrderVO.setOrderTotalPrice(orderTotalPrice);

		/****** TranTime **********/
		String tranTime_Str = request.getParameter("TranTime");
		System.out.println(tranTime_Str);
		java.sql.Timestamp tranTime = java.sql.Timestamp.valueOf(request.getParameter("TranTime"));
		checkedOrderVO.setTranTime(tranTime);

		/****** OrderState **********/
		String orderState_Str = request.getParameter("OrderState");
		System.out.println(orderState_Str);
		Integer orderState = Integer.valueOf(orderState_Str);
		checkedOrderVO.setOrderState(orderState);

		/****** 取貨方式 **********/
		String pickupMethod_Str = request.getParameter("PickupMethod");
		System.out.println(pickupMethod_Str);
		Integer pickupMethod = Integer.valueOf(pickupMethod_Str);
		checkedOrderVO.setPickupMethod(pickupMethod);

		/****** 物流編號 **********/
		String trackingNum = request.getParameter("TrackingNum");
		System.out.println(trackingNum);
		if (orderState == 4 && !trackingNum.equals("0")) {
			errorMsgs.add("訂單作廢的話，物流編號請填0");
		}
		checkedOrderVO.setReceiverPhone(receiverPhone);

		checkedOrderVO.setTrackingNum(trackingNum);

		/********* 有錯誤訊息就回到編輯頁面 *************/

		if (!(errorMsgs.size() == 0)) {
			System.out.println("錯誤訊息forward開始");

			System.out.println("錯誤訊息forward到新增頁面開始");
			/***** 帶著錯誤訊息導到新增頁面 *******/
			request.setAttribute("orderVO", checkedOrderVO);

			RequestDispatcher failureView = request.getRequestDispatcher("/backend/product/editOrder.jsp");
			failureView.forward(request, response);

			return; // 程式中斷
		}

		/********* 沒有錯誤訊息開始更新 *************/
		System.out.println("開始更新訂單");
		OrderService orderService = new OrderService();
		orderService.updateOrder(checkedOrderVO);
		
		

		/********** 訂單如果作廢也要同步更新明細 *****/
		if (orderState == 4) {
			System.out.println("訂單作廢也把明細歸0");
			List<OrderDetailVO> list = checkedOrderVO.getAllDetailByOrderNo(orderNo);
			for (OrderDetailVO orderDetailVO : list) {
				OrderDetailService orderDetailService = new OrderDetailService();
				orderDetailService.clearDetail(orderDetailVO);
			}
		}
		

		/** 如果是完成訂單要寄信給訂購人 ***/

		Integer memNo = Integer.valueOf(request.getParameter("MemNo"));
		
		if (orderState == 2) {
			System.out.println("因為完成訂單了所以開始寄信");
			System.out.println("完成訂單的編號："+orderNo);
			MemVO memVO = checkedOrderVO.getMemVObyMemNo(memNo);
			
			MailService mailService = new MailService();
//		String receiverMailAddress=memVO.getMemEmail();
			String targetName = memVO.getMemName();
			String receiverMailAddress = "openthedidi2004@gmail.com";
			String subject = "親愛的會員：" + targetName + "女士/先生您好，您的訂單已經完成囉";
			StringBuffer messageText = new StringBuffer();
			messageText.append("本次訂單編號為" + orderNo);
			messageText.append(System.getProperty("line.separator"));
			messageText.append("金額為" + orderTotalPrice);
			messageText.append(System.getProperty("line.separator"));
			messageText.append("您可以前往訂單管理進行發表遊戲評論的動作囉");
			
			
			mailService.sendMail(receiverMailAddress, subject, messageText.toString());
			System.out.println("寄信完畢");
		}


		/*********** 更新完成轉看更新後成果 *********************/

		System.out.println("更新完成開始forward到ShowOrder.jsp");
		request.setAttribute("orderVO", checkedOrderVO);
		RequestDispatcher failureView = request.getRequestDispatcher("/backend/product/ShowOrder.jsp");
		failureView.forward(request, response);

		return; // 程式中斷

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
