package com.djzhao.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * ���ݿ⹤�ߡ�
 * 
 * @author djzhao
 * @time 2017��2��22�� ����10:23:55
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
