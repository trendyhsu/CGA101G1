package com.productPic.controller;

import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.productPic.model.ProductPicService;

/**
 * Servlet implementation class GetOnePicbyProductNo
 */
@WebServlet("/product/OutPicByServerlet")
public class OutPicByServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public OutPicByServerlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("image/gif");
		ProductPicService productPicService = new ProductPicService();
		productPicService.onePicInByte(Integer.valueOf(request.getParameter("ProductPicNO")));
		
		ServletOutputStream out = response.getOutputStream();
		out.write(productPicService.onePicInByte(Integer.valueOf(request.getParameter("ProductPicNO"))).getProductPicContentByte());
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
