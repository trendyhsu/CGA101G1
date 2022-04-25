package com.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.member.model.MemJDBCDAO;
import com.member.model.MemVO;
import static com.member.utils.MemeberConstants.MEM_SERVICE;
@WebServlet("/mem/MemVerify")
public class MemVerifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		if (request.getSession() !=null) {	
			MemVO mem =(MemVO)request.getSession().getAttribute("member");
			MemJDBCDAO dao=new MemJDBCDAO();
			dao.updateVerify(mem.getMemAccount());
			response.sendRedirect(request.getContextPath()+"/frontend/mem/login.html");
		}else {
			response.sendRedirect(request.getContextPath()+"/frontend/mem/register.html");
		}
		
	}
	
}