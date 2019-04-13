package com.twu.biblioteca;

import com.twu.biblioteca.service.Impl.SpyPrinter;
import com.twu.biblioteca.utils.Injector;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class BibliotecaAppTest {

    private BibliotecaApp bibliotecaApp = new BibliotecaApp();
    private SpyPrinter spyPrinter = new SpyPrinter();

    @Before
    public void setup() {
        Injector.getInstance().setPrinter(spyPrinter);

        Injector.getInstance().injectDependencies(bibliotecaApp);
    }

    @Test
    public void should_see_welcome_message_when_start_the_application() {
        bibliotecaApp.run();

        String actualResult = spyPrinter.getPrintCalls().get(0).get(0);

        assertEquals(
                "welcome message",
                "Welcome to Biblioteca. Your one-stop-shop for great book titles in Biblioteca!",
                actualResult);
    }

    @Test
    public void should_see_main_menu_of_options_after_welcome_message() {
        bibliotecaApp.run();

        assertEquals(
                "book list followed after welcome message",
                "List of books",
                spyPrinter.getPrintCalls().get(1).get(0));
    }
}
