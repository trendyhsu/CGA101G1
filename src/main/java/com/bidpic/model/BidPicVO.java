package com.bidpic.model;

import java.io.Serializable;

public class BidPicVO implements Serializable {
	// 建立承接 bidpic 表格的 Value Object
	public BidPicVO() {

	}
	private Integer bidProdPicNo;
	private Integer bidProductNo;
	private byte[] bidProdPicContent;
	
	public Integer getBidProdPicNo() {
		return bidProdPicNo;
	}
	public void setBidProdPicNo(Integer bidProdPicNo) {
		this.bidProdPicNo = bidProdPicNo;
	}
	public Integer getBidProductNo() {
		return bidProductNo;
	}
	public void setBidProductNo(Integer bidProductNo) {
		this.bidProductNo = bidProductNo;
	}
	public byte[] getBidProdPicContent() {
		return bidProdPicContent;
	}
	public void setBidProdPicContent(byte[] bidProdPicContent) {
		this.bidProdPicContent = bidProdPicContent;
	}

}
