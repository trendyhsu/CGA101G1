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
public class TimerUpdateCouponState extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Timer timer;

	// 開始時建立
	public void init() throws ServletException {	
		// 建立執行器使用run方法
		TimerTask task = new TimerTask() {
			public void run() {
				MemCouponService memCouponService = new MemCouponService();
				List<MemCouponVO> list = memCouponService.showAllMemCoupon();
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
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
						
				}
			}
		};
		timer = new Timer();
	    Calendar cal = new GregorianCalendar(2022, Calendar.APRIL, 28, 0, 0, 0);
	    timer.scheduleAtFixedRate(task, cal.getTime(), 1000);
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}

	public void destroy() {
		timer.cancel();
	}

}
