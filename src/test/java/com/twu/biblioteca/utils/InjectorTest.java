package com.twu.biblioteca.utils;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.service.impl.*;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class InjectorTest {

    @Test
    public void should_return_the_same_instance_of_Injector_when_get_it_multiple_times() {
        Injector firstInjector = Injector.getInstance();
        Injector secondInjector = Injector.getInstance();

        assertEquals("Injector", firstInjector, secondInjector);
    }

    @Test
    public void should_inject_CLI_printer_to_biblioteca_app() {
        Injector injector = Injector.getInstance();
        injector.setPrinterService(new SpyPrinterService());
        BibliotecaApp bibliotecaApp = new BibliotecaApp();

        injector.injectDependencies(bibliotecaApp);

        assertNotNull(bibliotecaApp.getPrinterService());
    }

    @Test
    public void should_inject_input_service_to_biblioteca_app() {
        Injector injector = Injector.getInstance();
        injector.setPrinterService(new SpyPrinterService());
        injector.setInputService(new InputServiceImpl());
        BibliotecaApp bibliotecaApp = new BibliotecaApp();

        injector.injectDependencies(bibliotecaApp);

        assertNotNull(bibliotecaApp.getInputService());
    }

    @Test
    public void should_inject_book_service_to_biblioteca_app() {
        Injector injector = Injector.getInstance();
        injector.setPrinterService(new SpyPrinterService());
        injector.setInputService(new InputServiceImpl());
        injector.setBookBorrowService(new BookServiceMockImpl());
        BibliotecaApp bibliotecaApp = new BibliotecaApp();

        injector.injectDependencies(bibliotecaApp);

        assertNotNull(bibliotecaApp.getBookBorrowService());
    }

    @Test
    public void should_inject_printer_dependency_to_UI_service() {
        Injector injector = Injector.getInstance();
        injector.setPrinterService(new SpyPrinterService());
        injector.setInputService(new InputServiceImpl());
        injector.setBookBorrowService(new BookServiceMockImpl());
        UIServiceCLIImpl uiServiceCLI = new UIServiceCLIImpl();
        injector.setUiService(uiServiceCLI);
        BibliotecaApp bibliotecaApp = new BibliotecaApp();

        injector.injectDependencies(bibliotecaApp);

        assertNotNull(bibliotecaApp.getUiService());
        assertNotNull(uiServiceCLI.getPrinterService());
    }
}
