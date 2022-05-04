package com.fqkeyword.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fqkeyword.model.FQKeyWordService;
import com.fqkeyword.model.FQKeyWordVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/fqkeyword/all")
public class FQKeyWordGetAll extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		List<FQKeyWordVO> list = new ArrayList<>();
		FQKeyWordService fqSvc = new FQKeyWordService();
		List<Map<String, Object>> keyWordList = new ArrayList<>();
		list = fqSvc.getAll();
		
		for(FQKeyWordVO keyWord : list) {
			Map<String, Object> map = new HashMap<>() ;
			map.put("fqKeyWordContent", keyWord.getFqKeyWordContent());
			map.put("answerContent", keyWord.getAnswerContent());
			keyWordList.add(map);
		}
		Gson g = new GsonBuilder().disableHtmlEscaping().create();
		String data = g.toJson(keyWordList);
		out.print(data);
		
	}
	
}
