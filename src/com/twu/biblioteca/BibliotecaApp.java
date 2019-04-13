package com.twu.biblioteca;

import com.twu.biblioteca.service.Printer;
import com.twu.biblioteca.service.impl.CLIPrinter;
import com.twu.biblioteca.utils.Injector;

import java.util.Collections;

public class BibliotecaApp {

    private static final String LIST_OF_BOOKS = "List of books";
    private static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Biblioteca!";

    private Printer printer;

    public static void main(String[] args) {
        Injector injector = Injector.getInstance();
        injector.setPrinter(new CLIPrinter());

        BibliotecaApp bibliotecaApp = new BibliotecaApp();

        injector.injectDependencies(bibliotecaApp);

        bibliotecaApp.run();
    }

    void run() {

        printer.print(Collections.singletonList(WELCOME_MESSAGE));

        printer.print(Collections.singletonList(LIST_OF_BOOKS));
    }

    public Printer getPrinter() {
        return printer;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }
}
