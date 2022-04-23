package com.forumpostcollection.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class ForumPostCollectionJNDIDAO implements ForumPostCollectionDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
		"INSERT INTO forumpostcollection (MemNo,ForumPostNo) VALUES (?, ?)";
	private static final String DELETE = 
		"DELETE FROM forumpostcollection WHERE MemNo=? and ForumPostNo = ?";
	private static final String GET_ALL_STMT = 
		"SELECT ForumPostNo FROM forumpostcollection WHERE MemNo=? ORDER BY ForumPostNo";

	@Override
	public void insert(ForumPostCollectionVO forumPostCollectionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, forumPostCollectionVO.getMemNo());
			pstmt.setInt(2, forumPostCollectionVO.getForumPostNo());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer memNo,Integer forumPostNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memNo);
			pstmt.setInt(2, forumPostNo);

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public List<ForumPostCollectionVO> getAll(Integer memNo) {
		List<ForumPostCollectionVO> list = new ArrayList<ForumPostCollectionVO>();
		ForumPostCollectionVO forumPostCollectionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			pstmt.setInt(1, memNo);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				forumPostCollectionVO = new ForumPostCollectionVO();
				forumPostCollectionVO.setForumPostNo(rs.getInt("forumPostNo"));
				
				list.add(forumPostCollectionVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

//	public static void main(String[] args) {
//
//		ForumPostCollectionJDBCDAO dao = new ForumPostCollectionJDBCDAO();

//OK!	//新增 
//		ForumPostCollectionVO newForumPostCollection = new ForumPostCollectionVO();
//		newForumPostCollection.setMemNo(11004);
//		newForumPostCollection.setForumPostNo(41002);
//		
//		dao.insert(newForumPostCollection);

	
//OK!	//刪除
		
//		dao.delete(11005,41004);
//
//OK!	//查詢
//		List<ForumPostCollectionVO> list = dao.getAll(11005);
//		
//		for (ForumPostCollectionVO lovePost : list) {
//			
//			System.out.println(lovePost.getForumPostNo());
//		}
//	}
}