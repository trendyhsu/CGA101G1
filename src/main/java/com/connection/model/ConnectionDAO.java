package com.connection.model;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public interface ConnectionDAO {
	static DataSource getDataSource() {
		try {
			return (DataSource) new InitialContext().lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	default Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}
}
