package com.djzhao.common;

/**
 * 数据库信息。
 * 
 * @author djzhao
 * @time 2017年2月22日 上午11:03:45
 */
public class DatabaseInfo {
	
	public static String[] DRIVES = {"oracle.jdbc.driver.OracleDriver", "com.mysql.jdbc.Driver"};
	
	/** 链接地址 */
	public static String URI;
	
	/** 用户名 */
	public static String USER;
	
	/** 密码 */
	public static String PASSWORD;
	
	/** 数据库类型 */
	public static DATABASEENUM DATABASE; 
}
