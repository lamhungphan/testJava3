package com.rs.testjava3.controller;

import com.rs.testjava3.dao.SanPhamDAO;
import com.rs.testjava3.entity.SanPham;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@WebServlet({"/product/edit/*", "/product/index", "/product/create", "/product/update", "/product/delete", "/product/filter", "/product/reset"})
public class SanPhamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SanPham sanPham = new SanPham();

        try {
            BeanUtils.populate(sanPham, req.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        String path = req.getServletPath();
        SanPhamDAO dao = new SanPhamDAO();
        List<SanPham> listOfProduct = null;

        if (path.contains("edit")) {
            String id = req.getPathInfo().substring(1);
            sanPham = dao.findById(id);
        } else if (path.contains("create")) {
            try {
                dao.create(sanPham);
                sanPham = new SanPham();
            } catch (RuntimeException e) {
                req.setAttribute("message", "Trùng khóa chính");
            }
        } else if (path.contains("update")) {
            dao.update(sanPham);
        } else if (path.contains("delete")) {
            dao.delete(sanPham.getId());
            sanPham = new SanPham();
        } else if (path.contains("reset")) {
            sanPham = new SanPham();
        } else if (path.contains("filter")) {
            String selectedType = req.getParameter("loai");

            if (selectedType != null && !selectedType.equalsIgnoreCase("Tatca")) {
                listOfProduct = dao.filterByType(selectedType);
            } else {
                listOfProduct = dao.findAll();
            }
            req.setAttribute("list", listOfProduct);
        }

        if (listOfProduct == null) {
            listOfProduct = dao.findAll();
        }
        req.setAttribute("item", sanPham);
        req.setAttribute("list", listOfProduct);
        req.getRequestDispatcher("/views/sanPhamManager.jsp").forward(req, resp);
    }
}
