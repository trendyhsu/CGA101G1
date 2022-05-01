package com.gamenews.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamenews.model.GameNewsService;
import com.gamenews.model.GameNewsVO;

@WebServlet("/gameNews/gameNewsPic")
public class GameNewsPicServlet extends HttpServlet {

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
		resp.setContentType("image/gif");
		
		ServletOutputStream out = resp.getOutputStream();
		Integer gameNewsNo = Integer.valueOf(req.getParameter("gameNewsNo"));
//		System.out.print(gameNewsNo);
		GameNewsService gnSvc = new GameNewsService();
		GameNewsVO gameNewsVO = gnSvc.getOne(gameNewsNo);
		if(gameNewsVO.getGameNewsPic() != null) {
			System.out.println(gameNewsVO.getGamePlatformNo());
			out.write(gameNewsVO.getGameNewsPic());
		}
		
	}
	
	

}
