package com.twu.biblioteca.utils;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.InputService;
import com.twu.biblioteca.service.Printer;

public class Injector {

    private static Injector instance;

    private Printer printer;
    private InputService inputService;
    private BookService bookService;

    private Injector() {
    }

    public static Injector getInstance() {
        if (instance == null) {
            instance = new Injector();
        }

        return instance;
    }

    public void injectDependencies(BibliotecaApp bibliotecaApp) {
        bibliotecaApp.setPrinter(this.printer);
        bibliotecaApp.setInputService(this.inputService);
        bibliotecaApp.setBookService(this.bookService);
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    public void setInputService(InputService inputService) {
        this.inputService = inputService;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
}
