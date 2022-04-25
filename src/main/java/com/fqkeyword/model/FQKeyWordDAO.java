package com.fqkeyword.model;

import java.util.List;
import java.util.Map;



public interface FQKeyWordDAO {
	
	public void insert(FQKeyWordVO fqKeyWordVO);
    public void update(FQKeyWordVO fqKeyWordVO);
    public void delete(Integer fqKeyWordNo);
    public FQKeyWordVO findByPrimaryKey(Integer fqKeyWordNo);
    public List<FQKeyWordVO> getAll();
    //萬用查詢
    public List<FQKeyWordVO> getAll(Map<String, String[]> map);
}
