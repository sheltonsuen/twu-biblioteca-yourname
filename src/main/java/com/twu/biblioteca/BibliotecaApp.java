package com.twu.biblioteca;

import com.twu.biblioteca.service.BorrowAbleService;
import com.twu.biblioteca.service.InputService;
import com.twu.biblioteca.service.PrinterService;
import com.twu.biblioteca.service.UIService;
import com.twu.biblioteca.service.impl.BookServiceMockImpl;
import com.twu.biblioteca.service.impl.PrinterServiceCLIImpl;
import com.twu.biblioteca.service.impl.InputServiceImpl;
import com.twu.biblioteca.service.impl.UIServiceCLIImpl;
import com.twu.biblioteca.utils.Injector;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BibliotecaApp {
    private PrinterService printerService;
    private InputService inputService;
    private BorrowAbleService bookBorrowService;
    private UIService uiService;

    public static void main(String[] args) {
        Injector injector = Injector.getInstance();
        injector.setPrinterService(new PrinterServiceCLIImpl());
        injector.setInputService(new InputServiceImpl());
        injector.setBookBorrowService(new BookServiceMockImpl());
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
                uiService.showBookList(bookBorrowService.listAll());
                break;
            case 2:
                uiService.showCheckoutBookHint(bookBorrowService.checkout(inputService.inputBookName()));
                break;
            case 3:
                uiService.showReturnBookHint(bookBorrowService.returnBack(inputService.inputBookName()));
                break;
            default:
                uiService.showInvalidOptionsMessage();
                break;
        }
    }
}
