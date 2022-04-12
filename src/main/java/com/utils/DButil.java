package com.utils;

public class DButil {
	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://35.194.146.30:3306/cga101g1?serverTimezone=Asia/Taipei";
	private static final String USERID = "tibame";
	private static final String PASSWORD = "tibame";
	public static String getDriver() {
		return DRIVER;
	}
	public static String getUrl() {
		return URL;
	}
	public static String getUserid() {
		return USERID;
	}
	public static String getPassword() {
		return PASSWORD;
	}

}
