package com.gamecompany.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.gamecompany.model.GameCompanyService;
import com.google.gson.Gson;

/**
 * Servlet implementation class GameTypeGetAll
 */
@WebServlet("/gamecompany/getAllGameCompany")
public class GameCompanyGetAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameCompanyGetAll() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();
		GameCompanyService gameCompanyService =new GameCompanyService();
		
		Gson gson = new Gson();
		String json = gson.toJson(gameCompanyService.getAll());
		out.print(json);
		System.out.println(json);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
