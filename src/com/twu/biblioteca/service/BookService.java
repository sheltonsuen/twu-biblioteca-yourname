package com.twu.biblioteca.service;

import com.twu.biblioteca.domain.Book;

import java.util.List;

public interface BookService {

    List<Book> listAll();

    boolean checkout(String name);

    boolean returnBook(String name);
}
