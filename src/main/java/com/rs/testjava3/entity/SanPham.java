package com.rs.testjava3.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SanPham {
    private String id;
    private String name;
    private float price;
    private  String type;
}
