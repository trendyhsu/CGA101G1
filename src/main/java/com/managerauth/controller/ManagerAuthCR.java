package com.managerauth.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manager.model.ManagerVO;
import com.managerauth.model.ManagerAuthService;
import com.managerauth.model.ManagerAuthVO;
import com.managerauthrizationfunction.model.ManagerAuthrizationFunctionService;
import com.managerauthrizationfunction.model.ManagerAuthrizationFunctionVO;
import com.mysql.cj.x.protobuf.MysqlxCrud.Delete;
import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;

/**
 * Servlet implementation class Test
 */
@WebServlet("/managerAuth/managerAuthCR")
public class ManagerAuthCR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerAuthCR() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		// 從input hidden 取得
		Integer managerNo = Integer.valueOf(request.getParameter("managerNo"));
		System.out.println(request.getParameter("managerNo"));
		
		// 資料庫中目前managerNo有的權限清單
		ManagerAuthService managerAuthService = new ManagerAuthService();
		List<ManagerAuthVO> list = managerAuthService.getFunction(managerNo);
		System.out.println(list);
		// 刪除所有managerNo的權限
		managerAuthService.deleteOneManagerAuthrizationFunction(managerNo);
		
		// 從checkbox取得勾選的list
		String[] managerAuthNos = request.getParameterValues("authName");
		
		// 查詢現在有沒有
		for(String managerAuth:managerAuthNos) {
			managerAuthService.addManagerAuth(managerNo, Integer.valueOf(managerAuth));
		}
		String url = "/backend/manager/getAllManager.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交
		successView.forward(request, response);
	}

}
