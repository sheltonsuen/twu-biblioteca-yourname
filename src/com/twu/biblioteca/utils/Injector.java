package com.twu.biblioteca.utils;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.service.Printer;

public class Injector {

    private static Injector instance;

    private Printer printer;

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
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }
}
