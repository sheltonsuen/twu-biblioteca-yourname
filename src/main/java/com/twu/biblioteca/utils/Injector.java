package com.twu.biblioteca.utils;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.service.BorrowAbleService;
import com.twu.biblioteca.service.InputService;
import com.twu.biblioteca.service.Printer;
import com.twu.biblioteca.service.UIService;
import com.twu.biblioteca.service.impl.UIServiceCLIImpl;
import lombok.Setter;

@Setter
public class Injector {

    private static Injector instance;

    private Printer printer;
    private InputService inputService;
    private BorrowAbleService borrowAbleService;
    private UIServiceCLIImpl uiService;

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
        bibliotecaApp.setBorrowAbleService(this.borrowAbleService);
        bibliotecaApp.setUiService(this.uiService);
        this.uiService.setPrinter(this.printer);
    }
}
