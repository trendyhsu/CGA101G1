package com.couponType.model;

import java.util.List;

import org.hibernate.Session;

import com.core.utils.HibernateUtil;

public interface CouponType_interface {

	public void insert(CouponTypeVO couponTypeVO);
	public void update(CouponTypeVO couponTypeVO);
	public void delete(Integer couponTypeNo);
	public CouponTypeVO getOne(Integer couponTypeNo);
	public List<CouponTypeVO> getAll();
	default Session getSession() {
		return HibernateUtil
				.getSessionFactory()
				.getCurrentSession();
	}
}
