package com.fqkeyword.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.google.gson.Gson;

public class FQKeyWordDAOImpl implements FQKeyWordDAO {
	// JNDI
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// 目前測試使用JDBC
//	String url = "jdbc:mysql://35.194.146.30:3306/cga101g1?serverTimezone=Asia/Taipei";
//	String user = "tibame";
//	String pswd = "tibame";

	@Override
	public List<FQKeyWordVO> getAll() {
		List<FQKeyWordVO> keyWords = new ArrayList<>();
		String sql = "SELECT FQKeyWordNo, FQKeyWordContent, AnswerContent from fqkeyword;";

		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Integer FQKeyWordNo = rs.getInt(1);
				String FQKeyWordContent = rs.getString(2);
				String AnswerContent = rs.getString(3);
				FQKeyWordVO keyWord = new FQKeyWordVO(FQKeyWordNo, FQKeyWordContent, AnswerContent);
				keyWords.add(keyWord);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return keyWords;

	}

	@Override
	public FQKeyWordVO findByPrimaryKey(Integer fqKeyWordNo) {
		FQKeyWordVO fqKeyWordVO = null;
		String sql = "SELECT FQKeyWordNo, FQKeyWordContent, AnswerContent FROM fqkeyword WHERE FQKeyWordNo = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, fqKeyWordNo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				fqKeyWordVO = new FQKeyWordVO();
				fqKeyWordVO.setFqKeyWordNo(rs.getInt("FQKeyWordNo"));
				fqKeyWordVO.setFqKeyWordContent(rs.getString("FQKeyWordContent"));
				fqKeyWordVO.setAnswerContent(rs.getString("AnswerContent"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		if(fqKeyWordVO == null) {
//			System.out.println("查無資料");
//		}else {
//			System.out.println(fqKeyWordVO);
//		}
		return fqKeyWordVO;

	}

	@Override
	public void insert(FQKeyWordVO fqKeyWordVO) {
		String sql = "INSERT INTO fqkeyword(`FQKeyWordContent`, `AnswerContent`)" + "values(?,?);";

		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, fqKeyWordVO.getFqKeyWordContent());
			ps.setString(2, fqKeyWordVO.getAnswerContent());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(FQKeyWordVO fqKeyWordVO) {
		String sql = "UPDATE fqkeyword set FQKeyWordContent = ?, AnswerContent = ? where FQKeyWordNo = ?;";

		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, fqKeyWordVO.getFqKeyWordContent());
			ps.setString(2, fqKeyWordVO.getAnswerContent());
			ps.setInt(3, fqKeyWordVO.getFqKeyWordNo());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Integer fqKeyWordNo) {
		String sql = "DELETE from fqkeyword where FQKeyWordNo = ?;";

		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, fqKeyWordNo);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<FQKeyWordVO> getAll(Map<String, String[]> map) {
		List<FQKeyWordVO> list = new ArrayList<FQKeyWordVO>();
		String sql = "SELECT FQKeyWordNo, FQKeyWordContent, AnswerContent from fqkeyword"
				+ AllForOneQuery.anyConditions(map);
		try (Connection conn = ds.getConnection(); 
			PreparedStatement ps = conn.prepareStatement(sql)) {
			System.out.println("finalSql = " + sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				FQKeyWordVO fqKeyWordVO = new FQKeyWordVO();
				fqKeyWordVO.setFqKeyWordNo(rs.getInt(1));
				fqKeyWordVO.setFqKeyWordContent(rs.getString(2));
				fqKeyWordVO.setAnswerContent(rs.getString(3));
				list.add(fqKeyWordVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
