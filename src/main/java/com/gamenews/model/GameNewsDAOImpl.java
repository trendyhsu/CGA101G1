package com.gamenews.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class GameNewsDAOImpl implements GameNewsDAO{

	// JNDI
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	// 使用JDBC
//	String url = "jdbc:mysql://35.194.146.30:3306/cga101g1?serverTimezone=Asia/Taipei";
//	String user = "tibame";
//	String pswd = "tibame";
	
	@Override
	public List<GameNewsVO> getAll() {
		List<GameNewsVO> gameNewsAll = new ArrayList<>();
		String sql = "SELECT GameNewsNo, GamePlatformNo, ManagerNo, GameNewsTitle, GameNewsContent, GameNewsPic "+
					 "FROM gamenews";
		try(Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Integer gameNewsNo = rs.getInt("GameNewsNo");
				Integer gamePlatformNo = rs.getInt("GamePlatformNo");
				Integer managerNo = rs.getInt("ManagerNo");
				String gameNewsTitle = rs.getString("GameNewsTitle");
				String gameNewsContent = rs.getString("GameNewsContent");
				byte[] gameNewsPic = rs.getBytes("GameNewsPic");
				GameNewsVO gameNews = new GameNewsVO(gameNewsNo,gamePlatformNo, managerNo, gameNewsTitle, gameNewsContent, gameNewsPic);
				gameNewsAll.add(gameNews);
			}
		}catch(SQLException e) {
			e.printStackTrace();
	
		}
		return gameNewsAll;
	}

	@Override
	public GameNewsVO findByPrimaryKey(Integer gameNewsNo) {
		GameNewsVO gameNewsVO = null;
		String sql = "SELECT GameNewsNo, GamePlatformNo, ManagerNo, GameNewsTitle, GameNewsContent, GameNewsPic "+
				 "FROM gamenews WHERE GameNewsNo = ?";
		try(Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, gameNewsNo);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				gameNewsVO = new GameNewsVO();
				gameNewsVO.setGameNewsNo(rs.getInt("GameNewsNo"));
				gameNewsVO.setGamePlatformNo(rs.getInt("GamePlatformNo"));
				gameNewsVO.setManagerNo(rs.getInt("ManagerNo"));
				gameNewsVO.setGameNewsTitle(rs.getString("GameNewsTitle"));
				gameNewsVO.setGameNewsContent(rs.getString("GameNewsContent"));
				gameNewsVO.setGameNewsPic(rs.getBytes("GameNewsPic"));
				}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return gameNewsVO;
	}

	@Override
	public void insert(GameNewsVO gameNewsVO) {
		String sql ="INSERT INTO gamenews (`GamePlatformNo`,`ManagerNo`,`GameNewsTitle`,`GameNewsContent`,`GameNewsPic`) "
				+ "values(?, ?, ?, ?, ?);";
		try(Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1,gameNewsVO.getGamePlatformNo());
			ps.setInt(2, gameNewsVO.getManagerNo());
			ps.setString(3, gameNewsVO.getGameNewsTitle());
			ps.setString(4, gameNewsVO.getGameNewsContent());
			ps.setBytes(5, gameNewsVO.getGameNewsPic());
			ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(GameNewsVO gameNewsVO) {
		String sql = "UPDATE gamenews set GamePlatformNo = ?, ManagerNo = ?, GameNewsTitle = ?, GameNewsContent = ?, GameNewsPic = ? where GameNewsNo = ?;";
		
		try(Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1,gameNewsVO.getGamePlatformNo());
			ps.setInt(2, gameNewsVO.getManagerNo());
			ps.setString(3, gameNewsVO.getGameNewsTitle());
			ps.setString(4, gameNewsVO.getGameNewsContent());
			ps.setBytes(5, gameNewsVO.getGameNewsPic());
			ps.setInt(6, gameNewsVO.getGameNewsNo());
			ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer gameNewsNo) {
		String sql ="DELETE from gamenews where GameNewsNo = ?;";

		try(Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, gameNewsNo);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<GameNewsVO> getAll(Map<String, String[]> map) {
		List<GameNewsVO> list = new ArrayList<GameNewsVO>();
		String sql = "SELECT GameNewsNo, GamePlatformNo, ManagerNo, GameNewsTitle, GameNewsContent, GameNewsPic "+
				 "FROM gamenews" + AllForOneQuery.anyConditions(map);
		try(Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)){
			System.out.println("final SQL= "+ sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				GameNewsVO gameNewsVO = new GameNewsVO();
				gameNewsVO.setGameNewsNo(rs.getInt(1));
				gameNewsVO.setGamePlatformNo(rs.getInt(2));
				gameNewsVO.setManagerNo(rs.getInt(3));
				gameNewsVO.setGameNewsTitle(rs.getString(4));
				gameNewsVO.setGameNewsContent(rs.getString(5));
				gameNewsVO.setGameNewsPic(rs.getBytes(6));
				list.add(gameNewsVO);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public void updateWithoutPic(GameNewsVO gameNewsVO) {
		String sql = "UPDATE gamenews set GamePlatformNo = ?, ManagerNo = ?, GameNewsTitle = ?, GameNewsContent = ? where GameNewsNo = ?;";
		
		try(Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1,gameNewsVO.getGamePlatformNo());
			ps.setInt(2, gameNewsVO.getManagerNo());
			ps.setString(3, gameNewsVO.getGameNewsTitle());
			ps.setString(4, gameNewsVO.getGameNewsContent());
			ps.setInt(5, gameNewsVO.getGameNewsNo());
			ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<GameNewsVO> getTopTwelve(Integer gamePlatformNo) {
		String sql = "SELECT GameNewsNo, GamePlatformNo, ManagerNo, GameNewsTitle, GameNewsContent from gamenews where GamePlatformNo = ? limit 12 ;";
		List<GameNewsVO> list = new ArrayList<GameNewsVO>();
		try(Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, gamePlatformNo);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				GameNewsVO gameNewsVO = new GameNewsVO();
				gameNewsVO.setGameNewsNo(rs.getInt(1));
				gameNewsVO.setGamePlatformNo(rs.getInt(2));
				gameNewsVO.setManagerNo(rs.getInt(3));
				gameNewsVO.setGameNewsTitle(rs.getString(4));
				gameNewsVO.setGameNewsContent(rs.getString(5));

				list.add(gameNewsVO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
