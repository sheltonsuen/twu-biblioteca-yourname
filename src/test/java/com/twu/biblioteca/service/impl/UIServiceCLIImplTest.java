package com.twu.biblioteca.service.impl;

import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.domain.Describable;
import com.twu.biblioteca.domain.Movie;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

public class UIServiceCLIImplTest {

    private SpyPrinterService spyPrinter;
    private UIServiceCLIImpl uiServiceCLI;

    @Test
    public void should_print_welcome_message() {
        withServices();

        uiServiceCLI.showWelcomeMessage();

        String welcomeFirstLine = spyPrinter.getPrintCalls().get(0).get(1);
        String welcomeSecondLine = spyPrinter.getPrintCalls().get(0).get(2);

        assertEquals(
                "welcome message first line",
                "*                    Welcome to Biblioteca                     *",
                welcomeFirstLine);
        assertEquals(
                "welcome message second line",
                "*    Your one-stop-shop for great book titles in Biblioteca!   *",
                welcomeSecondLine);
    }

    @Test
    public void should_print_menu_options_list() {
        withServices();

        uiServiceCLI.showMenuOptions();

        assertEquals("menu options",
                7,
                spyPrinter.getPrintCalls().get(0).size());
        assertEquals("list book option", "*    1. List of books    *", spyPrinter.getPrintCalls().get(0).get(1));
        assertEquals("checkout book", "*    2. Checkout Book    *", spyPrinter.getPrintCalls().get(0).get(2));
        assertEquals("return book", "*    3. Return Book      *", spyPrinter.getPrintCalls().get(0).get(3));
        assertEquals("list movies", "*    4. List Movies      *", spyPrinter.getPrintCalls().get(0).get(4));
        assertEquals("quit", "*    5. Quit             *", spyPrinter.getPrintCalls().get(0).get(5));
    }

    @Test
    public void should_print_invalid_options_message() {
        withServices();

        uiServiceCLI.showInvalidOptionsMessage();

        assertEquals("invalid option message",
                "*    (ఠ్ఠ ˓̭ ఠ్ఠ)    Please select a valid option!   *",
                spyPrinter.getPrintCalls().get(0).get(1));
    }

    @Test
    public void should_print_book_list_brief_intro() {
        withServices();
        Describable describable = new Book("Test Book", "Test Author", 1999);

        uiServiceCLI.showBookList(Arrays.asList(describable, describable));

        assertEquals("book list",
                "*    Test Book                           Test Author         1999    *",
                spyPrinter.getPrintCalls().get(1).get(0));
        assertEquals("book list",
                "*    Test Book                           Test Author         1999    *",
                spyPrinter.getPrintCalls().get(1).get(1));
    }

    @Test
    public void should_print_checkout_success_message_when_success() {
        withServices();

        uiServiceCLI.showCheckoutBookHint(true);

        assertEquals("checkout success",
                "*    （｡ò ∀ ó｡）  Thank you! Enjoy the book    *",
                spyPrinter.getPrintCalls().get(0).get(1));
    }

    @Test
    public void should_print_checkout_failed_message_when_fails() {
        withServices();

        uiServiceCLI.showCheckoutBookHint(false);

        assertEquals("checkout fails",
                "*    ( ‾̮‿͂‾̮ ꐦ)    Sorry, that book is not available    *",
                spyPrinter.getPrintCalls().get(0).get(1));
    }

    @Test
    public void should_print_return_book_success_message_when_success() {
        withServices();

        uiServiceCLI.showReturnBookHint(true);

        assertEquals("checkout success",
                "*    （｡ò ∀ ó｡）    Thank you for returning the book    *",
                spyPrinter.getPrintCalls().get(0).get(1));
    }

    @Test
    public void should_print_return_book_failed_message_when_fails() {
        withServices();

        uiServiceCLI.showReturnBookHint(false);

        assertEquals("checkout fails",
                "*    ( ‾̮‿͂‾̮ ꐦ)    This is not a valid book to return    *",
                spyPrinter.getPrintCalls().get(0).get(1));
    }

    @Test
    public void should_print_movie_list_with_brief_intro() {
        withServices();
        Describable describable = new Movie("Forrest Gump", 1994, "Robert Zemeckis", 2);

        uiServiceCLI.showMovieList(Arrays.asList(describable, describable));

        assertEquals("movie list header",
                "*    Movie Name                      |   Year |  Director        | Rating *",
                spyPrinter.getPrintCalls().get(0).get(1));
        assertEquals("movie one",
                "*    Forrest Gump                        1994    Robert Zemeckis     2    *",
                spyPrinter.getPrintCalls().get(1).get(0));
        assertEquals("book two",
                "*    Forrest Gump                        1994    Robert Zemeckis     2    *",
                spyPrinter.getPrintCalls().get(1).get(1));
    }

    private void withServices() {
        uiServiceCLI = new UIServiceCLIImpl();
        spyPrinter = new SpyPrinterService();
        uiServiceCLI.setPrinterService(spyPrinter);
    }
}
