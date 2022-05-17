package com.couponType.model;

import java.sql.Date;
import java.util.List;

public class CouponTypeService {
	private CouponTypeJDBCDAO dao;

	public CouponTypeService() {
		dao = new CouponTypeJDBCDAO();
	}

	// 秀所有優惠券種類
	public List<CouponTypeVO> showAllCouponType() {
		return dao.getAll();
	}

	// 查找單一優惠券
	public CouponTypeVO listOneCouponType(int couponTypeNo) {
		CouponTypeVO couponTypeVO = dao.getOne(couponTypeNo);
		return couponTypeVO;
	}

	//比對優惠券名稱
	public List<CouponTypeVO> selectOneName(String couponName) {
		List<CouponTypeVO> list = dao.getOneName(couponName);
		return list;
	}
	// 新增優惠券
	public void addNewCouponTypeVO(String couponTypeName, Integer discountPrice,Date couponDeadline, Integer couponQuantity, String couponDescription ) {
		CouponTypeVO couponTypeVO = new CouponTypeVO(); 
		couponTypeVO.setCouponName(couponTypeName);
		couponTypeVO.setDiscountPrice(discountPrice);
		couponTypeVO.setCouponDeadline(couponDeadline);
		couponTypeVO.setCouponQuantity(couponQuantity);
		couponTypeVO.setCouponDescription(couponDescription);
		dao.insert(couponTypeVO);
	}

	// 修改優惠券
	public CouponTypeVO editCouponType(String couponTypeName, Integer discountPrice,Date couponDeadline, Integer couponQuantity, String couponDescription, Integer couponTypeNo) {
		CouponTypeVO couponTypeVO = new CouponTypeVO();
		
		couponTypeVO.setCouponName(couponTypeName);
		couponTypeVO.setDiscountPrice(discountPrice);
		couponTypeVO.setCouponDeadline(couponDeadline);
		couponTypeVO.setCouponQuantity(couponQuantity);
		couponTypeVO.setCouponDescription(couponDescription);
		couponTypeVO.setCouponTypeNo(couponTypeNo);
		dao.update(couponTypeVO);
		return couponTypeVO;
	}
	//刪除優惠券
	public void deleteCouponType(Integer couponTypeNo) {
		dao.delete(couponTypeNo);
	}
	
	
}
