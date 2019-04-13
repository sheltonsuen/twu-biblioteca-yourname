package com.twu.biblioteca;

import com.twu.biblioteca.service.Impl.SpyPrinter;
import com.twu.biblioteca.service.impl.BookServiceMockImpl;
import com.twu.biblioteca.utils.Injector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class BibliotecaAppTest {

    private BibliotecaApp bibliotecaApp = new BibliotecaApp();
    private SpyPrinter spyPrinter = new SpyPrinter();
    private Integer mockOptionalNumber = 1;

    @Before
    public void setup() {
        Injector.getInstance().setPrinter(spyPrinter);
        Injector.getInstance().setInputService(() -> mockOptionalNumber++);
        Injector.getInstance().setBookService(new BookServiceMockImpl());

        Injector.getInstance().injectDependencies(bibliotecaApp);
    }

    @Test
    public void should_see_welcome_message_when_start_the_application() {
        mockOptionalNumber = 1;

        bibliotecaApp.run();

        String actualResult = spyPrinter.getPrintCalls().get(0).get(0);

        assertEquals(
                "welcome message",
                "Welcome to Biblioteca. Your one-stop-shop for great book titles in Biblioteca!",
                actualResult);
    }

    @Test
    public void should_see_book_list_option_after_welcome_message() {
        mockOptionalNumber = 1;

        bibliotecaApp.run();

        assertEquals(
                "book list followed after welcome message",
                "1. List of books",
                spyPrinter.getPrintCalls().get(1).get(0));
    }

    @Test
    public void should_see_a_list_of_book_information_when_input_menu_option_1() {
        mockOptionalNumber = 1;

        bibliotecaApp.run();

        int actualResult = spyPrinter.getPrintCalls().get(2).size();
        assertEquals("Books count", 4, actualResult);
    }

    @Test
    public void should_see_a_notification_when_chose_an_invalid_option() {
        mockOptionalNumber = 0;
        Injector.getInstance().setInputService(() -> mockOptionalNumber++);
        Injector.getInstance().injectDependencies(bibliotecaApp);

        bibliotecaApp.run();

        String actualResult = spyPrinter.getPrintCalls().get(2).get(0);

        assertEquals("Invalid menu options message", "Please select a valid option!", actualResult);
    }

    @Test
    public void should_see_quit_option_after_book_list() {
        mockOptionalNumber = 3;

        bibliotecaApp.run();

        assertEquals(
                "quit option followed after book list",
                "3. Quit",
                spyPrinter.getPrintCalls().get(1).get(2));
    }

    @Test
    public void should_quit_after_chose_quit_option() {
        Injector.getInstance().setInputService(() -> 3);
        Injector.getInstance().injectDependencies(bibliotecaApp);

        bibliotecaApp.run();
    }

    @Test
    public void should_can_select_again_when_not_quit() {
        mockOptionalNumber = 0;
        Injector.getInstance().setInputService(() -> mockOptionalNumber++);
        Injector.getInstance().injectDependencies(bibliotecaApp);

        bibliotecaApp.run();

        assertEquals("invalid option",
                "Please select a valid option!",
                spyPrinter.getPrintCalls().get(2).get(0));

        assertEquals("book list count", 4, spyPrinter.getPrintCalls().get(4).size());

        mockOptionalNumber = 0;
    }

    @Test
    public void should_see_checkout_optional() {
        mockOptionalNumber = 2;
        bibliotecaApp.run();

        assertEquals(
                "quit option followed after book list",
                "2. Checkout Book",
                spyPrinter.getPrintCalls().get(1).get(1));
    }
}
