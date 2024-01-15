package com.example.SpingOnlineSite.Entity;

import lombok.Getter;

/**
 * The enum Order status.
 */
@Getter
public enum OrderStatus {
    /**
     * Pending order status.
     */
    PENDING("Обрабатывается"),
    /**
     * Accepted order status.
     */
    ACCEPTED("Принят"),
    /**
     * Rejected order status.
     */
    REJECTED("Отклонен");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

}
