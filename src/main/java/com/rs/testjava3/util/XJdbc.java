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

	// Khối static: Chỉ chạy một lần khi lớp được nạp vào bộ nhớ (khi ứng dụng khởi động)
	static {
		try { // Nạp driver JDBC cho MySQL vào bộ nhớ
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e); // Ném ngoại lệ nếu không tìm thấy driver
		}
	}

	/** Mở kết nối tới cơ sở dữ liệu */
	public static Connection getConnection() throws SQLException {
		// Trả về một đối tượng Connection thông qua DriverManager
		return DriverManager.getConnection(dburl, username, password);
	}

	/**Thao tác dữ liệu (INSERT, UPDATE, DELETE) */
	public static int executeUpdate(String sql, Object... values) throws SQLException {
		// Tạo PreparedStatement từ câu SQL và các giá trị truyền vào
		PreparedStatement stmt = XJdbc.createPreStmt(sql, values);
		// Thực thi câu lệnh SQL và trả về số dòng bị ảnh hưởng
		return stmt.executeUpdate();
	}

	/**Truy vấn dữ liệu*/
	public static ResultSet executeQuery(String sql, Object... values) throws SQLException {
		// Tạo PreparedStatement từ câu SQL và các giá trị truyền vào
		PreparedStatement stmt = XJdbc.createPreStmt(sql, values);
		// Thực thi câu lệnh SQL và trả về đối tượng ResultSet chứa kết quả
		return stmt.executeQuery();
	}

	/**Tạo PreparedStatement làm việc với SQL hoặc PROC*/
	private static PreparedStatement createPreStmt(String sql, Object... values) throws SQLException {
		Connection connection = XJdbc.getConnection();
		PreparedStatement stmt = null;

		// Kiểm tra nếu câu SQL là thủ tục lưu trữ (bắt đầu bằng ký tự "{")
		if (sql.trim().startsWith("{")) {
			stmt = connection.prepareCall(sql); // Tạo CallableStatement nếu là thủ tục
		} else {
			stmt = connection.prepareStatement(sql); // Tạo PreparedStatement cho câu lệnh SQL thường
		}
		// Gán các giá trị tham số cho câu SQL (tương ứng với các dấu "?")
		for (int i = 0; i < values.length; i++) {
			stmt.setObject(i + 1, values[i]); // Đặt giá trị cho từng tham số
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
