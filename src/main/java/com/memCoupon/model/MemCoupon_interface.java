package com.memCoupon.model;

import java.util.List;

public interface MemCoupon_interface {

	public void insert(MemCouponVO memCouponVO);
	public void update(MemCouponVO memCouponVO);
	public void delete(Integer  memCouponNo);
	public MemCouponVO getOne(Integer  memCouponNo);
	public List<MemCouponVO> getAll();
}
