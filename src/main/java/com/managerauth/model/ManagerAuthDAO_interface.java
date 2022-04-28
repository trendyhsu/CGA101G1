package com.managerauth.model;

import java.util.List;

public interface ManagerAuthDAO_interface {
	public void insert(ManagerAuthVO managerAuthVO);
    public void update(ManagerAuthVO managerAuthVO);
    public void delete(Integer managerNo,Integer managerAuthrizationFunctionNo);
    public ManagerAuthVO findByPrimaryKey(Integer managerNo);
    public List<ManagerAuthVO> getAll();
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
