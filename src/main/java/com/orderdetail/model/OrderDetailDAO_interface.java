package com.orderdetail.model;

import java.util.List;

public interface OrderDetailDAO_interface {

	//查某產品銷售狀況
    public List<OrderDetailVO> findByProductNo(Integer productNo);
    
	//查全產品銷售狀況
    public List<OrderDetailVO> findAllProduct();
    
    
    //查熱銷Top9產品
    public List<OrderDetailVO> findTop9AllProduct();
    
    //查熱銷Top9產品map版
    public List<Object> findTop9AllProductByMap();
    
    //查某張訂單的全部項目
    public List<OrderDetailVO> findAllDetailByOrderNo(Integer OrderNo);
    
    public OrderDetailVO showCaledCommentByProductNo(Integer productNo);
    
    //新增訂單項目
    public void newByOrder(OrderDetailVO OrderDetailVO);
    
    //修改訂單項目內容
    public void modByOrder(OrderDetailVO OrderDetailVO);
    
    //作廢訂單項目內容
    public OrderDetailVO clearByOrder(OrderDetailVO orderDetailVO);
    
    //發表評論
    public void newCommentCotent(OrderDetailVO orderDetailVO);
    
//	//查某會員的所有評論
//    public List<OrderDetailVO> findCommentbyMemNo(Integer memNo);
    
	//查某商品的所有評論
    public List<OrderDetailVO> findCommentbyProductNo(Integer productNo);
}
