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

import static com.twu.biblioteca.consts.ApplicationContant.*;

public class BibliotecaApp {
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

        loopMenuUntilQuit();
    }

    private void loopMenuUntilQuit() {
        while (true) {
            printer.print(Arrays.asList(LIST_OF_BOOKS, CHECKOUT_BOOK, RETURN_BOOK, QUIT));

            Integer optionNumber = inputService.inputMenuOptionNumber();

            if (optionNumber == 4) {
                break;
            }

            startOption(optionNumber);
        }
    }

    private void startOption(Integer optionNumber) {
        switch (optionNumber) {
            case 1:
                printBookList();
                break;
            case 2:
                checkoutBook(inputService.inputBookName());
                break;
            case 3:
                returnBook(inputService.inputBookName());
                break;
            default:
                printer.print(Collections.singletonList(INVALID_OPTION_NOTIFICATION));
                break;
        }
    }

    private void returnBook(String bookName) {
        boolean returnResult = bookService.returnBook(bookName);

        if (!returnResult) {
            printer.print(Collections.singletonList(UNSUCCESSFULLY_RETURN_BOOK));
            return;
        }

        printer.print(Collections.singletonList(SUCCESSFULLY_RETURN_BOOK));
    }

    private void checkoutBook(String bookName) {
        boolean checkoutSuccess = bookService.checkout(bookName);

        if (!checkoutSuccess) {
            printer.print(Collections.singletonList(UNSUCCESSFULLY_CHECKOUT_BOOK));
            return;
        }

        printer.print(Collections.singletonList(SUCCESSFULLY_CHECKOUT_BOOK));
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
