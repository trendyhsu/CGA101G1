package com.bidapplylist.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bidproduct.model.BidProductService;
import com.bidproduct.model.BidProductVO;
import com.bidrecord.model.BidRecordService;
import com.bidrecord.model.BidRecordVO;
import com.member.model.MemVO;

@WebServlet("/bid/bidApplyListMemNo")
public class BidApplyListMemNoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// session 取得會員編號
		MemVO memVO = (MemVO)request.getSession().getAttribute("memVO");
//		Integer memNo = memVO.getMemNo();
		
		Integer memNo = 11002;
		
		BidRecordService bidRecordSvc = new BidRecordService();
		List<BidRecordVO> bidRecordVOs = bidRecordSvc.getByMemNo(memNo);
				
		Set<Integer> bidProductNoSet = new TreeSet<Integer>();
		
		
		// 使用 set 去除重複
		for(BidRecordVO bidRecordVO:bidRecordVOs) {
			bidProductNoSet.add(bidRecordVO.getBidProductNo());
		}
		
		Set<Integer> bidProductNoSet2 = new TreeSet<Integer>();
		for(BidRecordVO bidRecordVO:bidRecordVOs) {
			bidProductNoSet2.add(bidRecordVO.getBidProductNo());
		}
		
		BidProductService bidProductSvc = new BidProductService();
		
		// 
		Iterator<Integer> iterator = bidProductNoSet.iterator();
		
		// 放置過濾完的 bidProductVO
		List<BidProductVO> bidProductVOs = new ArrayList<BidProductVO>();
		List<BidRecordVO> bidRecordVOByProductNos = new ArrayList<BidRecordVO>();
		
		while (iterator.hasNext()) {
			BidProductVO bidProductVO = bidProductSvc.getOneBid(iterator.next());
			bidProductVOs.add(bidProductVO);
		
		}

		Iterator<Integer> iterator2 = bidProductNoSet2.iterator();
		
		while (iterator2.hasNext()) {

		BidRecordVO bidRecordVO = bidRecordSvc.getHighestByBidProductNo(iterator2.next());
		
			if(bidRecordVO != null) {
				bidRecordVOByProductNos.add(bidRecordVO);
			
			}
		}
		// 將過濾好的VO放置於request scope內
		request.setAttribute("bidProductVOs", bidProductVOs);
		request.setAttribute("bidRecordVOByProductNos", bidRecordVOByProductNos);
		
		RequestDispatcher successView = request.getRequestDispatcher("/frontend/bid/mybid.jsp");
		successView.forward(request, response);
	}

}
