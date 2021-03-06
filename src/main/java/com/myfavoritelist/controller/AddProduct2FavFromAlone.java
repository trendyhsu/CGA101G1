package com.myfavoritelist.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemVO;
import com.myfavoritelist.model.MyfavoritelistService;
import com.myfavoritelist.model.MyfavoritelistVo;

/**
 * Servlet implementation class AddProduct2FavFromAlone
 */
@WebServlet("/product/AddProduct2FavFromAlone")
public class AddProduct2FavFromAlone extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddProduct2FavFromAlone() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		MemVO memVO = (MemVO) (session.getAttribute("memVO"));
		PrintWriter out = response.getWriter();

		if (memVO == null) {
			out.print(0);
//			response.sendRedirect("/CGA101G1/frontend/memLogin/login.html");
		} else {

			Integer memNo = memVO.getMemNo();
//		Integer memNo = 11003;
			// 注意要會員編號改成活的

			Integer productNo = ((request.getParameter("ProductNo").equals("") ? 0
					: Integer.valueOf(request.getParameter("ProductNo"))));

			// 先查有沒有重複

			MyfavoritelistService myfavoritelistService = new MyfavoritelistService();
			MyfavoritelistVo myfavoritelistVo = myfavoritelistService.getOneByOneMem(memNo, productNo);
			if (myfavoritelistVo.getProductNo() == 0) {
//			System.out.println("沒有加過要加入清單");
				myfavoritelistService.addOne(memNo, productNo);
				out.print(1);
//				response.sendRedirect("/CGA101G1/frontend/Product/HomePageinProduct.html?ProductNo=" + productNo);
			} else {
//			System.out.println("重複添加");
//				response.sendRedirect("/CGA101G1/frontend/Product/HomePageinProduct.html?ProductNo=" + productNo);
				out.print(2);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
