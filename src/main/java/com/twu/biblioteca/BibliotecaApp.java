package com.twu.biblioteca;

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

import java.util.Collections;

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
                uiService.showBookList(borrowAbleService.listAll());
                break;
            case 2:
                uiService.showCheckoutBookHint(borrowAbleService.checkout(inputService.inputBookName()));
                break;
            case 3:
                uiService.showReturnBookHint(borrowAbleService.returnBook(inputService.inputBookName()));
                break;
            default:
                uiService.showInvalidOptionsMessage();
                break;
        }
    }
}
