package com.twu.biblioteca;

import com.twu.biblioteca.service.Printer;
import com.twu.biblioteca.service.impl.CLIPrinter;

import java.util.Collections;

public class BibliotecaApp {

    public static void main(String[] args) {
        Printer printer = new CLIPrinter();

        printer.print(Collections.singletonList("Welcome to Biblioteca. Your one-stop-shop for great book titles in Biblioteca!"));

        printer.print(Collections.singletonList("List of books"));
    }
}
