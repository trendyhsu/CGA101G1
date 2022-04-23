package com.forummsg.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class ForumMsgDAO implements ForumMsgDAO_interface {
	
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
			"INSERT INTO forummsg (MemNo,ForumPostNo,ForumMsgType,ForumMsg) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_FORUMMSG = 
			"UPDATE forummsg SET ForumMsg=? WHERE ForumMsgNo = ?";
	private static final String UPDATE_FORUMMSGTYPE = 
			"UPDATE forummsg SET ForumMsgType=? WHERE ForumMsgNo = ?";
	private static final String GET_ONE_STMT = 
			"SELECT ForumMsgNo,MemNo,ForumPostNo,ForumMsgType,ForumMsg,ForumMsgTime FROM forummsg WHERE ForumMsgNo = ?";
	private static final String GET_ALL_STMT = 
			"SELECT ForumMsgNo,MemNo,ForumPostNo,ForumMsgType,ForumMsg,ForumMsgTime FROM forummsg ORDER BY ForumMsgNo";
	private static final String GET_ONE_FORUMPOST_FORUMMSG = 
			"SELECT ForumMsgNo,MemNo,ForumPostNo,ForumMsgType,ForumMsg,ForumMsgTime FROM forummsg WHERE ForumPostNo = ? ORDER BY ForumMsgNo";
	
	

	@Override
	public void insert(ForumMsgVO forumMsgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, forumMsgVO.getMemNo());
			pstmt.setInt(2, forumMsgVO.getForumPostNo());
			pstmt.setInt(3, forumMsgVO.getForumMsgType());
			pstmt.setString(4, forumMsgVO.getForumMsg());

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
	public void update(ForumMsgVO forumMsgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_FORUMMSG);

			pstmt.setString(1, forumMsgVO.getForumMsg());
			pstmt.setInt(2, forumMsgVO.getForumMsgNo());

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
	
	public void updateForumMsgType(ForumMsgVO forumMsgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_FORUMMSGTYPE);

			pstmt.setInt(1, forumMsgVO.getForumMsgType());
			pstmt.setInt(2, forumMsgVO.getForumMsgNo());

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
	public ForumMsgVO findByPrimaryKey(Integer forumMsgNo) {

		ForumMsgVO forumMsgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, forumMsgNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				forumMsgVO = new ForumMsgVO();
				forumMsgVO.setForumMsgNo(rs.getInt("forumMsgNo"));
				forumMsgVO.setMemNo(rs.getInt("memNo"));
				forumMsgVO.setForumPostNo(rs.getInt("forumPostNo"));
				forumMsgVO.setForumMsgType(rs.getInt("forumMsgType"));
				forumMsgVO.setForumMsg(rs.getString("forumMsg"));
				forumMsgVO.setForumMsgTime(rs.getTimestamp("forumMsgTime"));
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
		return forumMsgVO;
	}
	
	
	@Override
	public List<ForumMsgVO> getAll() {
		List<ForumMsgVO> list = new ArrayList<ForumMsgVO>();
		ForumMsgVO forumMsgVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				forumMsgVO = new ForumMsgVO();
				forumMsgVO.setForumMsgNo(rs.getInt("forumMsgNo"));
				forumMsgVO.setMemNo(rs.getInt("memNo"));
				forumMsgVO.setForumPostNo(rs.getInt("forumPostNo"));
				forumMsgVO.setForumMsgType(rs.getInt("forumMsgType"));
				forumMsgVO.setForumMsg(rs.getString("forumMsg"));
				forumMsgVO.setForumMsgTime(rs.getTimestamp("forumMsgTime"));
				list.add(forumMsgVO); // Store the row in the list
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
	public List<ForumMsgVO> findForumPostForumMsg(Integer forumPostNo) {

		List<ForumMsgVO> list = new ArrayList<ForumMsgVO>();
		
		ForumMsgVO forumMsgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_FORUMPOST_FORUMMSG);
			pstmt.setInt(1, forumPostNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				forumMsgVO = new ForumMsgVO();
				forumMsgVO.setForumMsgNo(rs.getInt("forumMsgNo"));
				forumMsgVO.setMemNo(rs.getInt("memNo"));
				forumMsgVO.setForumPostNo(rs.getInt("forumPostNo"));
				forumMsgVO.setForumMsgType(rs.getInt("forumMsgType"));
				forumMsgVO.setForumMsg(rs.getString("forumMsg"));
				forumMsgVO.setForumMsgTime(rs.getTimestamp("forumMsgTime"));
				list.add(forumMsgVO);
			}
;
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
//		ForumMsgJDBCDAO dao = new ForumMsgJDBCDAO();

//OK!	//新增
//		ForumMsgVO newForumMsg = new ForumMsgVO();
//		newForumMsg.setMemNo(11004);
//		newForumMsg.setForumPostNo(41007);
//		newForumMsg.setForumMsgType(1);
//		newForumMsg.setForumMsg("有新角色要噴錢了...");
//	
//		dao.insert(newForumMsg);
//
//OK!	// 修改
//		ForumMsgVO updateForumMsg = new ForumMsgVO();
//		updateForumMsg.setForumMsg("");
//		updateForumMsg.setForumMsgNo(42004);
//		
//		dao.update(updateForumMsg);
			
//OK!   //修改 ForumMsgType 
//		ForumMsgVO updateForumMsg = new ForumMsgVO();
//		updateForumMsg.setForumMsgType(0);
//		updateForumMsg.setForumMsgNo(42004);
//		
//		dao.updateForumMsgType(updateForumMsg);
		
		
//
//OK!	//查詢 One 
//		ForumMsgVO oneForumMsg = dao.findByPrimaryKey(42001);
//		System.out.print(oneForumMsg.getForumMsgNo() + ",");
//		System.out.print(oneForumMsg.getMemNo() + ",");
//		System.out.print(oneForumMsg.getForumPostNo() + ",");
//		System.out.print(oneForumMsg.getForumMsgType() + ",");
//		System.out.print(oneForumMsg.getForumMsg() + ",");
//		System.out.print(oneForumMsg.getForumMsgTime() + ",");
//		System.out.println();
//		System.out.println("---------------------");
//
//OK!   //查詢 All
//		List<ForumMsgVO> list = dao.getAll();
//		for (ForumMsgVO aForumMsg : list) {
//		System.out.print(aForumMsg.getForumMsgNo() + ",");
//		System.out.print(aForumMsg.getMemNo() + ",");
//		System.out.print(aForumMsg.getForumPostNo() + ",");
//		System.out.print(aForumMsg.getForumMsgType() + ",");
//		System.out.print(aForumMsg.getForumMsg() + ",");
//		System.out.print(aForumMsg.getForumMsgTime() + ",");
//		System.out.println();
//		System.out.println("----------");
//		}
		
		
//OK!   //查詢 某討論區所有留言
	
//		List<ForumMsgVO> list = dao.findForumPostForumMsg(41005);
//	
//		for (ForumMsgVO aForumMsg : list) {
//		System.out.print(aForumMsg.getForumMsgNo() + ",");
//		System.out.print(aForumMsg.getMemNo() + ",");
//		System.out.print(aForumMsg.getForumPostNo() + ",");
//		System.out.print(aForumMsg.getForumMsgType() + ",");
//		System.out.print(aForumMsg.getForumMsg() + ",");
//		System.out.print(aForumMsg.getForumMsgTime() + ",");
//		System.out.println();
//		System.out.println("----------");
//		}
//	}
}