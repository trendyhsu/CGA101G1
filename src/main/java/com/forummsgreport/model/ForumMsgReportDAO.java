package com.forummsgreport.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class ForumMsgReportDAO implements ForumMsgReportDAO_interface {

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
		"INSERT INTO forummsgreport (ForumMsgNo,MemNo,ForumMsgReportType,ForumMsgReportWhy) VALUES (?, ?, ?, ?)";
	private static final String UPDATE = 
		"UPDATE forummsgreport SET ForumMsgReportType=? WHERE ForumMsgReportNo = ?";
	private static final String GET_ONE_STMT = 
		"SELECT ForumMsgReportNo,ForumMsgNo,MemNo,ForumMsgReportType,ForumMsgReportWhy,ForumMsgReportTime FROM forummsgreport WHERE ForumMsgReportNo = ?";
	private static final String GET_ALL_STMT = 
		"SELECT ForumMsgReportNo,ForumMsgNo,MemNo,ForumMsgReportType,ForumMsgReportWhy,ForumMsgReportTime FROM forummsgreport ORDER BY ForumMsgReportNo DESC";
	private static final String GET_MY_FORUMMSGREPORT=
		"SELECT ForumMsgNo,ForumMsgReportWhy,ForumMsgReportType FROM forummsgreport WHERE MemNo=? ORDER BY ForumMsgReportNo DESC";
	
	
	

	@Override
	public void insert(ForumMsgReportVO forumMsgReportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, forumMsgReportVO.getForumMsgNo());
			pstmt.setInt(2, forumMsgReportVO.getMemNo());
			pstmt.setInt(3, forumMsgReportVO.getForumMsgReportType());
			pstmt.setString(4, forumMsgReportVO.getForumMsgReportWhy());

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
	public void update(ForumMsgReportVO forumMsgReportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, forumMsgReportVO.getForumMsgReportType());
			pstmt.setInt(2, forumMsgReportVO.getForumMsgReportNo());

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
	public ForumMsgReportVO findByPrimaryKey(Integer forumMsgReportNo) {

		ForumMsgReportVO forumMsgReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, forumMsgReportNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				forumMsgReportVO = new ForumMsgReportVO();
				forumMsgReportVO.setForumMsgReportNo(rs.getInt("forumMsgReportNo"));
				forumMsgReportVO.setForumMsgNo(rs.getInt("forumMsgNo"));
				forumMsgReportVO.setMemNo(rs.getInt("memNo"));
				forumMsgReportVO.setForumMsgReportType(rs.getInt("forumMsgReportType"));
				forumMsgReportVO.setForumMsgReportWhy(rs.getString("forumMsgReportWhy"));
				forumMsgReportVO.setForumMsgReportTime(rs.getTimestamp("forumMsgReportTime"));
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
		return forumMsgReportVO ;
	}

	@Override
	public List<ForumMsgReportVO> getAll() {
		List<ForumMsgReportVO> list = new ArrayList<ForumMsgReportVO>();
		ForumMsgReportVO forumMsgReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				forumMsgReportVO = new ForumMsgReportVO();
				forumMsgReportVO.setForumMsgReportNo(rs.getInt("forumMsgReportNo"));
				forumMsgReportVO.setForumMsgNo(rs.getInt("forumMsgNo"));
				forumMsgReportVO.setMemNo(rs.getInt("memNo"));
				forumMsgReportVO.setForumMsgReportType(rs.getInt("forumMsgReportType"));
				forumMsgReportVO.setForumMsgReportWhy(rs.getString("forumMsgReportWhy"));
				forumMsgReportVO.setForumMsgReportTime(rs.getTimestamp("forumMsgReportTime"));
				list.add(forumMsgReportVO); // Store the row in the list
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
	
	public List<ForumMsgReportVO> findMyForumMsgReport(Integer memNo) {

		List<ForumMsgReportVO> list = new ArrayList<ForumMsgReportVO>();
		ForumMsgReportVO forumMsgReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MY_FORUMMSGREPORT);

			pstmt.setInt(1, memNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				forumMsgReportVO = new ForumMsgReportVO();
				forumMsgReportVO.setForumMsgNo(rs.getInt("forumMsgNo"));
				forumMsgReportVO.setForumMsgReportWhy(rs.getString("forumMsgReportWhy"));
				forumMsgReportVO.setForumMsgReportType(rs.getInt("forumMsgReportType"));
				list.add(forumMsgReportVO);
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
		return list ;
	}

//	public static void main(String[] args) {
//
//		ForumMsgReportJDBCDAO dao = new ForumMsgReportJDBCDAO();

//OK!	//新增
//		ForumMsgReportVO newForumMsgReport = new ForumMsgReportVO();
//		newForumMsgReport.setForumMsgNo(42005);
//		newForumMsgReport.setMemNo(11003);
//		newForumMsgReport.setForumMsgReportType(0);
//		newForumMsgReport.setForumMsgReportWhy("�o�H�n�Q��");
//		
//		dao.insert(newForumMsgReport);
//
//OK!	//修改 
//		ForumMsgReportVO updateForumMsgReport = new ForumMsgReportVO();
//		
//		updateForumMsgReport.setForumMsgReportType(2);
//		updateForumMsgReport.setForumMsgReportNo(44001);
//		
//		dao.update(updateForumMsgReport);

//OK!	// 查詢 One
//		ForumMsgReportVO oneForumMsgReport = dao.findByPrimaryKey(44002);
//		System.out.print(oneForumMsgReport.getForumMsgReportNo() + ",");
//		System.out.print(oneForumMsgReport.getForumMsgNo() + ",");
//		System.out.print(oneForumMsgReport.getMemNo() + ",");
//		System.out.print(oneForumMsgReport.getForumMsgReportType() + ",");
//		System.out.print(oneForumMsgReport.getForumMsgReportWhy() + ",");
//		System.out.print(oneForumMsgReport.getForumMsgReportTime() + ",");
//		
//		System.out.println("---------------------");
//
//OK!   //查詢 All
//		List<ForumMsgReportVO> list = dao.getAll();
//		for (ForumMsgReportVO aForumMsgReport : list) {
//			System.out.print(aForumMsgReport.getForumMsgReportNo() + ",");
//			System.out.print(aForumMsgReport.getForumMsgNo() + ",");
//			System.out.print(aForumMsgReport.getMemNo() + ",");
//			System.out.print(aForumMsgReport.getForumMsgReportType() + ",");
//			System.out.print(aForumMsgReport.getForumMsgReportWhy() + ",");
//			System.out.print(aForumMsgReport.getForumMsgReportTime() + ",");
//			System.out.println();
//		}
		
//OK!	//查詢 MY_FORUMMSGREPORT
//		List<ForumMsgReportVO> list = dao.findMyForumMsgReport(11003);
//		
//		for (ForumMsgReportVO myForumMsgReport : list) {
//			
//			System.out.print(myForumMsgReport.getForumMsgNo() + ",");
//			System.out.print(myForumMsgReport.getForumMsgReportWhy() + ",");
//			System.out.print(myForumMsgReport.getForumMsgReportType());
//		
//			System.out.println();
//		}
//	}
}