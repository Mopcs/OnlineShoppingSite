package com.example.SpingOnlineSite.Request;

import lombok.Getter;

@Getter
public class LoginRequest {

    private String phoneNumber;
    private String password;

    public void setNumber(String number) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}