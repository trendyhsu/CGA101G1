package com.managerauthrizationfunction.model;

import java.util.List;

public interface ManagerAuthrizationFunctionDAO_interface {
	public void insert(ManagerAuthrizationFunctionVO managerAuthrizationFunctionVO);
    public void update(ManagerAuthrizationFunctionVO managerAuthrizationFunctionVO);
    public void delete(Integer managerAuthrizationFunctionNo);
    public ManagerAuthrizationFunctionVO findByPrimaryKey(Integer managerAuthrizationFunctionNo);
    public List<ManagerAuthrizationFunctionVO> getAll();
    //
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
