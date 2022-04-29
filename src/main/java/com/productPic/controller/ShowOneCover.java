package com.productPic.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.productPic.model.ProductPicService;


@WebServlet("/product/showOneCover")
public class ShowOneCover extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ShowOneCover() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("image/gif");
		ProductPicService productPicService = new ProductPicService();

		
		ServletOutputStream out = response.getOutputStream();
		out.write(productPicService.onePicCoverInByte(Integer.valueOf(request.getParameter("ProductNO"))).getProductPicContentByte());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
