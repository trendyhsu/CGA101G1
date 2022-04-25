package com.gametype.model;

import java.util.List;

public interface GameTypeDAO_interface {
    public void insert(GameTypeVO gameTypeVO);
    public void update(GameTypeVO gameTypeVO);
    public void delete(Integer gameTypeNo);
    public GameTypeVO findByPrimaryKey(Integer gameTypeNo);
    public List<GameTypeVO> getAll();
    //複合查詢
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
