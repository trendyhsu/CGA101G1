package com.forum.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//import java.io.FileInputStream;
//import java.io.IOException;

public class ForumJNDIDAO implements ForumDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO forum (ForumName,ForumType,MemNo) VALUES (?, ?, ?)";
	private static final String INSERT_NOMEM_STMT = "INSERT INTO forum (ForumName,ForumType) VALUES (?, ?)";
	private static final String INSERT_FORUMIMG = "UPDATE forum SET ForumImg=? WHERE ForumNo = ?";
	private static final String UPDATE = "UPDATE forum SET ForumName=?, ForumType=?, MemNo=? WHERE ForumNo = ?";
	private static final String UPDATENOMEM = "UPDATE forum SET ForumName=?, ForumType=?, MemNo=NULL WHERE ForumNo = ?";
	private static final String GET_ONE_STMT = "SELECT ForumNo,ForumName,ForumType,MemNo,ForumImg FROM forum WHERE ForumNo = ?";
	private static final String GET_ONE_ONLY_NAME_STMT = "SELECT ForumNo,ForumName,ForumType,MemNo,ForumImg FROM forum WHERE ForumName = ?";
	private static final String GET_ALL_STMT = "SELECT ForumNo,ForumName,ForumType,MemNo,ForumImg FROM forum ORDER BY ForumNo";
	private static final String GET_FIND_FORUMNAME = "SELECT ForumNo,ForumName,ForumType,MemNo,ForumImg FROM forum WHERE ForumName LIKE ?";

	@Override
	public void insert(ForumVO forumVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, forumVO.getForumName());
			pstmt.setInt(2, forumVO.getForumType());
			pstmt.setInt(3, forumVO.getMemNo());
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void insertNoMem(ForumVO forumVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_NOMEM_STMT);

			pstmt.setString(1, forumVO.getForumName());
			pstmt.setInt(2, forumVO.getForumType());
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void insertForumImg(ForumVO forumVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_FORUMIMG);

			pstmt.setBytes(1, forumVO.getForumImg());
			pstmt.setInt(2, forumVO.getForumNo());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(ForumVO forumVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, forumVO.getForumName());
			pstmt.setInt(2, forumVO.getForumType());
			pstmt.setInt(3, forumVO.getMemNo());
			pstmt.setInt(4, forumVO.getForumNo());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void updateNoMem(ForumVO forumVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATENOMEM);

			pstmt.setString(1, forumVO.getForumName());
			pstmt.setInt(2, forumVO.getForumType());
			pstmt.setInt(3, forumVO.getForumNo());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public ForumVO findByPrimaryKey(Integer forumNo) {

		ForumVO forumVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, forumNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				forumVO = new ForumVO();
				forumVO.setForumNo(rs.getInt("forumNo"));
				forumVO.setForumName(rs.getString("forumName"));
				forumVO.setForumType(rs.getInt("forumType"));
				forumVO.setMemNo(rs.getInt("memNo"));
				forumVO.setForumImg(rs.getBytes("forumImg"));

			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return forumVO;
	}

	@Override
	public ForumVO findByOnlyName(String forumCharacter) {
		ForumVO forumVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ONLY_NAME_STMT);

			pstmt.setString(1, forumCharacter);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				forumVO = new ForumVO();
				forumVO.setForumNo(rs.getInt("forumNo"));
				forumVO.setForumName(rs.getString("forumName"));
				forumVO.setForumType(rs.getInt("forumType"));
				forumVO.setMemNo(rs.getInt("memNo"));
				forumVO.setForumImg(rs.getBytes("forumImg"));
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return forumVO;
	}

	@Override
	public List<ForumVO> getAll() {
		List<ForumVO> list = new ArrayList<ForumVO>();
		ForumVO forumVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				forumVO = new ForumVO();
				forumVO.setForumNo(rs.getInt("forumNo"));
				forumVO.setForumName(rs.getString("forumName"));
				forumVO.setForumType(rs.getInt("forumType"));
				forumVO.setMemNo(rs.getInt("memNo"));
				forumVO.setForumImg(rs.getBytes("forumImg"));
				list.add(forumVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public List<ForumVO> findByForumName(String forumCharacter) {

		List<ForumVO> list = new ArrayList<ForumVO>();

		ForumVO forumVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_FIND_FORUMNAME);

			pstmt.setString(1, "%" + forumCharacter + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {

				forumVO = new ForumVO();
				forumVO.setForumNo(rs.getInt("forumNo"));
				forumVO.setForumName(rs.getString("forumName"));
				forumVO.setForumType(rs.getInt("forumType"));
				forumVO.setMemNo(rs.getInt("memNo"));
				forumVO.setForumImg(rs.getBytes("forumImg"));

				list.add(forumVO);
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
//		ForumJDBCDAO dao = new ForumJDBCDAO();
//		
//OK!	// 新增
//		ForumVO newForum = new ForumVO();
//		newForum.setForumName("F1 2K");
//		newForum.setForumType(1);
//		newForum.setMemNo(11003);
//		try {
//		FileInputStream fis = new FileInputStream("C:/Users/Tibame_T14/Desktop/F1.jpg");
//		byte[] buffer = new byte[fis.available()];
//		fis.read(buffer);
//		fis.close();
//		newForum.setForumImg(buffer);
//		dao.insert(newForum);
//		} catch (IOException ie) {
//			System.out.println(ie);
//		}

//OK!	// 修改
//		ForumVO updateForum = new ForumVO();
//		updateForum.setForumName("");
//		updateForum.setForumType(1);
//		updateForum.setMemNo(11005);
//		try {
//			FileInputStream fis = new FileInputStream("C:/Users/Tibame_T14/Desktop/Demon_Slayer.png");
//			byte[] buffer = new byte[fis.available()];
//			fis.read(buffer);
//			fis.close();
//			updateForum.setForumImg(buffer);
//			updateForum.setForumNo(46003);
//
//			dao.update(updateForum);
//			
//			} catch (IOException ie) {
//				System.out.println(ie);
//			}

//OK!	//查詢 One
//		ForumVO oneForum =dao.findByPrimaryKey(46002);
//		
//		System.out.print(oneForum.getForumNo() + ",");
//		System.out.print(oneForum.getForumName() + ",");
//		System.out.print(oneForum.getForumType() + ",");
//		System.out.print(oneForum.getMemNo() + ",");
//		System.out.print(oneForum.getForumImg() + ",");
//
//		System.out.println("----------");
//		

//OK!	//查詢 ALL
//		List<ForumVO> list = dao.getAll();
//		for (ForumVO aForum : list) {
//			System.out.print(aForum.getForumNo() + ",");
//			System.out.print(aForum.getForumName() + ",");
//			System.out.print(aForum.getForumType() + ",");
//			System.out.print(aForum.getMemNo() + ",");
//			System.out.print(aForum.getForumImg() + ",");
//
//			System.out.println("----------");
//		}
//OK!		//查詢 ALL findByForumName
//		List<ForumVO> list = dao.findByForumName("F1");
//		for (ForumVO aForum : list) {
//		System.out.print(aForum.getForumNo() + ",");
//		System.out.print(aForum.getForumName() + ",");
//		System.out.print(aForum.getForumType() + ",");
//		System.out.print(aForum.getMemNo() + ",");
//		System.out.print(aForum.getForumImg() + ",");
//
//		System.out.println("----------");
//}
//	}
}