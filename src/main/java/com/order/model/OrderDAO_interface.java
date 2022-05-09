package com.order.model;

import java.util.List;
import java.util.Map;

public interface OrderDAO_interface {

	//顧客
    public List<OrderVO> findByMemNo(Integer memNo);
    
    
    //管理員    
    public void newOrderWithC(OrderVO orderVO);
    public void newOrderWithouC(OrderVO orderVO);
    //修改訂單   
    public void update(OrderVO orderVO);
    //查詢某個的訂單
    public OrderVO findByOrderNo(Integer orderNo); 
    
    //查詢某個的訂單
    public OrderVO findMemNoByOrderNo(Integer orderNo); 
    
    //查詢某個會員最新的訂單
    public OrderVO findNewetOrderByMem(Integer memNo);     
    //查詢作廢的訂單
    public List<OrderVO> findByTrashOrderNo();
    //查詢已完成的訂單
    public List<OrderVO> findByFinishedOrderNo();
    //查詢退貨的訂單
    public List<OrderVO> findByReturnedOrderNo();
    //查詢未出貨的訂單
    public List<OrderVO> findByUnPulledOrderNo();
    //查詢已出貨的訂單
    public List<OrderVO> findByPulledOrderNo();
         
    public List<OrderVO> getAll();


	public List<OrderVO> getOrdersBysearch(Map<String, String[]> map);
}
