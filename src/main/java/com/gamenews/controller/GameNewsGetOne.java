package com.gamenews.controller;

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

import com.gamenews.model.GameNewsService;
import com.gamenews.model.GameNewsVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/gamenews/GameNewsGetOne")
public class GameNewsGetOne extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		Integer gameNewsNo = Integer.valueOf(req.getParameter("gameNewsNo"));
		GameNewsService gnSvc = new GameNewsService();
		GameNewsVO gameNewsVO = gnSvc.getOne(gameNewsNo);
		
		Map<String, Object> map = new HashMap<>();
		map.put("gameNewsNo", gameNewsVO.getGameNewsNo());
		map.put("gamePlatformNo", gameNewsVO.getGamePlatformNo());
		map.put("managerNo", gameNewsVO.getManagerNo());
		map.put("gameNewsTitle", gameNewsVO.getGameNewsTitle());
		map.put("gameNewsContent", gameNewsVO.getGameNewsContent().replace("\r\n", "<br>"));
		map.put("ImgUrl", req.getContextPath()+"/gameNews/gameNewsPic?gameNewsNo="+gameNewsVO.getGameNewsNo());
		
		List<Object> list = new ArrayList<Object>();
		list.add(map);
		Gson g = new GsonBuilder().disableHtmlEscaping().create();
		String data = g.toJson(list);
		out.print(data);
	}
	
	

}
