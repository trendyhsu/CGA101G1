package com.manager.model;

import java.util.List;

public interface ManagerDAO_interface {
	public void insert(ManagerVO managerVO);
    public void update(ManagerVO managerVO);
    public void delete(Integer managerNo);
    public ManagerVO findByPrimaryKey(Integer managerNo);
    public List<ManagerVO> getAll();
    //
//  public List<EmpVO> getAll(Map<String, String[]> map); 
    // 管理員狀態
 	public void updateManagerState(ManagerVO managerVO);
    
}
