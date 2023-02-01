package com.iu.home.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {
	//getConnection
	public static Connection getConnnection()throws Exception {
		//1.연결정보 
		String username = "user01";
		String password = "user01";
		String url = "jdbc:oracle:thin:@192.168.1.120:1521/xe";
		//2.Driver 메모리 로딩 
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		Class.forName(driver);
		//3.DB 연결		
		Connection connection = DriverManager.getConnection(url, username, password);
		return connection;
	}

	public static void disConnect(ResultSet rs,PreparedStatement st, Connection connection) throws Exception {
		rs.close();
		st.close();
		connection.close();
		
	}
	
	public static void disConnect(PreparedStatement st, Connection connection) throws Exception {
		st.close();
		connection.close();
		
	}

//	public static void main(String[] args) throws Exception {
//		Connection con = DBConnection.getConnnection();
//		System.out.println(con != null);
//	}
}
