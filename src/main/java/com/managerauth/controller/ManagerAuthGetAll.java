package com.managerauth.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.managerauth.model.ManagerAuthService;

/**
 * Servlet implementation class GameTypeGetAll
 */
@WebServlet("/managerauth/getAllManagerAuth")
public class ManagerAuthGetAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerAuthGetAll() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ManagerAuthService managerAuthService =new ManagerAuthService();
		
		Gson gson = new Gson();
		String json = gson.toJson(managerAuthService.getAll());
		out.print(json);
		System.out.println(json);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
