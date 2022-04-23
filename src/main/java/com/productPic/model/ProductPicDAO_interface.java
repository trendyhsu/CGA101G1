package com.productPic.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface ProductPicDAO_interface {
    public void insert(ProductPicVO ProductPicVO);
    public void update(ProductPicVO ProductPicVO);
//  取單圖
    public List<ProductPicVO> findByPrimaryKeyInBase64(Integer productPicNo);
    public ProductPicVO findByPrimaryKeyInByte(Integer productPicNo);
    public List<ProductPicVO> getAll(HttpServletRequest request);
    public List<ProductPicVO> getAllCovers(HttpServletRequest request);
}
