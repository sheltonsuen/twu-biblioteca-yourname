package com.twu.biblioteca.domain;

import lombok.Data;

import static com.twu.biblioteca.consts.ApplicationContant.BOOK_INFO_SLICER;

@Data
public class Book implements Describable {

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

    @Override
    public String briefSelfIntroduce() {
        return this.name + BOOK_INFO_SLICER + this.author + BOOK_INFO_SLICER + this.yearOfPublished;
    }
}
