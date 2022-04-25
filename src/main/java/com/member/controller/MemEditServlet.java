package com.member.controller;

import static com.core.utils.JSONTrans.json2Pojo;
import static com.member.utils.MemeberConstants.MEM_SERVICE;
import static com.core.utils.JSONTrans.writePojo2Json;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemVO;

@WebServlet("/mem/MemEditServlet")
public class MemEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		//將JSON轉成MemVO
		MemVO memVO = json2Pojo(request, MemVO.class);
		MEM_SERVICE.memEdit(memVO);
		writePojo2Json(response, memVO);
	}

}
