package com.rs.testjava3.dao;

import com.rs.testjava3.entity.SanPham;
import com.rs.testjava3.util.XJdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {
    public List<SanPham> findAll() {
        String sql = "SELECT * FROM sanpham";
        try {
            List<SanPham> entities = new ArrayList<>();
            Object[] values = {};
            ResultSet resultSet = XJdbc.executeQuery(sql, values);
            while (resultSet.next()) {
                SanPham entity = new SanPham();
                entity.setId(resultSet.getString("masp"));
                entity.setName(resultSet.getString("tensp"));
                entity.setPrice(resultSet.getFloat("gia"));
                entity.setType(resultSet.getString("loaisp"));
                entities.add(entity);
            }
            return entities;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SanPham findById(String id) {
        String sql = "SELECT * FROM sanpham WHERE masp = ?";
        try {
            Object[] values = {id};
            ResultSet resultSet = XJdbc.executeQuery(sql, values);
            if (resultSet.next()) {
                SanPham entity = new SanPham();
                entity.setId(resultSet.getString("masp"));
                entity.setName(resultSet.getString("tensp"));
                entity.setPrice(resultSet.getFloat("gia"));
                entity.setType(resultSet.getString("loaisp"));
                return entity;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Not found");
    }

    public void create(SanPham entity) {
        String sql = "INSERT INTO sanpham VALUES (?, ?, ?, ?)";
        try {
            Object[] values = {
                    entity.getId(),
                    entity.getName(),
                    entity.getPrice(),
                    entity.getType()
            };
            XJdbc.executeUpdate(sql, values);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update(SanPham entity) {
        String sql = "UPDATE sanpham SET tensp = ?, gia = ?, loaisp = ? WHERE masp = ?";
        try {
            Object[] values = {
                    entity.getName(),
                    entity.getPrice(),
                    entity.getType(),
                    entity.getId()
            };
            XJdbc.executeUpdate(sql, values);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String id) {
        String sql = "DELETE FROM sanpham WHERE masp = ?";
        try {
            Object[] values = {id};
            XJdbc.executeUpdate(sql, values);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<SanPham> filterByType(String loai) {
        String sql = "SELECT * FROM sanpham WHERE loaisp = ?";
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, loai);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getString("masp"));
                sp.setName(rs.getString("tensp"));
                sp.setPrice(rs.getFloat("gia"));
                sp.setType(rs.getString("loaisp"));
                list.add(sp);
            }
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
