package com.manager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manager.model.ManagerService;
import com.manager.model.ManagerVO;
import com.member.model.MemVO;
import static com.core.utils.JSONTrans.json2Pojo;
import static com.core.utils.JSONTrans.writePojo2Json;
import static com.member.utils.MemeberConstants.MEM_SERVICE;
@WebServlet("/manager/managerLogin")
public class ManagerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		//將JSON轉成ManagerVO
		ManagerVO managerVO = json2Pojo(request, ManagerVO.class);
		if(managerVO == null) {
			managerVO =new ManagerVO();
			managerVO.setMessage("查無此會員");
			managerVO.setSuccessful(false);
			writePojo2Json(response, managerVO);
			return;
		}
		//調用service
		ManagerService managerService = new ManagerService();
		managerVO = managerService.login(managerVO);
		
		System.out.println(managerVO.isSuccessful());
		//如果帳密錯誤即立刻回傳
		if(!managerVO.isSuccessful()) {
			writePojo2Json(response, managerVO);
			return;
		}
		//帳號權限關閉回傳
		if(managerVO.getManagerState() == 1) {
			managerVO.setMessage("帳號權限關閉，如有疑問請洽詢徐用整");
			managerVO.setSuccessful(false);
			writePojo2Json(response, managerVO);
			return;
		}
		final HttpSession session = request.getSession();
		//驗證成功設定session
		if(managerVO.isSuccessful()) {
			if(request.getSession(false) != null) {
				request.changeSessionId();
			}
			session.setAttribute("loggedin", true);
			session.setAttribute("managerVO", managerVO);
			
		}
		String initlocation = (String) session.getAttribute("initlocation");
		managerVO.setInitlocation(initlocation);
		System.out.println(managerVO.isSuccessful());
		writePojo2Json(response, managerVO);
		
	}
	
	

	
	
	
}
