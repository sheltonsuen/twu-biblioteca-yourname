package com.twu.biblioteca.domain;

public class Book {

    private String name;
    private String author;
    private Integer yearOfPublished;

    public Book(String name, String author, Integer yearOfPublished) {
        this.name = name;
        this.author = author;
        this.yearOfPublished = yearOfPublished;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getYearOfPublished() {
        return yearOfPublished;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYearOfPublished(Integer yearOfPublished) {
        this.yearOfPublished = yearOfPublished;
    }
}
