package com.twu.biblioteca;

import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.InputService;
import com.twu.biblioteca.service.Printer;
import com.twu.biblioteca.service.impl.BookServiceMockImpl;
import com.twu.biblioteca.service.impl.CLIPrinter;
import com.twu.biblioteca.service.impl.InputServiceImpl;
import com.twu.biblioteca.utils.Injector;

import java.util.Collections;
import java.util.stream.Collectors;

public class BibliotecaApp {

    private static final String LIST_OF_BOOKS = "List of books";
    private static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Biblioteca!";

    private Printer printer;
    private InputService inputService;
    private BookService bookService;

    public static void main(String[] args) {
        Injector injector = Injector.getInstance();
        injector.setPrinter(new CLIPrinter());
        injector.setInputService(new InputServiceImpl());
        injector.setBookService(new BookServiceMockImpl());

        BibliotecaApp bibliotecaApp = new BibliotecaApp();

        injector.injectDependencies(bibliotecaApp);

        bibliotecaApp.run();
    }

    void run() {

        printer.print(Collections.singletonList(WELCOME_MESSAGE));

        printer.print(Collections.singletonList(LIST_OF_BOOKS));

        Integer optionNumber = inputService.inputMenuOptionNumber();

        if (optionNumber == 1) {
            printer.print(
                    bookService
                            .listAll()
                            .stream()
                            .map(book -> book.getName() + "    " + book.getAuthor() + "    " + book.getYearOfPublished())
                            .collect(Collectors.toList()));
        }
    }

    public Printer getPrinter() {
        return printer;
    }

    public InputService getInputService() {
        return inputService;
    }

    public BookService getBookService() {
        return bookService;
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
