package com.productPic.model;

public class ProductPicVO implements java.io.Serializable{
	private Integer productNo;
	private Integer productPicNo;
	private byte[] productPicContentByte ;
	private String imageUrl;
	private String productPicContentBase64;
	
	
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getProductNo() {
		return productNo;
	}
	public void setProductNo(Integer productNo) {
		this.productNo = productNo;
	}
	public Integer getProductPicNo() {
		return productPicNo;
	}
	public void setProductPicNo(Integer productPicNo) {
		this.productPicNo = productPicNo;
	}
	public byte[] getProductPicContentByte() {
		return productPicContentByte;
	}
	public void setProductPicContentByte(byte[] productPicContentByte) {
		this.productPicContentByte = productPicContentByte;
	}
	public String getProductPicContentBase64() {
		return productPicContentBase64;
	}
	public void setProductPicContentBase64(String productPicContentBase64) {
		this.productPicContentBase64 = productPicContentBase64;
	}


}
