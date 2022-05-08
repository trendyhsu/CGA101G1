package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.member.model.MemService;
import com.member.model.MemVO;


@WebServlet("/product/showShoppingMemInfo")
public class ShowShoppingMemInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ShowShoppingMemInfo() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		MemVO memVO = (MemVO) (session.getAttribute("memVO"));
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		Integer memNo = memVO.getMemNo();
		map.put("memNo", memNo);
		map.put("creditcardDate", memVO.getCreditcardDate());
		map.put("creditcardNo", memVO.getCreditcardNo());
		map.put("City", memVO.getMemCity());
		map.put("Dist", memVO.getMemDist());
		map.put("Add", memVO.getMemAdd());
		map.put("memMobile", memVO.getMemMobile());
		map.put("memName", memVO.getMemName());
		
		Gson gson = new Gson();
		String json = gson.toJson(map);
		out.print(json);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
