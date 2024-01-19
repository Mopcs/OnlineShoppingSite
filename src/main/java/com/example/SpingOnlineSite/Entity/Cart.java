package com.example.SpingOnlineSite.Entity;


import lombok.Getter;

import javax.persistence.*;

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

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
