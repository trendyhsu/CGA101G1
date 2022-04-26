package com.member.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.member.utils.MemeberConstants.MEM_SERVICE;
import com.member.model.MemVO;

@WebServlet("/mem/MemPasswordEditServlet")
public class MemPasswordEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String OmemPassoword = request.getParameter("oldPassword");
			String Reg = "^[(a-zA-z0-9)]{6,12}$";
			// 比對舊密碼
			MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
			Integer memNo = memVO.getMemNo();
			memVO = MEM_SERVICE.checkOldPassword(memNo);
			String memPassoword = memVO.getMemPassword();

			if (OmemPassoword == null || OmemPassoword.trim().length() == 0) {
				errorMsgs.add("舊密碼未輸入");
			} else if (!OmemPassoword.equals(memPassoword)) {
				errorMsgs.add("舊密碼錯誤");
			}

			String newPassword = request.getParameter("newPassword");
			if (newPassword == null || newPassword.trim().length() == 0) {
				errorMsgs.add("新密碼未輸入");
			} else if (!newPassword.trim().matches(Reg)) {
				errorMsgs.add("請輸入英文及數字，且密碼長度需大於5小於13字數");
			}else if (newPassword.equals(memPassoword)) {
				errorMsgs.add("新密碼不得與舊密碼相同");
			}

			String conNewPassword = request.getParameter("conNewPassword");
			if (!conNewPassword.equals(newPassword)) {
				errorMsgs.add("新密碼和確認密碼不相同，請重新輸入");
			}else if (conNewPassword == null || conNewPassword.trim().length() == 0) {
				errorMsgs.add("確認密碼未輸入");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				request.setAttribute("memVO", memVO); // 含有輸入格式錯誤的memVO物件,也存入req
				RequestDispatcher failureView = request.getRequestDispatcher("/frontend/mem/passwordChange.jsp");
				failureView.forward(request, response);
				return; // 程式中斷
			}
			/***************************2.開始修改資料*****************************************/
			MEM_SERVICE.updatePassword(conNewPassword, memNo);
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			request.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的memVO物件,存入req
			String url = "/frontend/mem/index.html";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交index.html
			successView.forward(request, response);

			/***************************其他可能的錯誤處理*************************************/
		} catch (Exception e) {
			errorMsgs.add("修改資料失敗:" + e.getMessage());
			RequestDispatcher failureView = request.getRequestDispatcher("/frontend/mem/passwordChange.jsp");
			failureView.forward(request, response);
		}

	}

}
