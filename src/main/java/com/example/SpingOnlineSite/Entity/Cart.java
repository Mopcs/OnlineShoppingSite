package com.example.SpingOnlineSite.Entity;


import lombok.Getter;

import javax.persistence.*;

/**
 * The type Cart.
 */
@Getter
@Entity
@Table(name = "cart")
public class Cart {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int cartId;

    @JoinColumn(name = "user_id")
    private int userId;


    /**
     * Sets cart id.
     *
     * @param cartId the cart id
     */
    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

}
