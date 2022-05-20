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

	public MemVO sendCouponToOneMem(Integer couponQuantity, Integer couponTypeNo, String memAccount) {
		// 取得該會員
		MemService memService = new MemService();
		MemVO memVO = memService.getByMemAccount(memAccount);
		Integer memNo = memVO.getMemNo();
		// 取得優惠券期限
		CouponTypeService couponTypeService = new CouponTypeService();
		Date date = couponTypeService.listOneCouponType(couponTypeNo).getCouponDeadline();
		// 發放
		if (memVO.getMemStatus() != 0 && memVO.getMemVrfed() != 0) {
			MemCouponVO memCouponVO = new MemCouponVO();
			memCouponVO.setCouponTypeNo(couponTypeNo);
			memCouponVO.setMemNo(memNo);
			memCouponVO.setCouponDate(date);
			dao.insert(memCouponVO);
			String subject = "恭喜獲得POPGAME優惠券!!";
			String messageText = "Hello!! " + memVO.getMemName() + "  以下是送給您的優惠券: " + "\n"
					+ couponTypeService.listOneCouponType(couponTypeNo).getCouponName() + "\n" + "歡迎多善加利用，祝您順心購物愉快!!";
			MailService mail = new MailService();
			mail.sendMail(memVO.getMemEmail(), subject, messageText);
			return memVO;
		} else {
			return memVO;
		}
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
			int memNoSet = (int) (Math.random() * memQuantity) + 11001;
			mem.add(memNoSet);
		}
		// 取得優惠券期限
		CouponTypeService couponTypeService = new CouponTypeService();
		Date date = couponTypeService.listOneCouponType(couponTypeNo).getCouponDeadline();
		MemCouponVO memCouponVO=new MemCouponVO();
		// 發送給這些會員
		for (Integer memNo : mem) {
			// 取得會員VO
			MemVO memVO = memService.getMemVObyMemNo(memNo);
			// 查看是否該會員已有優惠券
			List<MemCouponVO> list = dao.getMemCouponIsHave(couponTypeNo, memNo);
			boolean DontHave = true;
			for(MemCouponVO checkMemCouponVO : list) {
				try {
					if(checkMemCouponVO.getCouponState() == 0) {
					DontHave = false;
					break;
					}
				} catch (NullPointerException e) {
					DontHave = false;
					break;
				}
			}
			// 寄信通知，帳號非停權以及驗證過後才執行
			if (memVO.getMemStatus() != 0 && memVO.getMemVrfed() != 0 && DontHave) {
				memCouponVO.setCouponTypeNo(couponTypeNo);
				memCouponVO.setMemNo(memNo);
				memCouponVO.setCouponDate(date);
				dao.insert(memCouponVO);
				String subject = "恭喜獲得POPGAME優惠券!!";
				String messageText = "Hello!! " + memVO.getMemName() + "  以下是送給您的優惠券: " + "\n"
						+ couponTypeService.listOneCouponType(couponTypeNo).getCouponName() + "\n"
						+ "歡迎多善加利用，祝您順心購物愉快!!";
				MailService mail = new MailService();
				mail.sendMail(memVO.getMemEmail(), subject, messageText);
			}
		}
	}

	// 直接刪除某個優惠券種類，所有會員都不會有該優惠券
	public void deleteOneCoupon(Integer couponTypeNo) {
		dao.delete(couponTypeNo);
	}

	// 查看該會員是否已有此優惠券
	public List<MemCouponVO> checkMemCoupon(Integer couponTypeNo, String memAccount) {
		MemService memService = new MemService();
		MemVO memVO = memService.getByMemAccount(memAccount);
		Integer memNo = memVO.getMemNo();
		List<MemCouponVO> list = dao.getMemCouponIsHave(couponTypeNo, memNo);
		return list;
	}
}
