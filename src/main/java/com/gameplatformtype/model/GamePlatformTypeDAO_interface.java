package com.gameplatformtype.model;

import java.util.List;

public interface GamePlatformTypeDAO_interface {
    public void insert(GamePlatformTypeVO gamePlatformTypeVO);
    public void update(GamePlatformTypeVO gamePlatformTypeVO);
    public void delete(Integer gamePlatformNo);
    public GamePlatformTypeVO findByPrimaryKey(Integer gamePlatformNo);
    public List<GamePlatformTypeVO> getAll();
    //
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
