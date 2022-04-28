package com.managerauthrizationfunction.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.managerauthrizationfunction.model.ManagerAuthrizationFunctionService;

/**
 * Servlet implementation class GameTypeGetAll
 */
@WebServlet("/managerauthrizationfunction/getAllManagerAuthrizationFunction")
public class ManagerAuthrizationFunctionGetAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerAuthrizationFunctionGetAll() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ManagerAuthrizationFunctionService managerAuthrizationFunctionService =new ManagerAuthrizationFunctionService();
		
		Gson gson = new Gson();
		String json = gson.toJson(managerAuthrizationFunctionService.getAll());
		out.print(json);
		System.out.println(json);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
