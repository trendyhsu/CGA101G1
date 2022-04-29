package com.memCoupon.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.core.utils.MailService;
import com.couponType.model.CouponTypeService;
import com.member.model.MemService;
import com.member.model.MemVO;

public class MemCouponService {

	private MemCouponJDBCDAO dao;

	public MemCouponService() {
		dao = new MemCouponJDBCDAO();
	}

	// 秀出所有已發放的優惠券
	public List<MemCouponVO> showAllMemCoupon() {
		return dao.getAll();
	}

	// 秀出擁有某個優惠券種類的會員
	public List<MemCouponVO> listOneCoupon(Integer couponTypeNo) {
		return dao.getOneCouponType(couponTypeNo);
	}

	// 秀出某個會員擁有那些優惠券
	public List<MemCouponVO> listOneMemCoupon(Integer memNo) {
		return dao.getOneMember(memNo);
	}
	// 自動改變日期過期的優惠券狀態
	public void changeCouponState(MemCouponVO memCouponVO) {
		dao.changestate(memCouponVO);
	}

	// 發放優惠券給指定的隨機數量會員
	public void sendRandomCouponToMem(Integer couponQuantity, Integer couponTypeNo) {
		// 取得本網站會員總數量
		MemService memService = new MemService();
		int memQuantity = memService.listAllMem().size();
		// 判斷發放數量是否超過會員總數量
		if (couponQuantity > memQuantity) {
			int sub = couponQuantity - memQuantity;
			couponQuantity = couponQuantity - sub;
		}
		// 放進Set集合
		Set<Integer> mem = new HashSet<Integer>();
		while (mem.size() < couponQuantity) {
			int memNoSet = (int) (Math.random() * couponQuantity) + 11001;
			mem.add(memNoSet);
		}
		// 取得優惠券期限
		CouponTypeService couponTypeService = new CouponTypeService();
		Date date = couponTypeService.listOneCouponType(couponTypeNo).getCouponDeadline();
		// 發送給這些會員
		MemCouponVO memCouponVO = new MemCouponVO();
		for (Integer memNo : mem) {
			memCouponVO.setCouponTypeNo(couponTypeNo);
			memCouponVO.setMemNo(memNo);
			memCouponVO.setCouponDate(date);
			dao.insert(memCouponVO);
			// 取得會員VO
			MemVO memVO = memService.getMemVObyMemNo(memNo);
			// 寄信通知，帳號非停權才寄信
			if(memVO.getMemStatus() != 0 || memVO.getMemVrfed() != 0) {
			String subject = "恭喜獲得POPGAME優惠券!!";
			String messageText = "Hello!! " + memVO.getMemName() + "  以下是送給您的優惠券: " + "\n" + 
			couponTypeService.listOneCouponType(couponTypeNo).getCouponName() + "\n"+ 
					"歡迎多善加利用，祝您順心購物愉快!!";
			MailService mail = new MailService();
			mail.sendMail(memVO.getMemEmail(), subject, messageText);
			}
		}
	}

	// 直接刪除某個優惠券種類，所有會員都不會有該優惠券
	public void deleteOneCoupon(Integer couponTypeNo) {
		dao.delete(couponTypeNo);
	}
}
