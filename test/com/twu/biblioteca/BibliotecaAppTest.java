package com.twu.biblioteca;

import com.twu.biblioteca.service.Impl.SpyPrinter;
import com.twu.biblioteca.service.impl.BookServiceMockImpl;
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
        Injector.getInstance().setInputService(() -> 1);
        Injector.getInstance().setBookService(new BookServiceMockImpl());

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
    public void should_see_book_list_option_after_welcome_message() {
        bibliotecaApp.run();

        assertEquals(
                "book list followed after welcome message",
                "List of books",
                spyPrinter.getPrintCalls().get(1).get(0));
    }

    @Test
    public void should_see_a_list_of_book_information_when_input_menu_option_1() {
        bibliotecaApp.run();

        int actualResult = spyPrinter.getPrintCalls().get(2).size();

        assertEquals("Books count", 4, actualResult);
    }

    @Test
    public void should_see_a_notification_when_chose_an_invalid_option() {
        Injector.getInstance().setInputService(() -> 3);
        Injector.getInstance().injectDependencies(bibliotecaApp);

        bibliotecaApp.run();

        String actualResult = spyPrinter.getPrintCalls().get(2).get(0);

        assertEquals("Invalid menu options message", "Please select a valid option!", actualResult);
    }

    @Test
    public void should_see_quit_option_after_book_list() {
        bibliotecaApp.run();

        assertEquals(
                "quit option followed after book list",
                "Quit",
                spyPrinter.getPrintCalls().get(1).get(1));
    }
}
