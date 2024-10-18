package com.rs.testjava3.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private String masach;
    private String tensach;
    private float gia;
    private int namxb;
    private String loai;

    public Object[] toInsertData() {
        return new Object[]{masach, tensach, gia, namxb, loai};
    }

    public Object[] toUpdateData() {
        return new Object[]{tensach, gia, namxb, loai, masach};
    }
}
