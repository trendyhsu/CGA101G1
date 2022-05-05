package com.bidproduct.controller;

import java.io.IOException;
import java.sql.Timestamp;
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
import com.core.utils.MailService;
import com.member.model.MemService;
import com.member.model.MemVO;

//@WebServlet(loadOnStartup = 10, urlPatterns={"/bid/bidSchedule2"})
@WebServlet("/bid/bidSchedule2")
public class BidScheduleServlet2 extends HttpServlet {
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
		// 先將所有BidProduct放入ServletContext中
		BidProductService bidProductSvc = new BidProductService();
		List<BidProductVO> bidProductVOAll = bidProductSvc.getAll();
		getServletContext().setAttribute("bidProductVOAll", bidProductVOAll);

		System.out.println("目前競標Schedule有開啟!請注意效能");

		timer = new Timer();
		// 設定何時開始
		Calendar cal = new GregorianCalendar(2022, Calendar.APRIL, 4, 0, 0, 0);

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
				
				// 從ServletContext取得bidProductVOAll
				list = (List<BidProductVO>) getServletContext().getAttribute("bidProductVOAll");

				for (BidProductVO bidProductVO : list) {
					// 查詢bidState = 0 而且截標時間小於現在時間
					if (bidProductVO.getBidState() == 0
							&& bidProductVO.getBidSoldTime().before(new Timestamp(System.currentTimeMillis()))) {

						// 取得該商品編號
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
							bidProductSvc.updateBidStateHaveBuyer(bidState, buyerNo, bidWinnerPrice, bidProductNo);
							
							// 寄信通知最高出價者 已經成功得標
							MailService mailService = new MailService();
							
							MemService memSvc = new MemService();
							
							String to = memSvc.getMemVObyMemNo(buyerNo).getMemEmail();
							
							String subject = "您已得標囉！";
							
							String targetName = memSvc.getMemVObyMemNo(buyerNo).getMemName();
							
							String messageText = "親愛的帕GAME會員 " + targetName +" 您好!" + "\n\n" + "在此通知您商品已經得標，請於30分鐘內前往會員中心結帳，感謝您的參與！";
							
							mailService.sendMail(to, subject, messageText);
							
						} else {
							// 修改 bidState就可
							bidProductSvc.updateBidState(bidState, bidProductNo);
						}
						// 查詢新的bidProductVOAll 放回 ServletContext
						List<BidProductVO> bidProductVOAll = bidProductSvc.getAll();
						getServletContext().setAttribute("bidProductVOAll", bidProductVOAll);
					}
				}
			}
		};

//		 截標後三十分鐘把原本的競標狀態改為棄標
		TimerTask changeOrderState = new TimerTask() {

			@Override
			public void run() {
				List<BidProductVO> list = new ArrayList<>();
				BidProductService bidProductSvc = new BidProductService();

				// 從Context取得大於三十分鐘沒結帳的list
				list = (List<BidProductVO>) getServletContext().getAttribute("bidProductVOAll");

				for (BidProductVO bidProductVO : list) {
					if (bidProductVO.getBidState() == 1 && bidProductVO.getOrderState() == 0
							&& (bidProductVO.getBidSoldTime())
									.before(new Timestamp(System.currentTimeMillis() - (1000 * 60 * 30)))) {
						// 將競標商品狀態改為狀態 3 棄標
						bidProductSvc.updateBidState(new Integer(3), bidProductVO.getBidProductNo());
						
						// 查詢新的bidProductVOAll 放回 ServletContext
						List<BidProductVO> bidProductVOAll = bidProductSvc.getAll();
						getServletContext().setAttribute("bidProductVOAll", bidProductVOAll);
					}
				}
			}

		};

		timer.scheduleAtFixedRate(changeBidState, cal.getTime(), 1000);
		timer.scheduleAtFixedRate(changeOrderState, cal.getTime(), 1000);
	}

	@Override
	public void destroy() {
		timer.cancel();
	}
}
