package com.forum.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemService;
import com.member.model.MemVO;

@WebServlet("/forum/forumBadMemEditUpdate")
public class ForumBadMemEditUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	// 處理來自editForumMsg.jsp 送出修改商品請求

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		// 存放錯誤訊息 以防我們需要丟出錯誤訊息到頁面
		request.setAttribute("errorMsgs", errorMsgs);

		/*************************** 1.接收請求參數 **********************/

		Integer memNo = Integer.valueOf(request.getParameter("memNo").trim());

		Integer isMute = Integer.valueOf(request.getParameter("isMute").trim());

		MemService MemSvc = new MemService();

		MemVO memVO = new MemVO();

		// 回傳錯誤訊息
		if (!errorMsgs.isEmpty()) {

			memVO.setMemNo(memNo);
			memVO.setIsMute(isMute);

			request.setAttribute("memVO", memVO);

			RequestDispatcher failureView = request.getRequestDispatcher("/backend/forum/editBadMem.jsp");
			failureView.forward(request, response);
			return; // 程式中斷
		}

		/*************************** 2.開始修改資料 *****************************************/

		MemSvc.isMuteChange(isMute, memNo);

		/*************************** 3.修改完成,準備轉交(Send the Success view) *************/

		memVO.setMemNo(memNo);
		memVO = MemSvc.showMemInfo(memVO);

		request.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的memVO物件,存入request
		String url = "/backend/forum/listOneForumBadMem.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交listOneForumBadMem.jsp
		successView.forward(request, response);

	}

}
