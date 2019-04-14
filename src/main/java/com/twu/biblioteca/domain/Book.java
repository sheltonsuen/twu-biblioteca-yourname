package com.twu.biblioteca.domain;

import com.twu.biblioteca.utils.StringUtils;
import lombok.Data;

import static com.twu.biblioteca.consts.ApplicationConstant.INFO_SLICER;
import static com.twu.biblioteca.consts.ApplicationConstant.LANDSCAPE_LACE;

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
        return LANDSCAPE_LACE +
                INFO_SLICER + StringUtils.smooth(this.name, 32) +
                INFO_SLICER + StringUtils.smooth(this.author, 16) +
                INFO_SLICER + this.yearOfPublished +
                INFO_SLICER + LANDSCAPE_LACE;
    }
}
