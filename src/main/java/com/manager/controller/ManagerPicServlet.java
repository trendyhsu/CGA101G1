package com.manager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.manager.model.ManagerService;
import com.manager.model.ManagerVO;

@WebServlet("/manager/managerPic")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ManagerPicServlet extends HttpServlet {

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
		
		
		Integer managerNo = Integer.valueOf(req.getParameter("managerNo"));
		System.out.print(managerNo);
		
		ManagerService managerService = new ManagerService();
		ManagerVO managerVO = managerService.getOneManager(managerNo);
		
		ServletOutputStream out = resp.getOutputStream();
		out.write(managerVO.getMyManagerPic());
	}
	
	

}
