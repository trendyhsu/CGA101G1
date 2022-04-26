package com.gamecompany.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.utils.DButil;

public class GameCompanyJDBCDAO extends DButil implements GameCompanyDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO gamecompany (GameCompanyName) VALUES (?)";
	private static final String GET_ONE_STMT = "SELECT GameCompanyNo, GameCompanyName FROM gamecompany where GameCompanyNo = ?";
	private static final String GET_ALL_STMT = "SELECT GameCompanyNo, GameCompanyName FROM gamecompany order by GameCompanyNo";
	private static final String UPDATE = "UPDATE gamecompany set GameCompanyName=? where GameCompanyNo = ?";

	@Override
	public void insert(GameCompanyVO gameCompanyVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, gameCompanyVO.getGameCompanyName());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(GameCompanyVO gameCompanyVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, gameCompanyVO.getGameCompanyName());
			pstmt.setInt(2, gameCompanyVO.getGameCompanyNo());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer gameCompanyNo) {
		// TODO Auto-generated method stub

	}

	@Override
	public GameCompanyVO findByPrimaryKey(Integer gameCompanyNo) {

		GameCompanyVO gameCompanyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, gameCompanyNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				gameCompanyVO = new GameCompanyVO();
				gameCompanyVO.setGameCompanyNo(rs.getInt("GameCompanyNo"));
				gameCompanyVO.setGameCompanyName(rs.getString("GameCompanyName"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		return gameCompanyVO;
	}

	@Override
	public List<GameCompanyVO> getAll() {

		List<GameCompanyVO> list = new ArrayList<GameCompanyVO>();
		GameCompanyVO gameCompanyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				gameCompanyVO = new GameCompanyVO();
				gameCompanyVO.setGameCompanyNo(rs.getInt("GameCompanyNo"));
				gameCompanyVO.setGameCompanyName(rs.getString("GameCompanyName"));
				list.add(gameCompanyVO);
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

}
