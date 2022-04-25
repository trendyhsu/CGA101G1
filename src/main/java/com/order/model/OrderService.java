package com.order.model;

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
}
