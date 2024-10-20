package com.rs.testjava3.util;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class SqlBuilder {

    private static StringBuilder sql;

    public static <T> String generateSelectAll(Class<T> classType) {
        sql = new StringBuilder();
        sql.append("SELECT * FROM ");
        String tableName = classType.getSimpleName();
        sql.append(tableName);
        return sql.toString();
    }

    public static <T> String generateSelectByKey(Class<T> classType, String column) {
        Method[] methods = classType.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("set") && method.getName().substring(3).equals(column)) {
                sql = new StringBuilder();
                sql.append("SELECT * FROM ");
                String tableName = classType.getSimpleName();
                sql.append(tableName);
                sql.append(" WHERE ");
                sql.append(column);
                sql.append(" = ?");
                return sql.toString();
            }
        }
        throw new RuntimeException("No such column");
    }

    public static <T> String generateInsert(Class<T> classType, Method[] methods) {
        sql = new StringBuilder();
        sql.append("INSERT INTO ");
        String tableName = classType.getSimpleName();
        sql.append(tableName);
        sql.append(" ( ");
        for (Method method : methods) {
            if (method.getName().startsWith("set")) {
                String column = method.getName().substring(3);
                sql.append(column);
                sql.append(", ");
            }
        }
        sql.deleteCharAt(sql.lastIndexOf(","));
        sql.append(") VALUES ( ");
        sql.append("?, ".repeat(classType.getDeclaredFields().length));
        sql.deleteCharAt(sql.lastIndexOf(","));
        sql.append(")");
        return sql.toString();
    }

    public static <T> String generateUpdate(Class<T> classType, Method[] methods, String keyColumn) {
        sql = new StringBuilder();
        sql.append("UPDATE ");
        String tableName = classType.getSimpleName();
        sql.append(tableName);
        sql.append(" SET ");
        for (Method method : methods) {
            if (method.getName().startsWith("set") && !method.getName().substring(3).equals(keyColumn)) {
                String column = method.getName().substring(3);
                sql.append(column);
                sql.append(" = ?, ");
            }
        }
        sql.deleteCharAt(sql.lastIndexOf(","));
        sql.append("WHERE ").append(keyColumn).append(" = ?");
        return sql.toString();
    }

    public static <T> String generateDelete(Class<T> classType, String keyColumn) {
        sql = new StringBuilder();
        sql.append("DELETE FROM ").append(classType.getSimpleName()).
                append(" WHERE ").append(keyColumn).append(" = ?");
        return sql.toString();
    }
}
