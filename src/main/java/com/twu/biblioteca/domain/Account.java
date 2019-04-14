package com.twu.biblioteca.domain;

import lombok.Data;

@Data
public class Account {
    private String name;
    private String email;
    private String phoneNumber;

    private String password;
    private String libraryNumber;

    public Account(String name, String email, String phoneNumber, String libraryNumber, String password) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.libraryNumber = libraryNumber;
        this.password = password;
    }
}
