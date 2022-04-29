package com.bidapplylist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bidapplylist.model.BidApplyListService;
import com.bidapplylist.model.BidApplyListVO;
import com.member.model.MemVO;
import com.utils.PageUtil;

@WebServlet("/bid/bidApplyListSellerTest")
public class BidApplyListSellerTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 取得會員編號
		MemVO memVO = (MemVO)request.getSession().getAttribute("memVO");
//		Integer memNo = memVO.getMemNo();
		
		Integer memNo = 11001;
		
		BidApplyListService bidApplyListSvc = new BidApplyListService();
		List<BidApplyListVO> bidApplyListVOs = bidApplyListSvc.getAllBidApplyListByMemNo(memNo);
		
		request.setAttribute("bidApplyListVOs", bidApplyListVOs);
		
		
		// 調用分頁工具
		
		int rowsPerPage = 5;  //每頁的筆數    
	    int rowNumber=0;      //總筆數
	    int pageNumber=0;     //總頁數      
	    int whichPage=1;      //第幾頁
	    int pageIndexArray[]=null;
	    int pageIndex=0; 
		
		try {
		       whichPage = Integer.parseInt(request.getParameter("whichPage"));
		       pageIndex=pageIndexArray[whichPage-1];
		    } catch (NumberFormatException e) { //第一次執行的時候
		       whichPage=1;
		       pageIndex=0;
		    } catch (ArrayIndexOutOfBoundsException e) { //總頁數之外的錯誤頁數
		         if (pageNumber>0){
		              whichPage=pageNumber;
		              pageIndex=pageIndexArray[pageNumber-1];
		         }
		    } 
		
//		List<BidApplyListVO> bidApplyListVOs = new PageUtil<BidApplyListVO>().getPagination(bidApplyListVOs2, 2, 2);
		
		// 將資料裝入attribute 帶到jsp顯示
		
		RequestDispatcher successView = request.getRequestDispatcher("/frontend/bid/myapplylist.jsp");
		successView.forward(request, response);
	}

}
