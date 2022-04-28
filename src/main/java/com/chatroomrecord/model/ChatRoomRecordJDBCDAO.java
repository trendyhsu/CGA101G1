package com.chatroomrecord.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bidproduct.model.BidProductVO;
import com.utils.DButil;

public class ChatRoomRecordJDBCDAO extends DButil implements ChatRoomRecordDAO_interface{

	private static final String INSERT_STMT = 
		"INSERT INTO chatroomrecord (memNo,forumNo,chatRoomContent) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT chatRoomRecordNo,memNo,forumNo,chatRoomContent,chatTime FROM chatroomrecord "
		+ "order by memNo";
	private static final String GET_ONE_STMT = 
		"SELECT chatRoomRecordNo,memNo,forumNo,chatRoomContent,chatTime FROM chatroomrecord "
		+ "where memNo = ?";
	//查詢所有 memNo
	private static final String GET_ALL_STMT_MEMNO = 
		"SELECT chatRoomRecordNo,memNo,forumNo,chatRoomContent,chatTime "
		+ "FROM chatroomrecord WHERE memNo = ?";
	private static final String DELETE = 
		"DELETE FROM chatroomrecord where chatRoomRecordNo = ?";
		private static final String UPDATE = 
		"UPDATE chatroomrecord set chatRoomContent=? where chatRoomRecordNo = ?";
		
	@Override
	public void insert(ChatRoomRecordVO chatroomrecordVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, chatroomrecordVO.getMemNo());
			pstmt.setInt(2, chatroomrecordVO.getForumNo());
			pstmt.setString(3, chatroomrecordVO.getChatRoomContent());
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
	public void update(ChatRoomRecordVO chatRoomRecordVO) {
		// TODO Auto-generated method stub
		
		}

	@Override
	public void delete(Integer chatRoomRecordNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ChatRoomRecordVO findByPrimaryKey(Integer chatRoomRecordNo) {
		// TODO Auto-generated method stub
		ChatRoomRecordVO chatRoomRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, chatRoomRecordNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				chatRoomRecordVO = new ChatRoomRecordVO();
				chatRoomRecordVO.setChatRoomRecordNo(rs.getInt("chatRoomRecordNo"));
				chatRoomRecordVO.setMemNo(rs.getInt("memNo"));
				chatRoomRecordVO.setForumNo(rs.getInt("forumNo"));
				chatRoomRecordVO.setChatRoomContent(rs.getString("chatRoomContent"));
				chatRoomRecordVO.setChatTime(rs.getTimestamp("chatTime"));
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
		return chatRoomRecordVO;
	}

	@Override
	public List<ChatRoomRecordVO> getAll() {
		// TODO Auto-generated method stub
		List<ChatRoomRecordVO> list = new ArrayList<ChatRoomRecordVO>();
		ChatRoomRecordVO chatRoomRecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chatRoomRecordVO = new ChatRoomRecordVO();
				chatRoomRecordVO.setChatRoomRecordNo(rs.getInt("chatRoomRecordNo"));
				chatRoomRecordVO.setMemNo(rs.getInt("memNo"));
				chatRoomRecordVO.setForumNo(rs.getInt("forumNo"));
				chatRoomRecordVO.setChatRoomContent(rs.getString("chatRoomContent"));
				chatRoomRecordVO.setChatTime(rs.getTimestamp("chatTime"));
				list.add(chatRoomRecordVO); // Store the row in the list
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
	public List<ChatRoomRecordVO> findByMemNoRecord(Integer memNo) {
		// TODO Auto-generated method stub
		List<ChatRoomRecordVO> list = new ArrayList<ChatRoomRecordVO>();
		ChatRoomRecordVO chatRoomRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ALL_STMT_MEMNO);
			pstmt.setInt(1, memNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chatRoomRecordVO = new ChatRoomRecordVO();
				chatRoomRecordVO.setChatRoomRecordNo(rs.getInt("chatRoomRecordNo"));
				chatRoomRecordVO.setMemNo(rs.getInt("memNo"));
				chatRoomRecordVO.setForumNo(rs.getInt("forumNo"));
				chatRoomRecordVO.setChatRoomContent(rs.getString("chatRoomContent"));
				chatRoomRecordVO.setChatTime(rs.getTimestamp("chatTime"));
				list.add(chatRoomRecordVO); // Store the row in the list
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

		ChatRoomRecordJDBCDAO dao = new ChatRoomRecordJDBCDAO();

		// 新增
//		ChatRoomRecordVO chatVO1 = new ChatRoomRecordVO();
//		chatVO1.setMemNo(11002);
//		chatVO1.setForumNo(46001);
//		chatVO1.setChatRoomContent("早上好馬萊");
//		dao.insert(chatVO1);
//		System.out.println("新增成功");
		// 查詢，單一
//		ChatRoomRecordVO chatVO2 = dao.findByPrimaryKey(47001);
//		System.out.print(chatVO2.getChatRoomRecordNo() + ",");
//		System.out.print(chatVO2.getMemNo() + ",");
//		System.out.print(chatVO2.getForumNo() + ",");
//		System.out.print(chatVO2.getChatRoomContent() + ",");
//		System.out.print(chatVO2.getChatTime() + ",");
//		System.out.println("---------------------");
		
		//查詢，單一會員所有紀錄
		List<ChatRoomRecordVO> list1 = dao.findByMemNoRecord(11001);
		for(ChatRoomRecordVO chatRoomRecordVO : list1) {
		System.out.print(chatRoomRecordVO.getChatRoomRecordNo() + ",");
		System.out.print(chatRoomRecordVO.getMemNo() + ",");
		System.out.print(chatRoomRecordVO.getForumNo() + ",");
		System.out.print(chatRoomRecordVO.getChatRoomContent() + ",");
		System.out.print(chatRoomRecordVO.getChatTime() + ",");
		System.out.println("---------------------");
		//查詢，all
//		List<ChatRoomRecordVO> list = dao.getAll();
//		for (ChatRoomRecordVO chat03 : list) {
//		System.out.print(chat03.getChatRoomRecordNo() + ",");
//		System.out.print(chat03.getMemNo() + ",");
//		System.out.print(chat03.getForumNo() + ",");
//		System.out.print(chat03.getChatRoomContent() + ",");
//		System.out.print(chat03.getChatTime() + ",");
//		System.out.println("---------------------");
//			}
		}
	}
}
