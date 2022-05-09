package com.order.model;

import java.util.List;
import java.util.Map;

public class OrderService {
	private OrderDAO_interface dao;
	
	public OrderService() {
		dao = new OrderDAO();
	}
	
	
	public OrderVO AddNewOrderWithC(Integer memNo,Integer memCouponNo,Integer orderTotalPrice,Integer pickupMethod,Integer shippingFee,String receiverName,String receiverAddress,String receiverPhone) {
		OrderVO orderVO = new OrderVO();
		orderVO.setMemNo(memNo);
		orderVO.setMemCouponNo(memCouponNo);
		orderVO.setOrderTotalPrice(orderTotalPrice);
		orderVO.setPickupMethod(pickupMethod);
		orderVO.setShippingFee(shippingFee);
		orderVO.setReceiverAddress(receiverAddress);
		orderVO.setReceiverName(receiverName);
		orderVO.setReceiverPhone(receiverPhone);
		dao.newOrderWithC(orderVO);
		return orderVO;
	}
	
	
	public OrderVO AddNewOrderWithoutC(Integer memNo,Integer orderTotalPrice,Integer pickupMethod,Integer shippingFee,String receiverName,String receiverAddress,String receiverPhone) {
		OrderVO orderVO = new OrderVO();
		orderVO.setMemNo(memNo);
		orderVO.setOrderTotalPrice(orderTotalPrice);
		orderVO.setPickupMethod(pickupMethod);
		orderVO.setShippingFee(shippingFee);
		orderVO.setReceiverAddress(receiverAddress);
		orderVO.setReceiverName(receiverName);
		orderVO.setReceiverPhone(receiverPhone);
		dao.newOrderWithouC(orderVO);
		return orderVO;
	}
	

	public OrderVO FindNewetOrderInMem(Integer memNo) {
		return dao.findNewetOrderByMem(memNo);
	}
	
	public List<OrderVO> findAllOrdersByMemNo(Integer memNo){
		return dao.findByMemNo(memNo);
	}
	
	public List<OrderVO> findAllOrders(){
		return dao.getAll();
	}
	
	public List<OrderVO> findOrdersBysearch(Map<String, String[]> map){
		return dao.getOrdersBysearch(map);
	}
	
	
	
	public OrderVO findOneOrderByOrderNo(Integer orderNo) {
		return dao.findByOrderNo(orderNo);
	}
	
	public void updateOrder(OrderVO orderVO) {
//		System.out.println("在S層開始更新Order");
		dao.update(orderVO);
	}
	
	public OrderVO findMemNoByOrderNo(Integer orderNo) {
		return dao.findMemNoByOrderNo(orderNo);
	}
	
}
