package com.twu.biblioteca.domain;

import lombok.Data;

@Data
public class Movie implements Describable {

    private String name;
    private Integer year;
    private String director;
    private Integer rating;
    private Boolean available;

    public Movie(String name, Integer year, String director, Integer rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
        this.available = true;
    }

    @Override
    public String briefSelfIntroduce() {
        return null;
    }
}
