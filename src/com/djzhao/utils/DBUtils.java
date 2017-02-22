package com.djzhao.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 数据库工具。
 * 
 * @author djzhao
 * @time 2017年2月22日 上午10:23:55
 */
public class DBUtils {
	
	public String url;
	
	public String user;
	
	public String password;

	public DBUtils(String url, String user, String password) {
		super();
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	public Connection getConn() {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
