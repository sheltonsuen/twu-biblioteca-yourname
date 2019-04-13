package com.twu.biblioteca.service.impl;

import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.domain.Book;

import java.util.Arrays;
import java.util.List;

public class BookServiceMockImpl implements BookService {

    private final static List<Book> BOOK_LIST = Arrays.asList(
            new Book("Romance of the Three Kingdoms", "Luo Guanzhong", 1400),
            new Book("Journey to the West Wu", "Cheng'en", 1503),
            new Book("A Dream in Red Mansions", "Cao Xueqin", 1763),
            new Book("Water Margin", "Shi Nai'an", 1370));

    @Override
    public List<Book> listAll() {
        return BOOK_LIST;
    }
}
