package com.twu.biblioteca;

import java.util.Arrays;
import java.util.List;

public class BibliotecaApp {

    private final static List<String> BOOK_LIST = Arrays.asList(
            "Romance of the Three Kingdoms",
            "Journey to the West",
            "A Dream in Red Mansions",
            "Water Margin");

    public static void main(String[] args) {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Biblioteca!");

        BOOK_LIST.forEach(System.out::println);
    }
}
