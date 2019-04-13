package com.twu.biblioteca.domain;

import lombok.Data;

@Data
public class Book {

    private String name;
    private String author;
    private Integer yearOfPublished;
    private Boolean available;

    public Book(String name, String author, Integer yearOfPublished) {
        this.name = name;
        this.author = author;
        this.yearOfPublished = yearOfPublished;
        this.available = true;
    }
}
