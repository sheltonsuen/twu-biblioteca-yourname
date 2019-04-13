package com.twu.biblioteca;

import com.twu.biblioteca.domain.Book;

import java.util.Arrays;
import java.util.List;

public class BibliotecaApp {

    private final static List<Book> BOOK_LIST = Arrays.asList(
            new Book("Romance of the Three Kingdoms", "Luo Guanzhong", 1400),
            new Book("Journey to the West Wu", "Cheng'en", 1503),
            new Book("A Dream in Red Mansions", "Cao Xueqin", 1763),
            new Book("Water Margin", "Shi Nai'an", 1370));

    public static void main(String[] args) {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Biblioteca!");

        BOOK_LIST.forEach((book) -> {
            System.out.print(book.getName());
            System.out.print("    ");
            System.out.print(book.getAuthor());
            System.out.print("    ");
            System.out.println(book.getYearOfPublished());
        });
    }
}
