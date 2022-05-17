package com.memCoupon.model;

import java.util.List;

import org.hibernate.Session;

import com.core.utils.HibernateUtil;

public interface MemCoupon_interface {

	public void insert(MemCouponVO memCouponVO);
	public void update(MemCouponVO memCouponVO);
	public void delete(Integer  memCouponNo);
	public MemCouponVO getOne(Integer  memCouponNo);
	public List<MemCouponVO> getAll();
	default Session getSession() {
		return HibernateUtil
				.getSessionFactory()
				.getCurrentSession();
	}
}
