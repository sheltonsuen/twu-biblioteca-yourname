package com.twu.biblioteca;

import com.twu.biblioteca.domain.Describable;
import com.twu.biblioteca.service.BorrowAbleService;
import com.twu.biblioteca.service.InputService;
import com.twu.biblioteca.service.Printer;
import com.twu.biblioteca.service.UIService;
import com.twu.biblioteca.service.impl.BookServiceMockImpl;
import com.twu.biblioteca.service.impl.CLIPrinter;
import com.twu.biblioteca.service.impl.InputServiceImpl;
import com.twu.biblioteca.service.impl.UIServiceCLIImpl;
import com.twu.biblioteca.utils.Injector;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.twu.biblioteca.consts.ApplicationContant.*;

@Setter
@Getter
public class BibliotecaApp {
    private Printer printer;
    private InputService inputService;
    private BorrowAbleService borrowAbleService;
    private UIService uiService;

    public static void main(String[] args) {
        Injector injector = Injector.getInstance();
        injector.setPrinter(new CLIPrinter());
        injector.setInputService(new InputServiceImpl());
        injector.setBorrowAbleService(new BookServiceMockImpl());
        injector.setUiService(new UIServiceCLIImpl());

        BibliotecaApp bibliotecaApp = new BibliotecaApp();

        injector.injectDependencies(bibliotecaApp);

        bibliotecaApp.run();
    }

    void run() {
        uiService.showWelcomeMessage();

        loopMenuUntilQuit();
    }

    private void loopMenuUntilQuit() {
        while (true) {
            uiService.showMenuOptions();

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
                uiService.showInvalidOptionsMessage();
                break;
        }
    }

    private void returnBook(String bookName) {
        boolean returnResult = borrowAbleService.returnBook(bookName);

        if (!returnResult) {
            printer.print(Collections.singletonList(UNSUCCESSFULLY_RETURN_BOOK));
            return;
        }

        printer.print(Collections.singletonList(SUCCESSFULLY_RETURN_BOOK));
    }

    private void checkoutBook(String bookName) {
        boolean checkoutSuccess = borrowAbleService.checkout(bookName);

        if (!checkoutSuccess) {
            printer.print(Collections.singletonList(UNSUCCESSFULLY_CHECKOUT_BOOK));
            return;
        }

        printer.print(Collections.singletonList(SUCCESSFULLY_CHECKOUT_BOOK));
    }

    private void printBookList() {
        List<String> bookList = borrowAbleService
                .listAll()
                .stream()
                .map(Describable::briefSelfIntroduce)
                .collect(Collectors.toList());

        printer.print(bookList);
    }
}
