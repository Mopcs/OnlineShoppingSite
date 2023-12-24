package com.example.SpingOnlineSite.Entity;


import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @JoinColumn(name = "product_id")
    private int productId;

    @Column(name = "quantity")
    private int quantity;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
