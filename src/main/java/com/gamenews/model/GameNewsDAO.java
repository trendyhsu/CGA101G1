package com.gamenews.model;

import java.util.List;
import java.util.Map;




public interface GameNewsDAO{
	
	 public void insert(GameNewsVO gameNewsVO);
     public void update(GameNewsVO gameNewsVO);
     public void updateWithoutPic(GameNewsVO gameNewsVO);
     public void delete(Integer gameNewsNo);
     public GameNewsVO findByPrimaryKey(Integer gameNewsNo);
     public List<GameNewsVO> getAll();
     //萬用查詢
     public List<GameNewsVO> getAll(Map<String, String[]> map);
     //取最新六則
     public List<GameNewsVO> getTopTwelve(Integer gamePlatformNo);
}
