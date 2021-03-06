package com.twu.biblioteca.service.impl;

import com.twu.biblioteca.domain.Describable;
import com.twu.biblioteca.service.BorrowAbleService;
import com.twu.biblioteca.domain.Book;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookServiceMockImpl implements BorrowAbleService {

    private List<Book> bookList = Arrays.asList(
            new Book("Romance of the Three Kingdoms", "Luo Guanzhong", 1400),
            new Book("Journey to the West Wu", "Cheng'en", 1503),
            new Book("A Dream in Red Mansions", "Cao Xueqin", 1763),
            new Book("Water Margin", "Shi Nai'an", 1370));

    @Override
    public List<Describable> listAll() {
        return bookList.stream().filter(Book::getAvailable).collect(Collectors.toList());
    }

    @Override
    public boolean checkout(String name) {
        for (Book book : bookList) {
            if (book.getName().equals(name)) {
                book.setAvailable(false);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean returnBack(String name) {
        for (Book book : bookList) {
            if (book.getName().equals(name)) {
                book.setAvailable(true);
                return true;
            }
        }

        return false;
    }
}
