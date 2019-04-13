package com.twu.biblioteca;

import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.InputService;
import com.twu.biblioteca.service.Printer;
import com.twu.biblioteca.service.impl.BookServiceMockImpl;
import com.twu.biblioteca.service.impl.CLIPrinter;
import com.twu.biblioteca.service.impl.InputServiceImpl;
import com.twu.biblioteca.utils.Injector;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BibliotecaApp {

    private static final String LIST_OF_BOOKS = "List of books";
    private static final String QUIT = "Quit";
    private static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Biblioteca!";
    private static final String BOOK_INFO_SLICER = "    ";
    private static final String INVALID_OPTION_NOTIFICATION = "Please select a valid option!";

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

        printer.print(Arrays.asList(LIST_OF_BOOKS, QUIT));

        Integer optionNumber = inputService.inputMenuOptionNumber();

        startMenu(optionNumber);
    }

    private void startMenu(Integer optionNumber) {
        if (optionNumber == 1) {
            printBookList();
            return;
        }

        printer.print(Collections.singletonList(INVALID_OPTION_NOTIFICATION));
    }

    private void printBookList() {
        List<String> bookList = bookService
                .listAll()
                .stream()
                .map(book -> book.getName() + BOOK_INFO_SLICER + book.getAuthor() + BOOK_INFO_SLICER + book.getYearOfPublished())
                .collect(Collectors.toList());

        printer.print(bookList);
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
