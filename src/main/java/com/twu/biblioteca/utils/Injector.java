package com.twu.biblioteca.utils;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.service.BorrowAbleService;
import com.twu.biblioteca.service.InputService;
import com.twu.biblioteca.service.PrinterService;
import com.twu.biblioteca.service.SecurityService;
import com.twu.biblioteca.service.impl.SecurityServiceImpl;
import com.twu.biblioteca.service.impl.UIServiceCLIImpl;
import lombok.Setter;

@Setter
public class Injector {

    private static Injector instance;

    private PrinterService printerService;
    private InputService inputService;
    private UIServiceCLIImpl uiService;
    private BorrowAbleService bookBorrowService;
    private BorrowAbleService movieBorrowService;
    private SecurityServiceImpl securityService;

    private Injector() {
    }

    public static Injector getInstance() {
        if (instance == null) {
            instance = new Injector();
        }

        return instance;
    }

    public void injectDependencies(BibliotecaApp bibliotecaApp) {
        bibliotecaApp.setPrinterService(this.printerService);
        bibliotecaApp.setInputService(this.inputService);
        bibliotecaApp.setUiService(this.uiService);
        bibliotecaApp.setBookBorrowService(this.bookBorrowService);
        bibliotecaApp.setMovieBorrowService(this.movieBorrowService);

        this.uiService.setPrinterService(this.printerService);

        this.securityService.setInputService(this.inputService);
        this.securityService.setPrinterService(this.printerService);
        bibliotecaApp.setSecurityService(this.securityService);
    }
}
