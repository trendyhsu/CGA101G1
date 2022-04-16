package com.bidpic.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.bidpic.model.BidPicDAO_interface;
import com.bidpic.model.BidPicJDBCDAO;
import com.bidpic.model.BidPicService;
import com.bidpic.model.BidPicVO;

/**
 * Servlet implementation class BidPicUploadServlet
 */
@WebServlet("/BidPicGetOne")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class BidPicGetOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BidPicGetOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("image/gif");
		
		ServletOutputStream out = response.getOutputStream();
		Integer bidProductNo = Integer.parseInt(request.getParameter("bidProductNo"));
		BidPicService bidPicSvc = new BidPicService();
		BidPicVO bidPicVO = bidPicSvc.getMainBidPicByBidProductNo(bidProductNo);
		out.write(bidPicVO.getBidProdPicContent());
		
		// 暫時上傳時需開啟
//		response.setCharacterEncoding("UTF-8");
//		Integer bidProductNo = Integer.parseInt(request.getParameter("bidProductNo"));
//		Part file = request.getPart("file1");
//		InputStream in = file.getInputStream();
//		byte[] buf = new byte[in.available()];
//		in.read(buf);
//		in.close();
//		
//		BidPicService bidPicService = new BidPicService();
//		
//		bidPicService.addBidPic(bidProductNo, buf);
//		
//		request.setAttribute("message", "上傳成功");
//		
//		RequestDispatcher successDispatcher = request.getRequestDispatcher("/backend/bid/addBidPic.jsp");
//		successDispatcher.forward(request, response);
		
	}

}
