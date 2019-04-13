package com.twu.biblioteca.domain;

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

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getYearOfPublished() {
        return yearOfPublished;
    }

    public Boolean getAvailable() {
        return available;
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

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
