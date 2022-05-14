package com.memCoupon.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.memCoupon.model.MemCouponService;
import com.memCoupon.model.MemCouponVO;
@WebServlet("/memCoupon/TimerUpdateCouponState")
//@WebServlet(loadOnStartup = 20, urlPatterns={"/memCoupon/TimerUpdateCouponState"})
public class TimerUpdateCouponState extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Timer timer;

	// 開始時建立
	public void init() throws ServletException {	
		// 建立執行器使用run方法
		MemCouponService memCouponService = new MemCouponService();
		List<MemCouponVO> list = memCouponService.showAllMemCoupon();
		getServletContext().setAttribute("list", list);
		//執行的任務
		TimerTask task = new TimerTask() {
			//實作方法
			public void run() {
				List<MemCouponVO> list =(List<MemCouponVO>)getServletContext().getAttribute("list");
				for (MemCouponVO memCouponVO : list) {
					//取得現在時間年月日做比對
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					java.sql.Date couponDate = memCouponVO.getCouponDate();
					Date today;
					try {
						today = sdf.parse(sdf.format(new Date()));
						Date date = sdf.parse(sdf.format(couponDate));
						if(today.after(date)) {
							memCouponService.changeCouponState(memCouponVO);
							list = memCouponService.showAllMemCoupon();
							getServletContext().setAttribute("list", list);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
						
				}
				
			}
		};
		timer = new Timer();
		Calendar rightNow = Calendar.getInstance();
		  int year = rightNow.get(Calendar.YEAR);
		  int month = rightNow.get(Calendar.MONTH);
		  int day = rightNow.get(Calendar.DAY_OF_MONTH);
		  
		  Calendar cal = new GregorianCalendar(year, month, day, 0, 0, 0);
	    timer.scheduleAtFixedRate(task, cal.getTime(), 10*1000);
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getServletContext();
	}

	public void destroy() {
		timer.cancel();
	}

}
