package com.orderdetail.model;

import java.sql.Date;

import com.order.model.OrderDAO;
import com.order.model.OrderDAO_interface;
import com.order.model.OrderVO;
import com.product.model.ProductDAO;
import com.product.model.ProductDAO_interface;
import com.product.model.ProductService;
import com.product.model.ProductVO;

public class OrderDetailVO implements java.io.Serializable{
	private Integer orderNo;
	private Integer productNo;
	private Integer productSales;
	private Integer productTotalPrice;
	private String commentCotent;
	private Date commentTime;
	private Integer CommentStar;
	
	
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getProductNo() {
		return productNo;
	}
	public void setProductNo(Integer productNo) {
		this.productNo = productNo;
	}
	public Integer getProductSales() {
		return productSales;
	}
	public void setProductSales(Integer productSales) {
		this.productSales = productSales;
	}
	public Integer getProductTotalPrice() {
		return productTotalPrice;
	}
	public void setProductTotalPrice(Integer productTotalPrice) {
		this.productTotalPrice = productTotalPrice;
	}
	public String getCommentCotent() {
		return commentCotent;
	}
	public void setCommentCotent(String commentCotent) {
		this.commentCotent = commentCotent;
	}
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	public Integer getCommentStar() {
		return CommentStar;
	}
	public void setCommentStar(Integer commentStar) {
		CommentStar = commentStar;
	}
	
	public com.product.model.ProductVO getProductVO(Integer productNo) {
		com.product.model.ProductService productService = new ProductService();
//		System.out.println(productService.GetOne(productNo).getProductName());
		return productService.GetOne(productNo);
	}
	
	public OrderVO findMemNoByOrderNo(Integer orderNo) {
		OrderDAO_interface dao = new OrderDAO();
		return dao.findMemNoByOrderNo(orderNo);
	}
}
