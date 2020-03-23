package com.kh.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	
	// Connection 객체 생성(DB와 연결)해서 리턴해주는 메소드
	public static Connection getConnection() {
		
		Connection conn = null;
		
		Properties prop = new Properties();
		
		String fileName = JDBCTemplate.class.getResource("/sql/driver/driver.properties").getPath();	// 컴파일돼면 WebContent로 가기 때문에 classes폴더안의 경로를 찾아야한다.(src가 아닌)
		System.out.println(fileName);
		
		try {
			prop.load(new FileReader(fileName));	// 내가 지정한 driver.properties에 지정된 값들 가져오는 메소드(key, value 다 가져옴)
			
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	// 전달받은 JDBC 객체 (connection, resultset, statement) 자원 반납시켜주는 close메소드 -> 다형성적용(매개변수 다르면 메소드이름 같아도 상관없음)
	public static void close(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset) {
		
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 전달받은 Connection으로 트랜잭션 처리하는 commit, rollback 메소드
	public static void commit(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
