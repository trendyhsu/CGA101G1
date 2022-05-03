package com.managerauth.model;

import java.util.List;
import java.util.Set;

import com.managerauthrizationfunction.model.ManagerAuthrizationFunctionVO;

public interface ManagerAuthDAO_interface {
	public void insert(ManagerAuthVO managerAuthVO);
    public void update(ManagerAuthVO managerAuthVO);
    public void delete(Integer managerNo,Integer managerAuthrizationFunctionNo);
    public ManagerAuthVO findByPrimaryKey(Integer managerNo);
    public List<ManagerAuthVO> getAll();
//  public List<EmpVO> getAll(Map<String, String[]> map); 
    public Set<ManagerAuthrizationFunctionVO> getFunction(Integer Managerno);
}
