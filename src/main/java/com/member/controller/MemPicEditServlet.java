package com.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.member.utils.MemeberConstants.MEM_SERVICE;

import com.member.model.MemVO;

@WebServlet("/mem/MemPicEditServlet")
public class MemPicEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//取得會員
		MemVO memVO = (MemVO) request.getSession().getAttribute("member");
		String memAccount = memVO.getMemAccount();
		//讀取圖檔
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		//轉字串 解碼存進陣列
		byte[] myPicByte = Base64.getDecoder().decode(jsonIn.toString());
				MEM_SERVICE.memEditPic(memAccount, myPicByte);
	}

}
