package com.example.SpingOnlineSite.Entity;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The type Product.
 */
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

    public Product()
    {

    }

    /**
     * Sets size.
     *
     * @param size the size
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Sets condition.
     *
     * @param condition the condition
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * Sets product type.
     *
     * @param productType the product type
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Sets user id.
     *
     * @param user_id the user id
     */
    public void setUserId(int user_id) {
        this.userId = user_id;
    }

    /**
     * Sets product id.
     *
     * @param productId the product id
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
