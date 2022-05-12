//package com.manager.controller;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.manager.model.ManagerService;
//import com.manager.model.ManagerVO;
//import com.member.model.MemVO;
//import static com.core.utils.JSONTrans.json2Pojo;
//import static com.core.utils.JSONTrans.writePojo2Json;
//import static com.member.utils.MemeberConstants.MEM_SERVICE;
//@WebServlet("/manager/managerAccountCheck")
//public class ManagerAccountCheck extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doPost(request, response);
//	}
//
//	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("application/json; charset=UTF-8");
//		ManagerVO managerVO = json2Pojo(request, ManagerVO.class);
//		//調用service
//		ManagerService managerService = new ManagerService();
//		managerVO = managerService.register(managerVO);
//		writePojo2Json(response, managerVO);
//	}
//	
//}
