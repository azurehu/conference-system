package com.chinasofti.meeting.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
//	声明一个静态连接对象，即ConnectionFactory每次获得的连接对象都是同一个对象
	private static Connection conn=null;
	
//	声明静态方法，获得数据库连接
	public static Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/meeting?useUnicode=true&characterEncoding=utf8","root","root");
			System.out.println("Connection Success!");
		} catch (ClassNotFoundException e) {
				e.printStackTrace();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return conn;
	}
	
//	声明静态方法，关闭数据库连接
	public static void closeConnection(){
		if(conn!=null){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	public static void main(String[] args) {
		ConnectionFactory.getConnection();
	}
}
