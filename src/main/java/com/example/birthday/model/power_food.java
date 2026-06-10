package com.example.birthday.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "power_food")
public class power_food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "element")
    private String element;
    
    @Column(name = "image_name")
    private String image_name;

    // JPA/ThymeleafのためにGetter/Setterが必須
    public String getElement() { return element; }
    public String getImage_name() { return image_name; }
    // setterも必要に応じて追加
}