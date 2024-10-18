package com.rs.testjava3.dao;

import com.rs.testjava3.entity.Book;
import com.rs.testjava3.util.Jdbc;

import java.sql.SQLException;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    @Override
    public List<Book> findAll() {
        String sql = "SELECT * FROM sach";
        return Jdbc.getResultList(Book.class, sql);
    }

    @Override
    public Book findById(String id) {
        String sql = "SELECT * FROM sach WHERE Masach = ?";
        try {
            return Jdbc.getSingleResult(Book.class, sql, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Book book) {
        String sql = "INSERT INTO sach VALUES (?, ?, ?, ?, ?)";
        try {
            Jdbc.IUD(sql, book.toInsertData());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Book book) {
        String sql = "UPDATE sach SET Tensach = ?, Gia = ?, Namxb = ?, Loai = ? WHERE Masach = ?";
        try {
            Jdbc.IUD(sql, book.toUpdateData());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM sach WHERE Masach = ?";
        try {
            Jdbc.IUD(sql, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> search(String keyword) {
        String sql = "SELECT * FROM sach WHERE Tensach LIKE ? OR Gia LIKE ? OR Namxb LIKE ? OR Loai LIKE ? OR Masach LIKE ?";
        return Jdbc.getResultList(Book.class, sql, "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
    }

    public static void main(String[] args) {
        BookDAOImpl dao = new BookDAOImpl();
        System.out.println(dao.findAll().toString());
    }
}
