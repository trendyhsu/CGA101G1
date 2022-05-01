package com.productPic.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ProductPicService {
	private ProductPicDAO_interface dao;
	
	public ProductPicService() {
		dao = new ProductPicJDBCDAO();
	}
	
	//新增圖片
	public ProductPicVO addProductPic(byte[] productPicContent) {
		ProductPicVO productPicVO = new ProductPicVO();
		productPicVO.setProductPicContentByte(productPicContent);
		dao.insert(productPicVO);	
		return productPicVO;
		
	}
	
	//更換圖片
	public ProductPicVO changeProductPic(Integer productPicNO,byte[] productPicContent) {
		ProductPicVO productPicVO = new ProductPicVO();
		productPicVO.setProductPicNo(productPicNO);
		productPicVO.setProductPicContentByte(productPicContent);
		dao.update(productPicVO);	
		return productPicVO;
		
	}
	
	// 取得所有圖片
	public List<ProductPicVO> getAll(HttpServletRequest request) {
		return dao.getAll(request);
	}
	
	
	// 取得所有封面
	public List<ProductPicVO> getAllCovers(HttpServletRequest request) {
		
		return dao.getAllCovers(request);
	}
	
	
	//取得一張圖片用byte輸出
	public ProductPicVO onePicInByte(Integer productPicNo) {
		return dao.findByPrimaryKeyInByte(productPicNo);
	}
	
	
	//取得一張封面圖片
	public ProductPicVO onePicCoverInByte(Integer productPicNo) {
		return dao.GetOneCoverByProductNo(productPicNo);
	}
	
	
	
	//取得一個商品全部的照片byBase64
	public List<ProductPicVO> productAllPics(Integer productNo){
		return dao.findByPrimaryKeyInBase64(productNo);
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
