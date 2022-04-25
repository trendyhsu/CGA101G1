package com.orderdetail.model;



public class OrderDetailService {

	private OrderDetailDAO_interface dao;
	
	public OrderDetailService() {
		dao = new OrderDetailDAO();
	};
	
	
	public void addNew(OrderDetailVO orderDetailVO) {
		dao.newByOrder(orderDetailVO);
	}
	
}
