package com.example.SpingOnlineSite.Entity;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PENDING("Обрабатывается"),
    ACCEPTED("Принят"),
    REJECTED("Отклонен");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

}
