package com.example.SpingOnlineSite.Entity;

import lombok.Getter;

@Getter
public enum OrderStatus {
    NEW("Новый"),
    PROCESSING("Обрабатывается"),
    COMPLETED("Завершен"),
    CANCELED("Отменен");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

}
