package com.example.SpingOnlineSite.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * The type Cart item.
 */
@Getter
@Setter
@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @JoinColumn(name = "product_id")
    private int productId;

    @Column(name = "quantity")
    private int quantity;

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
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
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
