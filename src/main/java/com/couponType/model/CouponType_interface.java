package com.couponType.model;

import java.util.List;

public interface CouponType_interface {

	public void insert(CouponTypeVO couponTypeVO);
	public void update(CouponTypeVO couponTypeVO);
	public void delete(Integer couponTypeNo);
	public CouponTypeVO getOne(Integer couponTypeNo);
	public List<CouponTypeVO> getAll();
}
