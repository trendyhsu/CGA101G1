package com.bidrecord.controller;

import java.io.Console;
import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.member.model.MemVO;

@WebServlet("/bid/bidRecordGetSession")
public class BidRecordGetSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String bidProductNo = request.getParameter("bidProductNo");
		System.out.println(bidProductNo);
		request.getSession().setAttribute("initlocationMem", request.getContextPath() + "/frontend/bid/listonebid.html?bidProductNo="+bidProductNo);
		
		Writer out = response.getWriter();
		HttpSession session = request.getSession();
		Integer memNo = null;
		
		try {
			MemVO memVO =(MemVO)session.getAttribute("memVO");
			memNo = memVO.getMemNo();
		} catch (NullPointerException e) {
			memNo = 0;
		}
		
		String outMemNo = String.valueOf(memNo);
		out.write(outMemNo);

	}

}
