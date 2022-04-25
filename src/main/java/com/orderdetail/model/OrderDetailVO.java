package com.orderdetail.model;

import java.sql.Date;

public class OrderDetailVO implements java.io.Serializable{
	private Integer orderNo;
	private Integer productNo;
	private Integer productSales;
	private Integer productTotalPrice;
	private String commentCotent;
	private Date commentTime;
	private Double CommentStar;
	
	
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
	public Double getCommentStar() {
		return CommentStar;
	}
	public void setCommentStar(Double commentStar) {
		CommentStar = commentStar;
	}
}
