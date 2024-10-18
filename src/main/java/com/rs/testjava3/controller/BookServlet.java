package com.rs.testjava3.controller;

import com.rs.testjava3.dao.BookDAO;
import com.rs.testjava3.dao.BookDAOImpl;
import com.rs.testjava3.entity.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@WebServlet({"/book/edit/*", "/book/index", "/book/create", "/book/update", "/book/delete", "/book/search", "/book/reset"})
public class BookServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDAO dao = new BookDAOImpl();
        String path = req.getServletPath();
        String uri = req.getRequestURI();
        List<Book> listOfBook;
        Book book = new Book();

        try {
            BeanUtils.populate(book, req.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        if (path.contains("edit")) {
            String id = req.getPathInfo().substring(1);
            book = dao.findById(id);

        } else if (path.contains("create")) {
            try {
                BeanUtils.populate(book, req.getParameterMap());
                dao.create(book);
                book = new Book();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                req.setAttribute("message", "Primary key mismatch");
            }

        } else if (path.contains("update")) {
            book = dao.findById(req.getParameter("masach"));
            try {
                BeanUtils.populate(book, req.getParameterMap());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            dao.update(book);

        } else if (path.contains("delete")) {
            dao.delete(req.getParameter("masach"));
            book = new Book();

        } else if (path.contains("search")) {
            String searchQuery = req.getParameter("timKiem");
            if (searchQuery != null && !searchQuery.isEmpty()) {
                listOfBook = dao.search(searchQuery);
                req.setAttribute("list", listOfBook);
            } else {
                req.setAttribute("message", "Enter a search keyword");
                List<Book> list = dao.findAll();
                req.setAttribute("list", list);
            }
        } else {
            List<Book> list = dao.findAll();
            req.setAttribute("list", list);
        }
        req.setAttribute("item", book);
        req.getRequestDispatcher("/views/bookManager.jsp").forward(req, resp);
    }
}
