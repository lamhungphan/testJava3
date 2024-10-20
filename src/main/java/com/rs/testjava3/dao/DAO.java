package com.rs.testjava3.dao;

import com.rs.testjava3.entity.Data;
import com.rs.testjava3.util.SqlBuilder;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class DAO<T> {

    public void getAll(Class<T> classType) {
        String sql = SqlBuilder.generateSelectAll(classType);
        System.out.println(sql);

//        XJdbc.execute(sql);  //Bỏ vào XJdbc
    }

    public void getByKey(Class<T> classType, String keyColumn, Object key) {
        String sql = SqlBuilder.generateSelectByKey(classType, keyColumn);
        System.out.println(sql);
        System.out.println(key.toString());

//        XJdbc.execute(sql, key);  //Bỏ vào XJdbc
    }

    public void add(T t) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = t.getClass().getMethods();
        Arrays.sort(methods, (m1, m2) -> m1.getName().compareTo(m2.getName()));
        String sql = SqlBuilder.generateInsert(t.getClass() , methods);
        System.out.println(sql);
        Object[] data = Data.toInsertData(t, methods);
        System.out.println(Arrays.toString(data));

//        XJdbc.execute(sql, data);  //Bỏ vào XJdbc
    }

    public void update(T t, String keyColumn) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = t.getClass().getMethods();
        Arrays.sort(methods, (m1, m2) -> m1.getName().compareTo(m2.getName()));
        String sql = SqlBuilder.generateUpdate(t.getClass() , methods, keyColumn);
        System.out.println(sql);
        Object[] data = Data.toUpdateData(t, methods, keyColumn);
        System.out.println(Arrays.toString(data));

//        XJdbc.execute(sql, data);  //Bỏ vào XJdbc
    }

    public void delete(Class<T> classType, String keyColumn, Object key) throws InvocationTargetException, IllegalAccessException {
        String sql = SqlBuilder.generateDelete(classType, keyColumn);
        System.out.println(sql);
        System.out.println(key.toString());

//        XJdbc.execute(sql, key);  //Bỏ vào XJdbc
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        DAO dao = new DAO();
//        User user = new User("user001","1234","admin","Linh","Nguyen","example@gmail.com");
//        dao.getAll(User.class);
//        dao.getByKey(User.class, user.getKeyName(), user.getUsername());
//        dao.add(user);
//        dao.update(user, user.getKeyName());
//        dao.delete(User.class, user.getKeyName(), user.getUsername());

        /*
        * Tạo entity phải có thêm một hàm String getKeyName(){return "<Tên cột của khóa chính>"}
        * Tên các field phải trùng với database
        * Tên lớp phải trùng tên bảng trong database

         */

    }
}
