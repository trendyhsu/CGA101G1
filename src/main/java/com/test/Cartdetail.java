package com.test;

import java.util.Objects;

public class Cartdetail {
	private Integer orderNo;
	private String productName;
	private String productNo;
	private Integer productSales;
	private Integer productTotalPrice;


	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
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


	@Override
	public int hashCode() {
		return Objects.hash(orderNo, productNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cartdetail other = (Cartdetail) obj;
		return Objects.equals(orderNo, other.orderNo) && Objects.equals(productNo, other.productNo);
	}
}
