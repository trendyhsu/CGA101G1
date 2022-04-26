package com.orderdetail.model;

import java.util.List;

public class OrderDetailService {

	private OrderDetailDAO_interface dao;
	
	public OrderDetailService() {
		dao = new OrderDetailDAO();
	};
	
	
	public void addNew(OrderDetailVO orderDetailVO) {
		dao.newByOrder(orderDetailVO);
	}
	
	public List<OrderDetailVO> GetAllDetailByOrderNo(Integer orderNo){
		return dao.findAllDetailByOrderNo(orderNo);
	}
	
	
}
