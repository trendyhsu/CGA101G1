package com.chatroombanlist.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chatroomrecord.model.ChatRoomRecordVO;
import com.utils.DButil;

public class ChatRoomBanListJDBCDAO extends DButil implements ChatRoomBanListDAO_interface{

	private static final String INSERT_STMT = 
		"INSERT INTO chatroombanlist (memNo,memNo_Baned) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT banListNo,memNo,memNo_Baned FROM chatroombanlist "
		+ "order by banListNo";
	private static final String GET_ONE_STMT_MEMNO = 
		"SELECT banListNo,memNo,memNo_Baned FROM chatroombanlist "
		+ "where MemNo = ?";
	private static final String DELETE = 
		"DELETE FROM chatroombanlist where (memNo = ?) And (memNo_Baned = ?)";
		private static final String UPDATE = 
		"UPDATE chatroombanlist set memNo_Baned=? where banListNo = ?";
	
	@Override
	public void insert(ChatRoomBanListVO chatRoomBanListVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, chatRoomBanListVO.getMemNo());
			pstmt.setInt(2, chatRoomBanListVO.getMemNo_Baned());

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
	public void update(ChatRoomBanListVO chatRoomBanListVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer memNo,Integer memNo_Baned) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memNo);
			pstmt.setInt(2, memNo_Baned);
			pstmt.executeUpdate();
			System.out.println("成功刪除");
			// Handle any getDriver() errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database getDriver(). "
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
	public List<ChatRoomBanListVO> findByOneMemBanList(Integer memNo) {
		// TODO Auto-generated method stub
		List<ChatRoomBanListVO> list = new ArrayList<ChatRoomBanListVO>();
		ChatRoomBanListVO chatRoomBanListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ONE_STMT_MEMNO);
			pstmt.setInt(1, memNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chatRoomBanListVO = new ChatRoomBanListVO();
				chatRoomBanListVO.setBanListNo(rs.getInt("banListNo"));
				chatRoomBanListVO.setMemNo(rs.getInt("memNo"));
				chatRoomBanListVO.setMemNo_Baned(rs.getInt("memNo_Baned"));
				list.add(chatRoomBanListVO); // Store the row in the list
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
	public List<ChatRoomBanListVO> getAll() {
		// TODO Auto-generated method stub
		List<ChatRoomBanListVO> list = new ArrayList<ChatRoomBanListVO>();
		ChatRoomBanListVO chatRoomBanListVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chatRoomBanListVO = new ChatRoomBanListVO();
				chatRoomBanListVO.setBanListNo(rs.getInt("banListNo"));
				chatRoomBanListVO.setMemNo(rs.getInt("memNo"));
				chatRoomBanListVO.setMemNo_Baned(rs.getInt("memNo_Baned"));

				list.add(chatRoomBanListVO); // Store the row in the list
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

	public static void main(String[] args) {

		ChatRoomBanListJDBCDAO dao = new ChatRoomBanListJDBCDAO();

		// 新增
//		ChatRoomBanListVO chatVO1 = new ChatRoomBanListVO();
//		chatVO1.setMemNo(11001);
//		chatVO1.setMemNo_Baned(11002);
//		dao.insert(chatVO1);
//		System.out.println("新增成功");
		//查詢單會員，全忽略
		List<ChatRoomBanListVO> list = dao.findByOneMemBanList(11001);
		for (ChatRoomBanListVO banList : list) {
		System.out.print(banList.getBanListNo() + ",");
		System.out.print(banList.getMemNo() + ",");
		System.out.print(banList.getMemNo_Baned() + ",");
		System.out.println("---------------------");
		}
//		//查詢全
//		List<ChatRoomBanListVO> list1 = dao.getAll();
//		for (ChatRoomBanListVO banList1 : list1) {
//		System.out.print(banList1.getBanListNo() + ",");
//		System.out.print(banList1.getMemNo() + ",");
//		System.out.print(banList1.getMemNo_Baned() + ",");
//		System.out.println("---------------------");
//		}
//	
//		// 刪除單一
//		dao.delete(11002,11001);
		
	}
}
