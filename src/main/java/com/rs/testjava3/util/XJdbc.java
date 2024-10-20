package com.rs.testjava3.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class XJdbc {
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String dburl = "jdbc:mysql://localhost:3306/sof203_bvasm";
	static String username = "root";
	static String password = "root";
	
	static {
		try { // nạp driver
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	/**Mở kết nối*/
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dburl, username, password);
	}
	/**Thao tác dữ liệu*/
	public static int executeUpdate(String sql, Object... values) throws SQLException {
		PreparedStatement stmt = XJdbc.createPreStmt(sql, values);
		return stmt.executeUpdate();
	}
	/**Truy vấn dữ liệu*/
	public static ResultSet executeQuery(String sql, Object... values) throws SQLException {
		PreparedStatement stmt = XJdbc.createPreStmt(sql, values);
		return stmt.executeQuery();
	}
	/**Tạo PreparedStatement làm việc với SQL hoặc PROC*/
	private static PreparedStatement createPreStmt(String sql, Object... values) throws SQLException {
		Connection connection = XJdbc.getConnection();
		PreparedStatement stmt = null;
		if(sql.trim().startsWith("{")) {
			stmt = connection.prepareCall(sql);
		} 
		stmt = connection.prepareStatement(sql);
		for (int i = 0; i < values.length; i++) {
			stmt.setObject(i + 1, values[i]);
		}
		return stmt;
	}

	public static void main(String[] args) {
        try {
            Connection conn = XJdbc.getConnection();
			if (conn != null ){
				System.out.println("Kết nối cơ sở dữ liệu thành công!");
			} else {
				System.out.println("Kết nối cơ sở dữ liệu thất bại!");
			}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
