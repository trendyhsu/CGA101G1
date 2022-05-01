package com.myfavoritelist.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.member.model.MemVO;
import com.myfavoritelist.model.MyfavoritelistService;
import com.myfavoritelist.model.MyfavoritelistVo;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.productPic.model.ProductPicService;
import com.productPic.model.ProductPicVO;


@WebServlet("/product/ShowMemFavoritelist")
public class ShowMemFavoritelist extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShowMemFavoritelist() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		HttpSession session = request.getSession();
		MemVO memVO = (MemVO) (session.getAttribute("memVO"));
		Integer memNo = memVO.getMemNo();
//		String memNo = "11003";

		
		/*-----------先查項目---------------*/
		MyfavoritelistService myfavoritelist = new MyfavoritelistService();
		List<MyfavoritelistVo> myfavoritelistVoList= myfavoritelist.getAllByOneMem(memNo);
		List<ProductVO> productVOs = new ArrayList<ProductVO>();
		ProductService productService = new ProductService();
		
//		List<ProductPicVO> productPicVOs =new ArrayList<ProductPicVO>();
//		ProductPicService productpicService = new ProductPicService();
		
		
		/*------------查產品---------------*/
		for(MyfavoritelistVo myfavoritelistVo:myfavoritelistVoList) {
			Integer productNo=myfavoritelistVo.getProductNo();
			ProductVO productVO= productService.GetOne(productNo);
			productVOs.add(productVO);
//			
//			ProductPicVO productPicVO = productpicService.getAllCovers(request);
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(productVOs);
		out.print(json);
		
		
		

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
