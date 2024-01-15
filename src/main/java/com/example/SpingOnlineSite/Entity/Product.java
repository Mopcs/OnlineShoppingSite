package com.example.SpingOnlineSite.Entity;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "size")
    private String size;

    @Column(name = "state")
    private String condition;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "color")
    private String color;

    @Column(name = "category")
    private String category;

    @JoinColumn(name = "user_id")
    private int userId;

    public void setSize(String size) {
        this.size = size;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setUserId(int user_id) {
        this.userId = user_id;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
