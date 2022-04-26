package com.member.controller;

import static com.core.utils.JSONTrans.writePojo2Json;
import static com.member.utils.MemeberConstants.MEM_SERVICE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemVO;

@WebServlet("/mem/MemSelfInfo")
public class MemSelfInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");

		MemVO mem =(MemVO)request.getSession().getAttribute("memVO");
		MemVO memVO = MEM_SERVICE.showMemInfo(mem);
		writePojo2Json(response, memVO);
		
	}

}
