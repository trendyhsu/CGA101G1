package com.product.model;

import java.util.List;

public class ProductService {
	private ProductDAO_interface dao;
	
	public ProductService() {
		dao = new ProductDAO();
	};
	
	
	
	//更新產品不上架                    //           1             2                       3                 4                       5                   6                         7                       8              9
	public ProductVO updateProductNotSold(Integer productNo,Integer gameTypeNo,Integer gamePlatformNo,Integer gameCompanyNo,String productName,Integer productPrice,Integer productState,String itemProdDescription,String upcNum) {
//		System.out.println("開始更新不上架");
		ProductVO productVO = new ProductVO();
//		System.out.println(productNo);
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
//		System.out.println("更新不上架over");
	
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
	
	//查詢正在販售的產品用map裝
	public List<Object> GetAllSelledProductsByMap(){
		return dao.getAllInSellByMap();
		
	} 
	
	
	//查詢正在販售的產品用map裝且須輸入分頁資訊
	public List<Object> GetAllSelledProductsByMap(Integer page){
		return dao.getPageInSellByMap(page);
		
	} 
	
	public ProductVO GetOne(Integer productNo) {
		return dao.findByPrimaryKey(productNo);
	}
	
	public ProductVO GetOneName(Integer productNo) {
		return dao.findNameByPrimaryKey(productNo);
	}
	
	//查最新新增的商品
	public List<ProductVO> GetNewestOne() {
		return dao.findByTop3MaxPrimaryKey();
	}
	
	//查全產品的名字
	public List<ProductVO> GetAllProductsName() {
		return dao.getAllInName();
	}
	
	//查正在上市產品的數量：顯示頁數用
	public Integer ShowSellCount() {
		return dao.showSellCount();
	}
	
	
	//查正在上市產品且遊戲種類是??的數量：顯示頁數用
	public Integer ShowSellCountAndGameType(Integer gameTypeNo) {
		return dao.showSellCountByGameType(gameTypeNo);
	}
	
	//查詢正在販售且遊戲種類是??的產品用map裝且須輸入分頁資訊
	public List<Object> GetAllSelledProductsByMapAndGameType(Integer page,Integer gameTypeNo){
		return dao.getPageInSellByMapAndGameType(page, gameTypeNo);
		
	} 
	
	
	//查詢正在販售且關鍵字是??的產品用map裝且須輸入分頁資訊
	public List<Object> GetAllSelledProductsBykeyword(Integer page,String keyword){
		return dao.getAllByWord(page,keyword);
		
	}
	
	//查正在上市產品且關鍵字是??的數量：顯示頁數用
	public Integer ShowSellCountByKeyword(String keyword) {
		return dao.showSellCountByKeyword(keyword);
	}
	
	
	//查正在上市產品且遊戲平台是??的數量：顯示頁數用
	public Integer ShowSellCountAndGamePlatform(Integer gamePlatformNo) {
		return dao.showSellCountByGamePlatformType(gamePlatformNo);
	}
	
	//查詢正在販售且遊戲種類是??的產品用map裝且須輸入分頁資訊
	public List<Object> GetAllSelledProductsByMapAndGamePlatform(Integer page,Integer gamePlatformNo){
		return dao.getPageInSellByMapAndGamePlatformNo(page, gamePlatformNo);
		
	} 
	
	
	//查正在上市產品且價格是??+??的數量：顯示頁數用
	public Integer showSellCountByMoney(Integer lowPrice, Integer highPrice) {
		return dao.showSellCountByMoney(lowPrice, highPrice);
	}
	
	//查正在上市產品且價格是??+??的產品用map裝且須輸入分頁資訊
	public List<Object> showInSellByMapAndMoney(Integer page,Integer lowPrice, Integer highPrice){
		return dao.getPageInSellByMapAndMoney(page, lowPrice, highPrice);
		
	}

}
