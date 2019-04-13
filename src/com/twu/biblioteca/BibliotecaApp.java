package com.twu.biblioteca;

import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.impl.BookServiceMockImpl;

public class BibliotecaApp {

    public static void main(String[] args) {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Biblioteca!");

        BookService bookService = new BookServiceMockImpl();

        bookService.listAll().forEach((book) -> {
            System.out.print(book.getName());
            System.out.print("    ");
            System.out.print(book.getAuthor());
            System.out.print("    ");
            System.out.println(book.getYearOfPublished());
        });
    }
}
