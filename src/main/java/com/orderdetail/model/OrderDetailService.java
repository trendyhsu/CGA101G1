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
	
	public OrderDetailVO clearDetail(OrderDetailVO orderDetailVO) {
		return dao.clearByOrder(orderDetailVO);
	}
	
	public OrderDetailVO showCommentAfterCaled(Integer productNo) {
		return dao.showCaledCommentByProductNo(productNo);
	}
	
	public void AddComment(OrderDetailVO orderDetailVO) {
		dao.newCommentCotent(orderDetailVO);
	}
	
	public List<OrderDetailVO> AllCommentByProductNo(Integer productNo){
		return dao.findCommentbyProductNo(productNo);
	}
	
	public List<OrderDetailVO> GetTop9Product(){
		return dao.findTop9AllProduct();
	}
	
	public List<Object> GetTop9ProductByMap(){
		return dao.findTop9AllProductByMap();
	}
}
