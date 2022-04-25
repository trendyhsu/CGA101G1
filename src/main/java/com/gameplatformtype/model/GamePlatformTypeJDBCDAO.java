package com.gameplatformtype.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.utils.DButil;

//
// 


public class GamePlatformTypeJDBCDAO extends DButil implements GamePlatformTypeDAO_interface{

//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/popgame_text?serverTimezone=Asia/Taipei";
//	String userid = "root";


	private static final String INSERT_STMT = 
		"INSERT INTO gameplatformtype (GamePlatformName) VALUES (?)";
	private static final String GET_ONE_STMT = 
		"SELECT GamePlatformNo,GamePlatformName FROM gameplatformtype where GamePlatformNo = ?";
	private static final String GET_ALL_STMT = 
		"SELECT GamePlatformNo,GamePlatformName FROM gameplatformtype order by GamePlatformNo";
	private static final String UPDATE = 
		"UPDATE gameplatformtype set GamePlatformName=? where GamePlatformNo = ?";
	
	@Override
	public void insert(GamePlatformTypeVO gamePlatformTypeVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, gamePlatformTypeVO.getGamePlatformName());

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
	public void update(GamePlatformTypeVO gamePlatformTypeVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, gamePlatformTypeVO.getGamePlatformName());
			pstmt.setInt(2, gamePlatformTypeVO.getGamePlatformNo());
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
	public void delete(Integer gameplatformno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GamePlatformTypeVO findByPrimaryKey(Integer gamePlatformNo) {
		// TODO Auto-generated method stub
		GamePlatformTypeVO gamePlatformTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, gamePlatformNo);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo �]�٬� Domain objects
				gamePlatformTypeVO = new GamePlatformTypeVO();
				gamePlatformTypeVO.setGamePlatformNo(rs.getInt("gamePlatformNo"));
				gamePlatformTypeVO.setGamePlatformName(rs.getString("gamePlatformName"));
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
		
		return gamePlatformTypeVO;
	}

	@Override
	public List<GamePlatformTypeVO> getAll() {
		// TODO Auto-generated method stub
		List<GamePlatformTypeVO> list = new ArrayList<GamePlatformTypeVO>();
		GamePlatformTypeVO gamePlatformTypeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 
				gamePlatformTypeVO = new GamePlatformTypeVO();
				gamePlatformTypeVO.setGamePlatformNo(rs.getInt("gamePlatformNo"));
				gamePlatformTypeVO.setGamePlatformName(rs.getString("gamePlatformName"));
				list.add(gamePlatformTypeVO);
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
//
//		GamePlatformTypeJDBCDAO dao = new GamePlatformTypeJDBCDAO();
//
//		// 
//		GamePlatformTypeVO platFormVO1 = new GamePlatformTypeVO();
//		platFormVO1.setGamePlatformName("PS5");
////		chatVO1.setChattime("current_timestamp");
//		dao.insert(platFormVO1);
//
//		//
//		GamePlatformTypeVO platFormVO3 = new GamePlatformTypeVO();
//		platFormVO3.setGamePlatformNo(64003);
//		platFormVO3.setGamePlatformName("PS4");
//		dao.update(platFormVO3);
//		
//		
//		// 
//		GamePlatformTypeVO platFormVO2 = dao.findByPrimaryKey(64001);
//		System.out.print(platFormVO2.getGamePlatformNo() + ",");
//		System.out.print(platFormVO2.getGamePlatformName() + ",");
//		System.out.println("---------------------");
//		
//		//
//		List<GamePlatformTypeVO> list = dao.getAll();
//		for (GamePlatformTypeVO platForm : list) {
//			System.out.print(platForm.getGamePlatformNo() + ",");
//			System.out.print(platForm.getGamePlatformName() + ",");
//			System.out.println("---------------------");
//		
//		}
//	}
}