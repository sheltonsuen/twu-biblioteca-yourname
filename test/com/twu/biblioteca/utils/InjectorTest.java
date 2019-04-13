package com.twu.biblioteca.utils;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.DAO.Impl.SpyPrinter;
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
        injector.setPrinter(new SpyPrinter());
        BibliotecaApp bibliotecaApp = new BibliotecaApp();

        injector.injectDependencies(bibliotecaApp);

        assertNotNull(bibliotecaApp.getPrinter());
    }
}
