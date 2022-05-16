package com.manager.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manager.model.ManagerVO;

@WebServlet("/manager/managerLogout")
public class ManagerLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Integer managerNo = Integer.valueOf(request.getParameter("managerNo"));
//		System.out.println(request.getParameter("managerNo"));
		
//		ManagerVO managerVO=(ManagerVO)request.getSession().getAttribute("managerVO");
		request.getSession().removeAttribute("managerVO");
		String url = "/backend/index.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);
	}

}