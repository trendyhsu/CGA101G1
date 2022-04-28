package com.member.controller;

import static com.member.utils.MemeberConstants.MEM_SERVICE;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemVO;

@WebServlet("/mem/MemSelfPicServlet")
public class MemSelfPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("image/gif; charset=UTF-8");
		
		ServletOutputStream out = response.getOutputStream();
		//取得會員
		Integer memNo =Integer.parseInt(request.getParameter("memNo"));
		//取得專案路徑的照片 小吳老師教的
		InputStream defaultPicStream = getServletContext().getResourceAsStream("/common/trendy.jpg");
		MemVO memVO = MEM_SERVICE.showMemPic(memNo, defaultPicStream);
		out.write(memVO.getMyPic());
	}

}
