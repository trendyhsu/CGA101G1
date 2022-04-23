package com.product.model;

import java.util.List;

public class ProductService {
	private ProductDAO_interface dao;
	
	public ProductService() {
		dao = new ProductDAO();
	};
	
	
	
	//更新產品不上架                    //           1             2                       3                 4                       5                   6                         7                       8              9
	public ProductVO updateProductNotSold(Integer productNo,Integer gameTypeNo,Integer gamePlatformNo,Integer gameCompanyNo,String productName,Integer productPrice,Integer productState,String itemProdDescription,String upcNum) {
		System.out.println("開始更新不上架");
		ProductVO productVO = new ProductVO();
		System.out.println(productNo);
		productVO.setProductNo(productNo);
		productVO.setGameTypeNo(gameTypeNo);
		productVO.setGamePlatformNo(gamePlatformNo);
		productVO.setGameCompanyNo(gameCompanyNo);
		productVO.setProductName(productName);
		productVO.setProductPrice(productPrice);
		productVO.setProductState(productState);
		productVO.setItemProdDescription(itemProdDescription);
		productVO.setUpcNum(upcNum);
		dao.updateNotSold(productVO);
		System.out.println("更新不上架over");
	
        return productVO;
	};
	
	
	//更新產品要上架                    //           1             2                       3                 4                       5                   6                         7                       8              9
	public ProductVO updateProductAndSold(Integer productNo,Integer gameTypeNo,Integer gamePlatformNo,Integer gameCompanyNo,String productName,Integer productPrice,Integer productState,String itemProdDescription,String upcNum) {
		ProductVO productVO = new ProductVO();
		productVO.setProductNo(productNo);
		productVO.setGameTypeNo(gameTypeNo);
		productVO.setGamePlatformNo(gamePlatformNo);
		productVO.setGameCompanyNo(gameCompanyNo);
		productVO.setProductName(productName);
		productVO.setProductPrice(productPrice);
		productVO.setProductState(productState);
		productVO.setItemProdDescription(itemProdDescription);
		productVO.setUpcNum(upcNum);
		dao.updateAndSold(productVO);
	
        return productVO;
	};
	
	
	//新增產品而且要上架                    //            1                2                       3                 4                       5                   6                         7                       8              9
	public ProductVO AddNewProductAndSold(Integer gameTypeNo,Integer gamePlatformNo,Integer gameCompanyNo,String productName,Integer productPrice,Integer productState,String itemProdDescription,String upcNum) {
		ProductVO productVO = new ProductVO();
		productVO.setGameTypeNo(gameTypeNo);
		productVO.setGamePlatformNo(gamePlatformNo);
		productVO.setGameCompanyNo(gameCompanyNo);
		productVO.setProductName(productName);
		productVO.setProductPrice(productPrice);
		productVO.setProductState(productState);
		productVO.setItemProdDescription(itemProdDescription);
		productVO.setUpcNum(upcNum);
		dao.insertAndSold(productVO);
	
        return productVO;
	};
	
	
	//新增產品不要上架                        //            1                2                       3                 4                       5                   6                         7                       8              9
	public ProductVO AddNewProductNotSold(Integer gameTypeNo,Integer gamePlatformNo,Integer gameCompanyNo,String productName,Integer productPrice,Integer productState,String itemProdDescription,String upcNum) {
		ProductVO productVO = new ProductVO();
		productVO.setGameTypeNo(gameTypeNo);
		productVO.setGamePlatformNo(gamePlatformNo);
		productVO.setGameCompanyNo(gameCompanyNo);
		productVO.setProductName(productName);
		productVO.setProductPrice(productPrice);
		productVO.setProductState(productState);
		productVO.setItemProdDescription(itemProdDescription);
		productVO.setUpcNum(upcNum);
		dao.insert(productVO);
	
        return productVO;
	};
	
	
	//查詢全部產品
	public List<ProductVO> GetAllProducts(){
		return dao.getAll();
		
	} 
	
	
	//查詢正在販售的產品
	public List<ProductVO> GetAllSelledProducts(){
		return dao.getAllInSell();
		
	} 
	
	public ProductVO GetOne(Integer productNo) {
		return dao.findByPrimaryKey(productNo);
	}

}
