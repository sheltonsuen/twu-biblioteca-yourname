package com.twu.biblioteca.domain;

import com.twu.biblioteca.utils.StringUtils;
import lombok.Data;

import static com.twu.biblioteca.consts.ApplicationConstant.INFO_SLICER;
import static com.twu.biblioteca.consts.ApplicationConstant.LANDSCAPE_LACE;

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
        return LANDSCAPE_LACE +
                INFO_SLICER + StringUtils.smooth(this.name, 32) +
                INFO_SLICER + this.year +
                INFO_SLICER + StringUtils.smooth(this.director, 16) +
                INFO_SLICER + this.rating +
                INFO_SLICER + LANDSCAPE_LACE;
    }
}
