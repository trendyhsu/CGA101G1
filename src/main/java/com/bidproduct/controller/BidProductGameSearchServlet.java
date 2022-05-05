package com.bidproduct.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bidapplylist.model.BidApplyListService;
import com.bidapplylist.model.BidApplyListVO;
import com.bidproduct.model.BidProductService;
import com.bidproduct.model.BidProductVO;
import com.google.gson.Gson;

@WebServlet("/bid/bidProductGameSearch")
public class BidProductGameSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String keyword = request.getParameter("keyword");
		Writer out = response.getWriter();
		
		BidProductService bidProductSvc = new BidProductService();
		BidApplyListService bidApplyListSvc = new BidApplyListService();
		
		// 用來裝整理過後的BidProductVO
		List<BidProductVO> list = new ArrayList<BidProductVO>();
		
		// 過濾是遊戲分類還是遊戲公司還是遊戲平台
		if (keyword.startsWith("64")) {
			List<BidApplyListVO> bidApplyListVOs = bidApplyListSvc.getAllBidApplyListByGamePlatformNo(Integer.valueOf(keyword));
			for(BidApplyListVO bidApplyListVO:bidApplyListVOs) {
				BidProductVO bidProductVO = bidProductSvc.getByBidApplyListNo(bidApplyListVO.getBidApplyListNo());
				if(bidProductVO.getBidProductNo() != null) {
					list.add(bidProductVO);					
				}
				
			}
			
		} else if (keyword.startsWith("63")) {
			List<BidApplyListVO> bidApplyListVOs = bidApplyListSvc.getAllBidApplyListByGameTypeNo(Integer.valueOf(keyword));
			for(BidApplyListVO bidApplyListVO:bidApplyListVOs) {
				BidProductVO bidProductVO = bidProductSvc.getByBidApplyListNo(bidApplyListVO.getBidApplyListNo());
				if(bidProductVO.getBidProductNo() != null) {
					list.add(bidProductVO);					
				}
			}
			
		} else {
			List<BidApplyListVO> bidApplyListVOs = bidApplyListSvc.getAllBidApplyListByGameCompanyNo(Integer.valueOf(keyword));
			for(BidApplyListVO bidApplyListVO:bidApplyListVOs) {
				BidProductVO bidProductVO = bidProductSvc.getByBidApplyListNo(bidApplyListVO.getBidApplyListNo());
				if(bidProductVO.getBidProductNo() != null) {
					list.add(bidProductVO);					
				}
			}
		}

		if (list.size() == 0) {
			list = bidProductSvc.getAll();
			// 查無資料?
		}
		Gson gson = new Gson();
		String json = gson.toJson(list);
		
		out.write(json);
	}

}
