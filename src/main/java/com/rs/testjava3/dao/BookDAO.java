package com.rs.testjava3.dao;

import com.rs.testjava3.entity.Book;

import java.util.List;

public interface BookDAO {
    List<Book> findAll();

    Book findById(String id);

    void create(Book book);

    void update(Book book);

    void delete(String id);

    List<Book> search(String keyword);
}
