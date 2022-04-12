package com.bidpic.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.bidpic.model.BidPicDAO;
import com.bidpic.model.BidPicVO;

public class BidPicDAO implements BidPicDAO_interface {

//	// 取得連線池連線
//	// 下面方法使用 try-with-resource 自動關閉
//	private static DataSource ds = null;
//
//	static {
//		try {
//			ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/TestDB");
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	// SQL語句
	private static final String INSERT_STMT = 
			"INSERT INTO bidpic (BidProductNo, BidProdPicContent) VALUES (?, ?)";
	
	private static final String UPDATE_STMT = 
			"UPDATE bidpic SET BidProductNo = ?, BidProdPicContent = ? WHERE BidProdPicNo = ?";
	
	private static final String DELETE_STMT = 
			"DELETE FROM bidpic WHERE BidProdPicNo = ?";
	
	private static final String GET_ALL_STMT = 
			"SELECT BidProdPicNo, BidProductNo, BidProdPicContent FROM bidpic";
	
	private static final String GET_ONE_STMT = 
			"SELECT BidProdPicNo, BidProductNo, BidProdPicContent FROM bidpic WHERE BidProdPicNo = ?";
	// 使用 BidProductNo 取得所有 BidProductNo 的照片
	private static final String GET_ALL_STMT_BIDPRODUCTNO = "SELECT BidProdPicNo, BidProductNo, BidProdPicContent FROM bidpic WHERE BidProductNo = ?";
	// 使用 BidProductNo 取得封面(第一張) BidProductNo 的照片
	private static final String GET_ONE_STMT_BIDPRODUCTNO = "SELECT BidProdPicNo, BidProductNo, BidProdPicContent FROM bidpic WHERE BidProductNo = ? limit 1";
	
	// BidPicDAO介面 方法實作
	@Override
	public void insert(BidPicVO bidPicVO) {
		try (Connection con = getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT);
		) {
			
			pstmt.setInt(1, bidPicVO.getBidProdPicNo());
			pstmt.setBytes(2, bidPicVO.getBidProdPicContent());

			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("資料庫新增錯誤" + e.getMessage());
		}

	}

	@Override
	public void update(BidPicVO bidPicVO) {
		try (Connection con = getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(UPDATE_STMT);
		) {
			
			pstmt.setInt(1, bidPicVO.getBidProductNo());
			pstmt.setBytes(2, bidPicVO.getBidProdPicContent());
			pstmt.setInt(3, bidPicVO.getBidProdPicNo());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("資料庫更新錯誤" + e.getMessage());
		}

	}

	@Override
	public void delete(Integer bidProdPicNo) {
		try (Connection con = getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(DELETE_STMT);
		) {
			
			pstmt.setInt(1, bidProdPicNo);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("資料庫刪除錯誤" + e.getMessage());
		}

	}

	@Override
	public List<BidPicVO> getAll() {
		
		List<BidPicVO> list = new ArrayList<BidPicVO>();
		BidPicVO bidPicVO = null;
		
		try (Connection con = getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);
		) {
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				bidPicVO = new BidPicVO();
				bidPicVO.setBidProdPicNo(rs.getInt("BidProdPicNo"));
				bidPicVO.setBidProductNo(rs.getInt("BidProductNo"));
				bidPicVO.setBidProdPicContent(rs.getBytes("BidProdPicContent"));
				list.add(bidPicVO);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("資料庫取得錯誤" + e.getMessage());
		}
		return list;
	}

	@Override
	public BidPicVO findByPrimaryKey(Integer bidProdPicNo) {
		
		BidPicVO bidPicVO = null;
		ResultSet rs = null;
		
		try (Connection con = getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT);
		) {
			pstmt.setInt(1, bidProdPicNo);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				bidPicVO = new BidPicVO();
				bidPicVO.setBidProdPicNo(rs.getInt("BidProdPicNo"));
				bidPicVO.setBidProductNo(rs.getInt("BidProductNo"));
				bidPicVO.setBidProdPicContent(rs.getBytes("BidProdPicContent"));
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("資料庫取得錯誤" + e.getMessage());
		}
		return bidPicVO;
	}

	@Override
	public List<BidPicVO> findByBidProductNo(Integer bidProductNo) {
		List<BidPicVO> list = new ArrayList<BidPicVO>();
		BidPicVO bidPicVO = null;
		ResultSet rs = null;
		
		try (Connection con = getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT_BIDPRODUCTNO);
		) {
			
			pstmt.setInt(1, bidProductNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bidPicVO = new BidPicVO();
				bidPicVO.setBidProdPicNo(rs.getInt("BidProdPicNo"));
				bidPicVO.setBidProductNo(rs.getInt("BidProductNo"));
				bidPicVO.setBidProdPicContent(rs.getBytes("BidProdPicContent"));
				list.add(bidPicVO);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("資料庫取得錯誤" + e.getMessage());
		}
		return list;
	}

	@Override
	public BidPicVO findFirstPicByBidProductNo(Integer bidProductNo) {
		
		BidPicVO bidPicVO = null;
		ResultSet rs = null;
		
		try (Connection con = getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT_BIDPRODUCTNO);
		) {
			pstmt.setInt(1, bidProductNo);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				bidPicVO = new BidPicVO();
				bidPicVO.setBidProdPicNo(rs.getInt("BidProdPicNo"));
				bidPicVO.setBidProductNo(rs.getInt("BidProductNo"));
				bidPicVO.setBidProdPicContent(rs.getBytes("BidProdPicContent"));
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("資料庫取得錯誤" + e.getMessage());
		}
		return bidPicVO;
	}

}
