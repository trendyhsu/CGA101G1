package com.forumpostpic.model;

import java.util.*;
//import java.io.FileInputStream;
//import java.io.IOException;
import java.sql.*;

public class ForumPostPicJDBCDAO implements ForumPostPicDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://35.194.146.30:3306/cga101g1?serverTimezone=Asia/Taipei";
	String userid = "tibame";
	String passwd = "tibame";

	private static final String INSERT_STMT = 
		"INSERT INTO forumpostpic (ForumPostNo,ForumPic) VALUES (?, ?)";
	private static final String UPDATE = 
		"UPDATE forumpostpic SET ForumPic = ? WHERE ForumPostPicNo = ?";
	private static final String DELETE = 
		"DELETE FROM forumpostpic WHERE ForumPostPicNo = ?";
	private static final String GET_ONE_STMT = 
		"SELECT ForumPostPicNo,ForumPostNo,ForumPic FROM forumpostpic WHERE ForumPostPicNo = ?";
	private static final String GET_ALL_STMT = 
		"SELECT ForumPostPicNo,ForumPostNo,ForumPic FROM forumpostpic ORDER BY ForumPostPicNo";
	private static final String GET_ONE_FORUM_TOTAL_POST_PIC_STMT = 
		"SELECT ForumPostPicNo,ForumPostNo,ForumPic FROM forumpostpic WHERE ForumPostNo=? ORDER BY ForumPostPicNo";
	
	
	

	@Override
	public void insert(ForumPostPicVO forumPostPicVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, forumPostPicVO.getForumPostNo());
			pstmt.setBytes(2, forumPostPicVO.getForumPic());
	
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
	public void update(ForumPostPicVO forumPostPicVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setBytes(1, forumPostPicVO.getForumPic());
			pstmt.setInt(2, forumPostPicVO.getForumPostPicNo());

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
	public void delete(Integer forumPostPicNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, forumPostPicNo);

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
	public ForumPostPicVO findByPrimaryKey(Integer forumPostPicNo) {

		ForumPostPicVO forumPostPicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, forumPostPicNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				forumPostPicVO = new ForumPostPicVO();
				forumPostPicVO.setForumPostPicNo(rs.getInt("forumPostPicNo"));
				forumPostPicVO.setForumPostNo(rs.getInt("forumPostNo"));
				forumPostPicVO.setForumPic(rs.getBytes("forumPic"));
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
		return forumPostPicVO;
	}

	@Override
	public List<ForumPostPicVO> getAll() {
		List<ForumPostPicVO> list = new ArrayList<ForumPostPicVO>();
		ForumPostPicVO forumPostPicVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				forumPostPicVO = new ForumPostPicVO();
				forumPostPicVO.setForumPostPicNo(rs.getInt("forumPostPicNo"));
				forumPostPicVO.setForumPostNo(rs.getInt("forumPostNo"));
				forumPostPicVO.setForumPic(rs.getBytes("forumPic"));

				list.add(forumPostPicVO); // Store the row in the list
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
	
	@Override
	public List<ForumPostPicVO> findOneForumTotalPostPic(Integer forumPostNo) {
		List<ForumPostPicVO> list = new ArrayList<ForumPostPicVO>();
		ForumPostPicVO forumPostPicVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_FORUM_TOTAL_POST_PIC_STMT);
			
			pstmt.setInt(1, forumPostNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				forumPostPicVO = new ForumPostPicVO();
				forumPostPicVO.setForumPostPicNo(rs.getInt("forumPostPicNo"));
				forumPostPicVO.setForumPostNo(rs.getInt("forumPostNo"));
				forumPostPicVO.setForumPic(rs.getBytes("forumPic"));

				list.add(forumPostPicVO); // Store the row in the list
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
//		ForumPostPicJDBCDAO dao = new ForumPostPicJDBCDAO();

//OK!	// 新增 
		
//		ForumPostPicVO newPostPic = new ForumPostPicVO();
		
//		newPostPic.setForumPostNo(41009);
//
//		try {
//		FileInputStream fis = new FileInputStream("C:\\Users\\Tibame_T14\\Desktop\\mS8JN7ml.jpg");
//		byte[] buffer = new byte[fis.available()];
//		fis.read(buffer);
//		fis.close();
//		newPostPic.setForumPic(buffer);
//		dao.insert(newPostPic);
//		} catch (IOException ie) {
//			System.out.println(ie);
//		}

//OK!	// 修改 
//		ForumPostPicVO updatePostPic = new ForumPostPicVO();
//	
//		try {
//			FileInputStream fis = new FileInputStream("C:/Users/Tibame_T14/Desktop/mS8JN7ml.jpg");
//			byte[] buffer = new byte[fis.available()];
//			fis.read(buffer);
//			fis.close();
//			updatePostPic.setForumPic(buffer);
//			updatePostPic.setForumPostPicNo(48024);
//
//			dao.update(updatePostPic);
//			
//			} catch (IOException ie) {
//				System.out.println(ie);
//			}

//OK!	// 刪除 
//		dao.delete(48024);
//
//OK!	// 查詢 One 
//		ForumPostPicVO onePostPic = dao.findByPrimaryKey(48024);
//		System.out.print(onePostPic.getForumPostPicNo() + ",");
//		System.out.print(onePostPic.getForumPostNo() + ",");
//		System.out.print(onePostPic.getForumPic());
//
//		System.out.println("---------------------");
//
//OK!	// 查詢 All
//		List<ForumPostPicVO> list = dao.getAll();
//		for (ForumPostPicVO aPostPic : list) {
//			System.out.print(aPostPic.getForumPostPicNo() + ",");
//			System.out.print(aPostPic.getForumPostNo() + ",");
//			System.out.print(aPostPic.getForumPic() + ",");
//			System.out.println();
//		}
		
//OK!	// 查詢 findOneForumTotalPostPic
//		List<ForumPostPicVO> list = dao.findOneForumTotalPostPic(41006);
//		for (ForumPostPicVO aPostPic : list) {
//			System.out.print(aPostPic.getForumPostPicNo() + ",");
//			System.out.print(aPostPic.getForumPostNo() + ",");
//			System.out.print(aPostPic.getForumPic());
//			System.out.println();
//		}
//	}
}