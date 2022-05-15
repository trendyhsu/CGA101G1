package com.productPic.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.productPic.model.ProductPicService;
import com.productPic.model.ProductPicVO;

/**
 * Servlet implementation class ShowPicByPicNo
 */
@WebServlet("/product/showPicByPicNo")
public class ShowPicByPicNo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShowPicByPicNo() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("image/gif");
		ServletOutputStream out = response.getOutputStream();
		ProductPicService productPicService = new ProductPicService();
		Integer productPicNo = Integer.valueOf(request.getParameter("productPicNo"));
		ProductPicVO productPicVO =productPicService.onePicByPicNo(productPicNo);
		out.write(productPicVO.getProductPicContentByte());
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
