package com.bidapplylist.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bidproduct.model.BidProductService;
import com.bidproduct.model.BidProductVO;
import com.bidrecord.model.BidRecordService;
import com.bidrecord.model.BidRecordVO;

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
//		request.getSession().getAttribute("memVO");
		Integer memNo = 11002;
		BidRecordService bidRecordSvc = new BidRecordService();
		List<BidRecordVO> bidRecordVOs = bidRecordSvc.getByMemNo(memNo);
		
		Set<Integer> bidProductNoSet = new TreeSet<Integer>();
		// 使用 set 去除重複
		for(BidRecordVO bidRecordVO:bidRecordVOs) {
			bidProductNoSet.add(bidRecordVO.getBidProductNo());
			System.out.println(bidRecordVO.getBidProductNo());
		}
		BidProductService bidProductSvc = new BidProductService();
		
		// 
		Iterator<Integer> iterator = bidProductNoSet.iterator();
		
		List<BidProductVO> bidProductVOs = new ArrayList<BidProductVO>();
		
		while (iterator.hasNext()) {
			BidProductVO bidProductVO = bidProductSvc.getOneBid(iterator.next());
			bidProductVOs.add(bidProductVO);
		}
		
	}

}
