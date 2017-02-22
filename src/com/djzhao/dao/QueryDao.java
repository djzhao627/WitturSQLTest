package com.djzhao.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import com.djzhao.utils.DBUtils;

/**
 * 数据库查询。
 * 
 * @author djzhao
 * @time 2017年2月22日 上午11:02:00
 */
public class QueryDao {
	
	/**
	 * 查询SQL。
	 * 
	 * @param sql
	 */
	public String Query(String url, String user, String password, String sql) {
		try {
			StringBuilder sb = new StringBuilder();
			DBUtils db = new DBUtils(url, user, password);
			Connection conn = db.getConn();
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				if (i != columnCount) {
					sb.append(metaData.getColumnName(i) + " | ");
				} else {
					sb.append(metaData.getColumnName(i));
				}
			}
			sb.append("\n\n");
			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					if (i != columnCount) {
						sb.append(rs.getObject(i) + " | ");
					} else {
						sb.append(rs.getObject(i));
					}
				}
				sb.append("\n\n");
			}
			return sb.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
