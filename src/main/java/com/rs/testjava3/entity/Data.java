package com.rs.testjava3.entity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Data {
    public static <T> Object[] toInsertData(T t, Method[] methods) throws InvocationTargetException, IllegalAccessException {
        Object[] data = new Object[t.getClass().getDeclaredFields().length];
        int i = 0;
        for(Method m : methods) {
            if(m.getName().startsWith("get") && !m.getName().substring(3).equals("Class") && !m.getName().substring(3).equals("KeyName")) {
                data[i] = m.invoke(t);
//                System.out.println(m.getName()+" : "+data[i]);
                i++;
            }
        }
        return data;
    }

    public static <T> Object[] toUpdateData(T t, Method[] methods, String keyColumn) throws InvocationTargetException, IllegalAccessException {
        Object[] data = new Object[t.getClass().getDeclaredFields().length];
        int i = 0;
        for(Method m : methods) {
            if(m.getName().startsWith("get") && !m.getName().substring(3).equals(keyColumn) && !m.getName().substring(3).equals("Class") && !m.getName().substring(3).equals("KeyName")){
                data[i] = m.invoke(t);
//                System.out.println(m.getName()+" : "+data[i]);
                i++;
            } else if(m.getName().startsWith("get") && m.getName().substring(3).equals(keyColumn) && !m.getName().substring(3).equals("Class") && !m.getName().substring(3).equals("KeyName")){
                data[data.length - 1] = m.invoke(t);
//                System.out.println(m.getName()+" : "+data[i]);
            }
        }
        return data;
    }
}
