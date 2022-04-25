package com.gameplatformtype.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gameplatformtype.model.GamePlatformTypeService;
import com.gametype.model.GameTypeService;
import com.google.gson.Gson;

/**
 * Servlet implementation class GameTypeGetAll
 */
@WebServlet("/gameplatformtype/getAllGamePlatformType")
public class GamePlatformTypeGetAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GamePlatformTypeGetAll() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();
		GamePlatformTypeService gamePlatformTypeSer =new GamePlatformTypeService();
		
		Gson gson = new Gson();
		String json = gson.toJson(gamePlatformTypeSer.getAll());
		out.print(json);
		System.out.println(json);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
