package com.twu.biblioteca;

import com.twu.biblioteca.service.impl.MockInputService;
import com.twu.biblioteca.service.impl.SpyPrinter;
import com.twu.biblioteca.service.impl.BookServiceMockImpl;
import com.twu.biblioteca.service.impl.UIServiceCLIImpl;
import com.twu.biblioteca.utils.Injector;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertEquals;

public class BibliotecaAppTest {

    private BibliotecaApp bibliotecaApp = new BibliotecaApp();
    private SpyPrinter spyPrinter;

    @Before
    public void setup() {
    }

    @Test
    public void should_see_welcome_message_when_start_the_application() {
        withInput(Collections.singletonList(4), Collections.emptyList());

        bibliotecaApp.run();

        String actualResult = spyPrinter.getPrintCalls().get(0).get(1);

        assertEquals(
                "welcome message",
                "*   Welcome to Biblioteca. Your one-stop-shop for great book titles in Biblioteca!   *",
                actualResult);
    }

    @Test
    public void should_see_book_list_option_after_welcome_message() {
        withInput(Collections.singletonList(4), Collections.emptyList());

        bibliotecaApp.run();

        assertEquals(
                "book list followed after welcome message",
                "*    1. List of books    *",
                spyPrinter.getPrintCalls().get(1).get(1));
    }

    @Test
    public void should_see_a_list_of_book_information_when_input_menu_option_1() {
        withInput(Arrays.asList(1, 4), Collections.emptyList());

        bibliotecaApp.run();

        int actualResult = spyPrinter.getPrintCalls().get(2).size();
        assertEquals("Books count", 4, actualResult);
    }

    @Test
    public void should_see_a_notification_when_chose_an_invalid_option() {
        withInput(Arrays.asList(0, 4), Collections.emptyList());

        bibliotecaApp.run();

        String actualResult = spyPrinter.getPrintCalls().get(2).get(0);

        assertEquals("Invalid menu options message", "Please select a valid option!", actualResult);
    }

    @Test
    public void should_see_quit_option_after_book_list() {
        withInput(Collections.singletonList(4), Collections.emptyList());

        bibliotecaApp.run();

        assertEquals(
                "quit option followed after book list",
                "*    4. Quit             *",
                spyPrinter.getPrintCalls().get(1).get(4));
    }

    @Test
    public void should_quit_after_chose_quit_option() {
        withInput(Collections.singletonList(4), Collections.emptyList());

        bibliotecaApp.run();
    }

    @Test
    public void should_can_select_again_when_not_quit() {
        withInput(Arrays.asList(0, 1, 4), Collections.emptyList());

        bibliotecaApp.run();

        assertEquals("invalid option",
                "Please select a valid option!",
                spyPrinter.getPrintCalls().get(2).get(0));

        assertEquals("book list count", 4, spyPrinter.getPrintCalls().get(4).size());
    }

    @Test
    public void should_see_checkout_optional() {
        withInput(Collections.singletonList(4), Collections.emptyList());

        bibliotecaApp.run();

        assertEquals(
                "quit option followed after book list",
                "*    2. Checkout Book    *",
                spyPrinter.getPrintCalls().get(1).get(2));
    }

    @Test
    public void should_checkout_the_book_if_chose_check_book_option() {
        withInput(Arrays.asList(2, 1, 4), Collections.singletonList("Journey to the West Wu"));

        bibliotecaApp.run();

        assertEquals(
                "books list count",
                3,
                spyPrinter.getPrintCalls().get(4).size());
    }

    @Test
    public void should_show_success_message_when_checkout_book_successfully() {
        withInput(Arrays.asList(2, 1, 4), Collections.singletonList("Journey to the West Wu"));

        bibliotecaApp.run();

        assertEquals(
                "success checkout message",
                "Thank you! Enjoy the book",
                spyPrinter.getPrintCalls().get(2).get(0));

        assertEquals(
                "books list count",
                3,
                spyPrinter.getPrintCalls().get(4).size());
    }

    @Test
    public void should_show_un_success_message_when_checkout_book_unsuccessfully() {
        withInput(Arrays.asList(2, 1, 4), Collections.singletonList("No that book"));

        bibliotecaApp.run();

        assertEquals(
                "success checkout message",
                "Sorry, that book is not available",
                spyPrinter.getPrintCalls().get(2).get(0));

        assertEquals(
                "books list count",
                4,
                spyPrinter.getPrintCalls().get(4).size());
    }

    @Test
    public void should_show_return_book_message_right_beow_check_out_option() {
        withInput(Collections.singletonList(4), Collections.emptyList());

        bibliotecaApp.run();

        assertEquals(
                "return option",
                "*    3. Return Book      *",
                spyPrinter.getPrintCalls().get(1).get(3));
    }

    @Test
    public void should_display_the_returned_book_when_return_success() {
        withInput(Arrays.asList(2, 3, 1, 4), Arrays.asList("Journey to the West Wu", "Journey to the West Wu"));

        bibliotecaApp.run();

        assertEquals(
                "books count",
                4,
                spyPrinter.getPrintCalls().get(6).size());
    }

    @Test
    public void should_display_success_message_when_return_book_successfully() {
        withInput(Arrays.asList(2, 3, 4), Arrays.asList("Journey to the West Wu", "Journey to the West Wu"));

        bibliotecaApp.run();

        assertEquals(
                "books count",
                "Thank you for returning the book",
                spyPrinter.getPrintCalls().get(4).get(0));
    }

    @Test
    public void should_display_unsuccessful_message_when_return_book_unsuccessfully() {
        withInput(Arrays.asList(2, 3, 4), Arrays.asList("Journey to the West Wu", "Wrong BookTest"));

        bibliotecaApp.run();

        assertEquals(
                "books count",
                "This is not a valid book to return",
                spyPrinter.getPrintCalls().get(4).get(0));
    }

    void withInput(List<Integer> options, List<String> bookNames) {
        Queue<Integer> optionsQueue = new ArrayDeque<>(options);
        Queue<String> bookNamesQueue = new ArrayDeque<>(bookNames);
        MockInputService mockInputService = new MockInputService(optionsQueue, bookNamesQueue);
        Injector.getInstance().setInputService(mockInputService);
        Injector.getInstance().setBorrowAbleService(new BookServiceMockImpl());

        spyPrinter = new SpyPrinter();
        Injector.getInstance().setPrinter(spyPrinter);

        Injector.getInstance().setUiService(new UIServiceCLIImpl());

        Injector.getInstance().injectDependencies(bibliotecaApp);
    }
}
