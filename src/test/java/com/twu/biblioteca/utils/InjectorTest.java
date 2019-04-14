package com.twu.biblioteca.utils;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.service.impl.*;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class InjectorTest {

    private UIServiceCLIImpl uiServiceCLI;
    private SecurityServiceImpl securityService;

    @Test
    public void should_return_the_same_instance_of_Injector_when_get_it_multiple_times() {
        Injector firstInjector = Injector.getInstance();
        Injector secondInjector = Injector.getInstance();

        assertEquals("Injector", firstInjector, secondInjector);
    }

    @Test
    public void should_inject_CLI_printer_to_biblioteca_app() {
        withInjectableService();
        BibliotecaApp bibliotecaApp = new BibliotecaApp();

        Injector.getInstance().injectDependencies(bibliotecaApp);

        assertNotNull(bibliotecaApp.getPrinterService());
    }

    @Test
    public void should_inject_input_service_to_biblioteca_app() {
        withInjectableService();
        BibliotecaApp bibliotecaApp = new BibliotecaApp();

        Injector.getInstance().injectDependencies(bibliotecaApp);

        assertNotNull(bibliotecaApp.getInputService());
    }

    @Test
    public void should_inject_book_service_to_biblioteca_app() {
        withInjectableService();
        BibliotecaApp bibliotecaApp = new BibliotecaApp();

        Injector.getInstance().injectDependencies(bibliotecaApp);

        assertNotNull(bibliotecaApp.getBookBorrowService());
    }

    @Test
    public void should_inject_printer_service_to_UI_service() {
        withInjectableService();
        BibliotecaApp bibliotecaApp = new BibliotecaApp();

        Injector.getInstance().injectDependencies(bibliotecaApp);

        assertNotNull(bibliotecaApp.getUiService());
        assertNotNull(uiServiceCLI.getPrinterService());
    }

    @Test
    public void should_inject_movie_borrow_service_to_bibliotec_app() {
        withInjectableService();
        BibliotecaApp bibliotecaApp = new BibliotecaApp();

        Injector.getInstance().injectDependencies(bibliotecaApp);

        assertNotNull(bibliotecaApp.getMovieBorrowService());
    }

    @Test
    public void should_inject_security_service_to_bibliotec_app() {
        withInjectableService();

        BibliotecaApp bibliotecaApp = new BibliotecaApp();

        Injector.getInstance().injectDependencies(bibliotecaApp);

        assertNotNull(bibliotecaApp.getSecurityService());
        assertNotNull(securityService.getInputService());
        assertNotNull(securityService.getPrinterService());
    }

    @Test
    public void should_inject_security_service_to_UI_service() {
        withInjectableService();
        BibliotecaApp bibliotecaApp = new BibliotecaApp();

        Injector.getInstance().injectDependencies(bibliotecaApp);

        assertNotNull(uiServiceCLI.getSecurityService());
    }

    private void withInjectableService() {
        Injector injector = Injector.getInstance();
        injector.setPrinterService(new SpyPrinterService());
        injector.setInputService(new InputServiceImpl());
        injector.setBookBorrowService(new BookServiceMockImpl());
        injector.setMovieBorrowService(new MovieServiceMockImpl());

        uiServiceCLI = new UIServiceCLIImpl();
        securityService = new SecurityServiceImpl();

        injector.setSecurityService(securityService);
        injector.setUiService(uiServiceCLI);
    }
}
