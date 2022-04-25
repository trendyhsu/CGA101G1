package com.myfavoritelist.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myfavoritelist.model.MyfavoritelistService;

/**
 * Servlet implementation class DeleteMemFavor
 */
@WebServlet("/product/deleteMemFavor")
public class DeleteMemFavor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DeleteMemFavor() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();		
//		String memNo = session.getAttribute("Member").getName();
		String memNo = "11003";
		Integer IntMemNo= Integer.valueOf(memNo);
		String deleteProductNo =request.getParameter("ProductNo");
		Integer IntDeleteProdcutNo = Integer.valueOf(deleteProductNo);
		
		MyfavoritelistService myfavoritelistService = new MyfavoritelistService();
		myfavoritelistService.deleteOne(IntMemNo, IntDeleteProdcutNo);
		System.out.println("成功刪除會員: "+memNo+"的最愛商品編號"+deleteProductNo);
		
		response.sendRedirect("/CGA101G1/frontend/Product/HomePageinProduct-wishlist.html");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
