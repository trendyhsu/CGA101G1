package com.bidproduct.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bidproduct.model.BidProductService;
import com.bidproduct.model.BidProductVO;
import com.bidrecord.model.BidRecordService;
import com.bidrecord.model.BidRecordVO;

@WebServlet("/bid/bidSchedule")
public class BidScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Timer timer;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	// 初始化時建立 timer 並放置於實體變數
	public void init() {
		timer = new Timer();
		// 設定何時開始
		Calendar cal = new GregorianCalendar(2022, Calendar.MARCH, 24, 0, 0, 0);

		// 截標後把原本競標狀態 0.進行中 更改競標狀態為 1.截標 或 2.流標
		TimerTask changeBidState = new TimerTask() {

			@Override
			public void run() {
				// 建立BidProductVO 的 清單
				List<BidProductVO> list = new ArrayList<>();

				// 呼叫BidRecord 及 BidProduct 的 Service
				BidProductService bidProductSvc = new BidProductService();
				BidRecordService bidRecordSvc = new BidRecordService();

				// 要取出 BidState 為 0 ( 競標中 )的競標商品
				Integer bidState = 0;
				Integer bidWinnerPrice = null;
				Integer buyerNo = null;

				// 查看有無出價紀錄, true 是有人出價
				boolean haveBid = true;

				// 查詢bidState = 0 而且截標時間小於現在時間
				list = bidProductSvc.getByBidStateAndSoldTime();
				for (BidProductVO bidProductVO : list) {
					Integer bidProductNo = bidProductVO.getBidProductNo();

					// 取得該商品最高出價紀錄
					BidRecordVO bidRecordVO = new BidRecordVO();
					bidRecordVO = bidRecordSvc.getHighestByBidProductNo(bidProductNo);
					try {
						bidWinnerPrice = bidRecordVO.getBidPrice();
						buyerNo = bidRecordVO.getMemNo();
						bidState = new Integer(1);

						// 沒有人出價的狀況 查詢會得到null
					} catch (NullPointerException ne) {
						bidWinnerPrice = null;
						buyerNo = null;
						haveBid = false;
						bidState = new Integer(2);
					}

					// 用 haveBid 判斷現在有無出價的狀況
					if (haveBid == true) {
						// 修改 bidState, buyerNo, bidWinnerPrice 
						bidProductSvc.updateBidStateHaveBuyer(bidState,buyerNo,bidWinnerPrice,bidProductNo);
					} else {
						// 修改 bidState就可
						bidProductSvc.updateBidState(bidState,bidProductNo);
					}

				}
			}
		};

		// 截標後三十分鐘把原本的收件狀態改為作廢 暫時沒空寫
//        TimerTask changeOrderState = new TimerTask() {

//			@Override
//			public void run() {
//				List<BidVO> list = new ArrayList<>();
//				BidService bidProductSvc = new BidService();
//				
//				Integer bidProdState = 0;
//				
//				list = bidProductSvc.取得方法 還沒寫;
//				for (BidVO bidVO : list) {
//					bidVO.setOrderState(3);
//					bidSvc.更新狀態(bidVO);
//				}
//			}
//        	
//        };

		timer.scheduleAtFixedRate(changeBidState, cal.getTime(), 1000);
//      timer.scheduleAtFixedRate(changeOrderState, cal.getTime(), 1000); 
	}

	@Override
	public void destroy() {
		timer.cancel();
	}
}
