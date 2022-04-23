package com.forumpost.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class ForumPostJNDIDAO implements ForumPostDAO_interface {

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
		"INSERT INTO forumpost (ForumNo,ForumPostType,MemNo,ForumPostState,ForumPostTitle,ForumPostContent,ForumPostFeatured) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
		"UPDATE forumpost SET ForumPostType=?,ForumPostTitle=?, ForumPostContent=? WHERE ForumPostNo = ?";
	private static final String UPDATE_FORUMPOSTSTATE = 
		"UPDATE forumpost SET ForumPostState=? WHERE ForumPostNo = ?";
	private static final String UPDATE_MASTER = 
		"UPDATE forumpost SET ForumPostFeatured=? WHERE ForumPostNo = ?";
	private static final String UPDATE_ADMIN = 
		"UPDATE forumpost SET ForumPostType=?, ForumPostState=? WHERE ForumPostNo = ?";
	private static final String GET_ONE_STMT = 
		"SELECT ForumPostNo,ForumNo,ForumPostType,MemNo,ForumPostState,ForumPostTitle,ForumPostContent,ForumPostTime,ForumPostFeatured FROM forumpost WHERE ForumPostNo = ?";
	private static final String GET_ALL_STMT = 
		"SELECT ForumPostNo,ForumNo,ForumPostType,MemNo,ForumPostState,ForumPostTitle,ForumPostContent,ForumPostTime,ForumPostFeatured FROM forumpost ORDER BY ForumPostNo";
	private static final String GET_ONE_FORUM_STMT = 
		"SELECT ForumPostFeatured,ForumPostType,ForumPostTitle,MemNo,ForumPostTime FROM forumpost WHERE ForumNo = ?";
	private static final String GET_ONE_MEM_STMT = 
		"SELECT ForumNo,ForumPostTitle,ForumPostTime FROM forumpost WHERE MemNo = ?";
	private static final String GET_FIND_POSTTYPE_POSTNAME = 
		"SELECT ForumPostNo,ForumNo,ForumPostType,MemNo,ForumPostState,ForumPostTitle,ForumPostTime,ForumPostFeatured FROM forumpost WHERE ForumNo=? AND ForumPostType=? AND ForumPostTitle LIKE ?";

	@Override
	public void insert(ForumPostVO forumPostVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, forumPostVO.getForumNo());
			pstmt.setInt(2, forumPostVO.getForumPostType());
			pstmt.setInt(3, forumPostVO.getMemNo());
			pstmt.setInt(4, forumPostVO.getForumPostState());
			pstmt.setString(5, forumPostVO.getForumPostTitle());
			pstmt.setString(6, forumPostVO.getForumPostContent());
			pstmt.setInt(7, forumPostVO.getForumPostFeatured());

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
	public void update(ForumPostVO forumPostVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, forumPostVO.getForumPostType());
			pstmt.setString(2, forumPostVO.getForumPostTitle());
			pstmt.setString(3, forumPostVO.getForumPostContent());
			pstmt.setInt(4, forumPostVO.getForumPostNo());

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
	public void updateForumPostState(ForumPostVO forumPostVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_FORUMPOSTSTATE);

			pstmt.setInt(1, forumPostVO.getForumPostState());
			pstmt.setInt(2, forumPostVO.getForumPostNo());

			pstmt.executeUpdate();
;
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
	public void updateMaster(ForumPostVO forumPostVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_MASTER);

			pstmt.setInt(1, forumPostVO.getForumPostFeatured());
			pstmt.setInt(2, forumPostVO.getForumPostNo());

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
	public void updateAdmin(ForumPostVO forumPostVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ADMIN);

			pstmt.setInt(1, forumPostVO.getForumPostType());
			pstmt.setInt(2, forumPostVO.getForumPostState());
			pstmt.setInt(3, forumPostVO.getForumPostNo());

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
	public ForumPostVO findByPrimaryKey(Integer forumPostNo) {

		ForumPostVO forumPostVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, forumPostNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
		
				forumPostVO = new ForumPostVO();
				forumPostVO.setForumPostNo(rs.getInt("forumPostNo"));
				forumPostVO.setForumNo(rs.getInt("forumNo"));
				forumPostVO.setForumPostType(rs.getInt("forumPostType"));
				forumPostVO.setMemNo(rs.getInt("memNo"));
				forumPostVO.setForumPostState(rs.getInt("forumPostState"));
				forumPostVO.setForumPostTitle(rs.getString("forumPostTitle"));
				forumPostVO.setForumPostContent(rs.getString("forumPostContent"));
				forumPostVO.setForumPostTime(rs.getTimestamp("forumPostTime"));
				forumPostVO.setForumPostFeatured(rs.getInt("forumPostFeatured"));
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
		return forumPostVO;
	}

	@Override
	public List<ForumPostVO> getAll() {
		List<ForumPostVO> list = new ArrayList<ForumPostVO>();
		ForumPostVO forumPostVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				forumPostVO = new ForumPostVO();
				forumPostVO.setForumPostNo(rs.getInt("forumPostNo"));
				forumPostVO.setForumNo(rs.getInt("forumNo"));
				forumPostVO.setForumPostType(rs.getInt("forumPostType"));
				forumPostVO.setMemNo(rs.getInt("memNo"));
				forumPostVO.setForumPostState(rs.getInt("forumPostState"));
				forumPostVO.setForumPostTitle(rs.getString("forumPostTitle"));
				forumPostVO.setForumPostContent(rs.getString("forumPostContent"));
				forumPostVO.setForumPostTime(rs.getTimestamp("forumPostTime"));
				forumPostVO.setForumPostFeatured(rs.getInt("forumPostFeatured"));
				list.add(forumPostVO); // Store the row in the list
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
	
	@Override
	public List<ForumPostVO> findByForumNo(Integer forumNo) {

		List<ForumPostVO> list = new ArrayList<ForumPostVO>();
		
		ForumPostVO forumPostVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_FORUM_STMT);

			pstmt.setInt(1, forumNo);

			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				
				forumPostVO = new ForumPostVO();
				forumPostVO.setForumPostFeatured(rs.getInt("forumPostFeatured"));
				forumPostVO.setForumPostType(rs.getInt("forumPostType"));
				forumPostVO.setForumPostTitle(rs.getString("forumPostTitle"));
				forumPostVO.setMemNo(rs.getInt("memNo"));
				forumPostVO.setForumPostTime(rs.getTimestamp("forumPostTime"));
				list.add(forumPostVO);
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
	
	@Override
	public List<ForumPostVO> findByMemNo(Integer memNo) {

		List<ForumPostVO> list = new ArrayList<ForumPostVO>();
		
		ForumPostVO forumPostVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_MEM_STMT);

			pstmt.setInt(1, memNo);

			rs = pstmt.executeQuery();
	
			while (rs.next()) {
			
				forumPostVO = new ForumPostVO();
				forumPostVO.setForumNo(rs.getInt("forumNo"));
				forumPostVO.setForumPostTitle(rs.getString("forumPostTitle"));
				forumPostVO.setForumPostTime(rs.getTimestamp("forumPostTime"));
				list.add(forumPostVO);
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
	
	public List<ForumPostVO> findByPostTypeName(Integer forumNo ,Integer forumPostType,String postCharacter) {

		List<ForumPostVO> list = new ArrayList<ForumPostVO>();

		ForumPostVO forumPostVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_FIND_POSTTYPE_POSTNAME);

			pstmt.setInt(1, forumNo);
			pstmt.setInt(2, forumPostType);
			pstmt.setString(3, "%" + postCharacter + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				forumPostVO = new ForumPostVO();
				forumPostVO.setForumPostNo(rs.getInt("forumPostNo"));
				forumPostVO.setForumNo(rs.getInt("forumNo"));
				forumPostVO.setForumPostType(rs.getInt("forumPostType"));
				forumPostVO.setMemNo(rs.getInt("memNo"));
				forumPostVO.setForumPostState(rs.getInt("forumPostState"));
				forumPostVO.setForumPostTitle(rs.getString("forumPostTitle"));
				forumPostVO.setForumPostTime(rs.getTimestamp("forumPostTime"));
				forumPostVO.setForumPostFeatured(rs.getInt("forumPostFeatured"));

				list.add(forumPostVO);
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
//		ForumPostJDBCDAO dao = new ForumPostJDBCDAO();
//
////OK!	// 新增 
//		ForumPostVO newForumPost = new ForumPostVO();
//		newForumPost.setForumNo(46004);
//		newForumPost.setForumPostType(5);
//		newForumPost.setMemNo(11007);
//		newForumPost.setForumPostState(1);
//		newForumPost.setForumPostTitle("");
//		newForumPost.setForumPostContent("");
//		newForumPost.setForumPostFeatured(0);
//		dao.insert(newForumPost);

//OK!	// 修改 
//		ForumPostVO updateForumPost = new ForumPostVO();
//		updateForumPost.setForumPostType(4);
//		updateForumPost.setForumPostTitle("");
//		updateForumPost.setForumPostContent("");
//		updateForumPost.setForumPostNo(41009);
//		
//		dao.update(updateForumPost);

		
//OK!	// 修改 UPDATE_FORUMPOSTSTATE 
//		ForumPostVO updateForumPost = new ForumPostVO();
//		updateForumPost.setForumPostState(0);
//		updateForumPost.setForumPostNo(41003);
//		
//		dao.updateForumPostState(updateForumPost);
		
		
//OK!   // 修改 UPDATE_MASTER
//		ForumPostVO updateForumPost = new ForumPostVO();
//		updateForumPost.setForumPostFeatured(1);
//		updateForumPost.setForumPostNo(41001);
//				
//		dao.updateMaster(updateForumPost);
		
//OK!   // 修改 UPDATE_ADMIN
//		ForumPostVO updateForumPost = new ForumPostVO();
//		updateForumPost.setForumPostType(2);
//		updateForumPost.setForumPostState(1);
//		updateForumPost.setForumPostNo(41001);
//						
//		dao.updateAdmin(updateForumPost);
			
//
//OK!	// 查詢 One
		
//		ForumPostVO oneForumPost = dao.findByPrimaryKey(41006);
//		System.out.println(oneForumPost.getForumPostNo() + ",");
//		System.out.println(oneForumPost.getForumNo() + ",");
//		System.out.println(oneForumPost.getForumPostType() + ",");
//		System.out.println(oneForumPost.getMemNo() + ",");
//		System.out.println(oneForumPost.getForumPostState() + ",");
//		System.out.println(oneForumPost.getForumPostTitle() + ",");
//		System.out.println(oneForumPost.getForumPostContent());
//		System.out.println(oneForumPost.getForumPostTime());
//		System.out.println(oneForumPost.getForumPostFeatured());
//		System.out.println("---------------------");
//
//OK!	// 查詢 All
//		List<ForumPostVO> list = dao.getAll();
//		for (ForumPostVO aForumPost : list) {
//			System.out.print(aForumPost.getForumPostNo() + ",");
//			System.out.print(aForumPost.getForumNo() + ",");
//			System.out.print(aForumPost.getForumPostType() + ",");
//			System.out.print(aForumPost.getMemNo() + ",");
//			System.out.println(aForumPost.getForumPostState() + ",");
//			System.out.println(aForumPost.getForumPostTitle() + ",");
//			System.out.println(aForumPost.getForumPostContent());
//			System.out.println(aForumPost.getForumPostTime());
//			System.out.println(aForumPost.getForumPostFeatured());
//			System.out.println("---------------------");
//		}
//		
		
//OK!	// 查詢 GET_ONE_FORUM_STMT
		
//		List<ForumPostVO> list = dao.findByForumNo(46003);
//		for (ForumPostVO aForumPost : list) {
//			
//			System.out.println(aForumPost.getForumPostFeatured()+",");
//			System.out.println(aForumPost.getForumPostType() + ",");
//			System.out.println(aForumPost.getForumPostTitle() + ",");
//			System.out.println(aForumPost.getMemNo() + ",");
//			System.out.println(aForumPost.getForumPostTime());
//			System.out.println("---------------------");
//		}
//OK!  // 查詢 GET_ONE_MEM_STMT
		
//	    List<ForumPostVO> list = dao.findByMemNo(11003);
//	    for (ForumPostVO aForumPost : list) {
//		
//		System.out.println(aForumPost.getForumNo()+",");
//		System.out.println(aForumPost.getForumPostTitle() + ",");
//		System.out.println(aForumPost.getForumPostTime());
//		System.out.println("---------------------");
//	}
	
	
//OK!  // 查詢 GET_FIND_POSTNAME
 
//		List<ForumPostVO> list = dao.findByPostTypeName(46002,0,5,"");
//	
//		for (ForumPostVO aForumPost : list) {
//
//		System.out.println(aForumPost.getForumPostNo() + ",");
//		System.out.println(aForumPost.getForumNo() + ",");
//		System.out.println(aForumPost.getForumPostType() + ",");
//		System.out.println(aForumPost.getMemNo() + ",");
//		System.out.println(aForumPost.getForumPostState() + ",");
//		System.out.println(aForumPost.getForumPostTitle() + ",");
//		System.out.println(aForumPost.getForumPostTime() + ",");
//		System.out.println(aForumPost.getForumPostFeatured());
//		System.out.println("---------------------");
//
//	}
//	}
}