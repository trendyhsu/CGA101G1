package com.forumpostreport.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class ForumPostReportJNDIDAO implements ForumPostReportDAO_interface {
	
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
		"INSERT INTO forumpostreport (ForumPostNo,MemNo,ForumPostReportType,ForumPostReportWhy) VALUES (?, ?, ?, ?)";
	private static final String UPDATE = 
		"UPDATE forumpostreport SET ForumPostReportType = ? WHERE ForumPostReportNo = ?";
	private static final String GET_ONE_STMT = 
		"SELECT ForumPostReportNo,ForumPostNo,MemNo,ForumPostReportType,ForumPostReportWhy,ForumPostReportTime FROM forumpostreport WHERE ForumPostReportNo = ?";
	private static final String GET_ALL_STMT = 
		"SELECT ForumPostReportNo,ForumPostNo,MemNo,ForumPostReportType,ForumPostReportWhy,ForumPostReportTime FROM forumpostreport ORDER BY ForumPostReportNo DESC";
	private static final String GET_MY_FORUMPOSTREPORT=
		"SELECT ForumPostNo,ForumPostReportWhy,ForumPostReportType FROM forumpostreport WHERE MemNo=? ORDER BY ForumPostReportNo Desc";
	
	

	@Override
	public void insert(ForumPostReportVO forumPostReportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, forumPostReportVO.getForumPostNo());
			pstmt.setInt(2, forumPostReportVO.getMemNo());
			pstmt.setInt(3, forumPostReportVO.getForumPostReportType());
			pstmt.setString(4, forumPostReportVO.getForumPostReportWhy());

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
	public void update(ForumPostReportVO forumPostReportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, forumPostReportVO.getForumPostReportType());
			pstmt.setInt(2, forumPostReportVO.getForumPostReportNo());

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
	public ForumPostReportVO findByPrimaryKey(Integer forumPostReportNo) {

		ForumPostReportVO forumPostReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, forumPostReportNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				forumPostReportVO = new ForumPostReportVO();
				forumPostReportVO.setForumPostReportNo(rs.getInt("forumPostReportNo"));
				forumPostReportVO.setForumPostNo(rs.getInt("forumPostNo"));
				forumPostReportVO.setMemNo(rs.getInt("memNo"));
				forumPostReportVO.setForumPostReportType(rs.getInt("forumPostReportType"));
				forumPostReportVO.setForumPostReportWhy(rs.getString("forumPostReportWhy"));
				forumPostReportVO.setForumPostReportTime(rs.getTimestamp("forumPostReportTime"));
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
		return forumPostReportVO;
	}

	@Override
	public List<ForumPostReportVO> getAll() {
		List<ForumPostReportVO> list = new ArrayList<ForumPostReportVO>();
		ForumPostReportVO forumPostReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
		
				forumPostReportVO = new ForumPostReportVO();
				forumPostReportVO.setForumPostReportNo(rs.getInt("forumPostReportNo"));
				forumPostReportVO.setForumPostNo(rs.getInt("forumPostNo"));
				forumPostReportVO.setMemNo(rs.getInt("memNo"));
				forumPostReportVO.setForumPostReportType(rs.getInt("forumPostReportType"));
				forumPostReportVO.setForumPostReportWhy(rs.getString("forumPostReportWhy"));
				forumPostReportVO.setForumPostReportTime(rs.getTimestamp("forumPostReportTime"));
				list.add(forumPostReportVO); // Store the row in the list
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
	public List<ForumPostReportVO> findMyForumPostReport(Integer memNo) {

		List<ForumPostReportVO> list = new ArrayList<ForumPostReportVO>();
		ForumPostReportVO forumPostReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MY_FORUMPOSTREPORT);

			pstmt.setInt(1, memNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				forumPostReportVO = new ForumPostReportVO();
				forumPostReportVO.setForumPostNo(rs.getInt("forumPostNo"));
				forumPostReportVO.setForumPostReportWhy(rs.getString("forumPostReportWhy"));
				forumPostReportVO.setForumPostReportType(rs.getInt("forumPostReportType"));
				list.add(forumPostReportVO);
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
//		ForumPostReportJDBCDAO dao = new ForumPostReportJDBCDAO();

//OK!	//新增
//		ForumPostReportVO newForumPostReport = new ForumPostReportVO();
//		newForumPostReport.setForumPostNo(41004);
//		newForumPostReport.setMemNo(11003);
//		newForumPostReport.setForumPostReportType(0);
//		newForumPostReport.setForumPostReportWhy("�o�����y��!");
//		
//		dao.insert(newForumPostReport);
//
//OK!	//修改 
//		ForumPostReportVO updateForumPostReport = new ForumPostReportVO();
//		updateForumPostReport.setForumPostReportType(2);
//		updateForumPostReport.setForumPostReportNo(43001);
//		
//		dao.update(updateForumPostReport);
//
//OK!	//查詢 One
//		ForumPostReportVO findOne = dao.findByPrimaryKey(43003);
//		System.out.print(findOne.getForumPostReportNo() + ",");
//		System.out.print(findOne.getForumPostNo() + ",");
//		System.out.print(findOne.getMemNo() + ",");
//		System.out.print(findOne.getForumPostReportType() + ",");
//		System.out.print(findOne.getForumPostReportWhy() + ",");
//		System.out.print(findOne.getForumPostReportTime() + ",");
//		System.out.println("---------------------");
//
//OK!	//查詢 All
//		List<ForumPostReportVO> list = dao.getAll();
//		for (ForumPostReportVO aForumPostReport : list) {
//			System.out.print(aForumPostReport.getForumPostReportNo() + ",");
//			System.out.print(aForumPostReport.getForumPostNo() + ",");
//			System.out.print(aForumPostReport.getMemNo() + ",");
//			System.out.print(aForumPostReport.getForumPostReportType() + ",");
//			System.out.print(aForumPostReport.getForumPostReportWhy() + ",");
//			System.out.print(aForumPostReport.getForumPostReportTime() + ",");
//			System.out.println("---------------------");
//		}
		
//OK!	//查詢 FindMyForumPostReport
//		List<ForumPostReportVO> list = dao.findMyForumPostReport(11003);
//		for (ForumPostReportVO aForumPostReport : list) {
//			
//			System.out.print(aForumPostReport.getForumPostNo() + ",");
//			System.out.print(aForumPostReport.getForumPostReportWhy() + ",");
//			System.out.println(aForumPostReport.getForumPostReportType());
//	
//			System.out.println("---------------------");
//		}
//		
//	}
}