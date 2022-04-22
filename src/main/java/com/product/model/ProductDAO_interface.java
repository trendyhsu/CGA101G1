package com.product.model;

import java.util.List;

public interface ProductDAO_interface {

	public void insert(ProductVO ProductVO);
	
	public void insertAndSold(ProductVO ProductVO);

	public void updateAndSold(ProductVO ProductVO);
	
	public void updateNotSold(ProductVO ProductVO);

	public ProductVO findByPrimaryKey(Integer ProductNo);

	public List<ProductVO> getAll();

	public List<ProductVO> getAllByWord(String ProductName);

}
