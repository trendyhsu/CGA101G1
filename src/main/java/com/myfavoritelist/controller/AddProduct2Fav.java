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

@WebServlet("/product/AddProduct2Fav")
public class AddProduct2Fav extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddProduct2Fav() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemVO memVO = (MemVO) (session.getAttribute("memVO"));
		PrintWriter out = response.getWriter();

		if (memVO == null) {
			/**** 沒有會員身分回傳0 ****/
			out.print(0);
//			response.sendRedirect("/CGA101G1/frontend/memLogin/login.html");
		} else {

			Integer memNo = memVO.getMemNo();
//		Integer memNo = 11003;
			// 注意要會員編號改成活的

//			System.out.println(request.getParameter("ProductNo"));
			Integer productNo = ((request.getParameter("ProductNo").equals("") ? 0
					: Integer.valueOf(request.getParameter("ProductNo"))));
//			System.out.println("轉成數字版：" + productNo);
			// 先查有沒有重複

			MyfavoritelistService myfavoritelistService = new MyfavoritelistService();
			MyfavoritelistVo myfavoritelistVo = myfavoritelistService.getOneByOneMem(memNo, productNo);
			if (myfavoritelistVo.getProductNo() == 0) {
//				System.out.println("沒有加過要加入清單");
				myfavoritelistService.addOne(memNo, productNo);
//				response.sendRedirect("/CGA101G1/frontend/Product/HomePageinshop.html");
				/**** 成功加入回傳1 ****/
				out.print(1);
			} else {

				/**** 成功加入回傳2 ****/
//				System.out.println("重複添加");
				out.print(2);
//				response.sendRedirect("/CGA101G1/frontend/Product/HomePageinshop.html");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
