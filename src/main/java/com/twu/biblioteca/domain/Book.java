package com.twu.biblioteca.domain;

import com.twu.biblioteca.utils.StringUtils;
import lombok.Data;

import static com.twu.biblioteca.consts.ApplicationContant.BOOK_INFO_SLICER;
import static com.twu.biblioteca.consts.ApplicationContant.BOOK_LACE;

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
        return BOOK_LACE +
                BOOK_INFO_SLICER +
                StringUtils.smooth(this.name, 32) +
                BOOK_INFO_SLICER +
                StringUtils.smooth(this.author, 16) +
                BOOK_INFO_SLICER +
                this.yearOfPublished +
                BOOK_INFO_SLICER +
                BOOK_LACE;
    }
}
