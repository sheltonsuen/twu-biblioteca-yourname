package com.twu.biblioteca;

import com.twu.biblioteca.service.BorrowAbleService;
import com.twu.biblioteca.service.InputService;
import com.twu.biblioteca.service.PrinterService;
import com.twu.biblioteca.service.UIService;
import com.twu.biblioteca.service.impl.*;
import com.twu.biblioteca.utils.Injector;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BibliotecaApp {
    private PrinterService printerService;
    private InputService inputService;
    private UIService uiService;
    private BorrowAbleService bookBorrowService;
    private BorrowAbleService movieBorrowService;

    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();

        injectDependenciesTo(bibliotecaApp);

        bibliotecaApp.run();
    }

    private static void injectDependenciesTo(BibliotecaApp bibliotecaApp) {
        Injector injector = Injector.getInstance();
        injector.setPrinterService(new PrinterServiceCLIImpl());
        injector.setInputService(new InputServiceImpl());
        injector.setUiService(new UIServiceCLIImpl());
        injector.setBookBorrowService(new BookServiceMockImpl());
        injector.setMovieBorrowService(new MovieServiceMockImpl());

        injector.injectDependencies(bibliotecaApp);
    }

    void run() {
        uiService.showWelcomeMessage();

        loopMenuUntilQuit();
    }

    private void loopMenuUntilQuit() {
        while (true) {
            uiService.showMenuOptions();

            Integer optionNumber = inputService.inputMenuOptionNumber();

            if (optionNumber == 6) {
                break;
            }

            startOption(optionNumber);
        }
    }

    private void startOption(Integer optionNumber) {
        switch (optionNumber) {
            case 1:
                uiService.showBookList(bookBorrowService.listAll());
                break;
            case 2:
                uiService.showCheckoutBookHint(bookBorrowService.checkout(inputService.inputNextLineAsString()));
                break;
            case 3:
                uiService.showReturnBookHint(bookBorrowService.returnBack(inputService.inputNextLineAsString()));
                break;
            case 4:
                uiService.showMovieList(movieBorrowService.listAll());
                break;
            case 5:
                uiService.showCheckoutMovieHint(movieBorrowService.checkout(inputService.inputNextLineAsString()));
                break;
            default:
                uiService.showInvalidOptionsMessage();
                break;
        }
    }
}
