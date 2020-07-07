package com.briup.environment.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
public class ConnectionFactory {
	//1.设置4个属性
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	//2.设置1个静态属性 
	private static  Connection conn;
	//3.如何对静态属性进行赋值操作？ 读取配置文件信息到属性
	static {
		//1.
		Properties properties = new Properties();
		try {
			//当name 没有路径/  分隔符 》表示文件在当前类的目录下
			properties.load(ConnectionFactory.class.getResourceAsStream("db.properties"));
			//读取properties对象进行赋值
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//2.设置静态方法
	public static Connection getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static  void close(ResultSet rs,Statement st,Connection conn) {
		try {
			if(rs != null) rs.close();
			if(st != null) st.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement st,Connection conn) {
		close(null,st,conn);
	}
}
