package com.gametype.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.utils.DButil;


// JDBC  OK
//遊戲種類 新增、修改、查詢

public class GameTypeJDBCDAO extends DButil implements GameTypeDAO_interface{
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/popgame_text?serverTimezone=Asia/Taipei";
//	String userid = "root";


	private static final String INSERT_STMT = 
		"INSERT INTO gametype (GameTypeName) VALUES (?)";
	private static final String GET_ONE_STMT = 
		"SELECT GameTypeNo,GameTypeName FROM gametype where GameTypeNo = ?";
	private static final String GET_ALL_STMT = 
		"SELECT GameTypeNo,GameTypeName FROM gametype order by GameTypeNo";
	private static final String UPDATE = 
		"UPDATE gametype set GameTypeName=? where GameTypeNo = ?";
		
	@Override
	public void insert(GameTypeVO gameTypeVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, gameTypeVO.getGameTypeName());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	
	@Override
	public void update(GameTypeVO gameTypeVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, gameTypeVO.getGameTypeName());
			pstmt.setInt(2, gameTypeVO.getGameTypeNo());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	
	@Override
	public GameTypeVO findByPrimaryKey(Integer gameTypeNo) {
		// TODO Auto-generated method stub
		
		GameTypeVO gameTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, gameTypeNo);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo �]�٬� Domain objects
				gameTypeVO = new GameTypeVO();
				gameTypeVO.setGameTypeNo(rs.getInt("gameTypeNo"));
				gameTypeVO.setGameTypeName(rs.getString("gameTypeName"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		
		return gameTypeVO;
	}
	@Override
	public List<GameTypeVO> getAll() {
		// TODO Auto-generated method stub
		
		List<GameTypeVO> list = new ArrayList<GameTypeVO>();
		GameTypeVO gameTypeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				gameTypeVO = new GameTypeVO();
				gameTypeVO.setGameTypeNo(rs.getInt("gameTypeNo"));
				gameTypeVO.setGameTypeName(rs.getString("gameTypeName"));
				list.add(gameTypeVO);
			}
			// Handle any driver errors
					} catch (ClassNotFoundException e) {
						throw new RuntimeException("Couldn't load database driver. "
								+ e.getMessage());
						// Handle any SQL errors
					} catch (SQLException se) {
						throw new RuntimeException("A database error occured. "
								+ se.getMessage());
						// Clean up JDBC resources
					} finally {
						}
						if (con != null) {
							try {
								con.close();
							} catch (Exception e) {
								e.printStackTrace(System.err);
							}
						}
		return list;
	}
	
//	public static void main(String[] args) {
////
//		GameTypeJDBCDAO dao = new GameTypeJDBCDAO();
////
//		// 新增
//		GameTypeVO gametypeVO1 = new GameTypeVO();
//		gametypeVO1.setGameTypeName("����");
//		
//		dao.insert(gametypeVO1);
//
//		// 修改
//		GameTypeVO gametypeVO2 = new GameTypeVO();
//		gametypeVO2.setGameTypeNo(63002);
//		gametypeVO2.setGameTypeName("�@��");
//		
//		dao.update(gametypeVO2);
		
//		// 查詢
//		GameTypeVO gametypeVO3 = dao.findByPrimaryKey(63002);
//		System.out.print(gametypeVO3.getGameTypeNo() + ",");
//		System.out.println(gametypeVO3.getGameTypeName() + ",");
//		System.out.println("---------------------");
//		
//		// listall查詢
//		List<GameTypeVO> list = dao.getAll();
//		for (GameTypeVO aGameType : list) {
//			System.out.print(aGameType.getGameTypeNo() + ",");
//			System.out.println(aGameType.getGameTypeName() + ",");
//		}
//	}

	@Override
	public void delete(Integer gameTypeNo) {
		// TODO Auto-generated method stub
		
	}
}
