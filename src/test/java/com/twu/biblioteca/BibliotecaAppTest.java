package com.twu.biblioteca;

import com.twu.biblioteca.service.impl.MockInputService;
import com.twu.biblioteca.service.impl.SpyPrinterService;
import com.twu.biblioteca.service.impl.BookServiceMockImpl;
import com.twu.biblioteca.service.impl.UIServiceCLIImpl;
import com.twu.biblioteca.utils.Injector;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertEquals;

public class BibliotecaAppTest {

    private BibliotecaApp bibliotecaApp = new BibliotecaApp();
    private SpyPrinterService spyPrinterService;

    @Before
    public void setup() {
    }

    @Test
    public void should_see_welcome_message_when_start_the_application() {
        withInput(Collections.singletonList(4), Collections.emptyList());

        bibliotecaApp.run();

        String welcomeFirstLine = spyPrinterService.getPrintCalls().get(0).get(1);
        String welcomeSecondLine = spyPrinterService.getPrintCalls().get(0).get(2);

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
    public void should_see_book_list_option_after_welcome_message() {
        withInput(Collections.singletonList(4), Collections.emptyList());

        bibliotecaApp.run();

        assertEquals(
                "book list followed after welcome message",
                "*    1. List of books    *",
                spyPrinterService.getPrintCalls().get(1).get(1));
    }

    @Test
    public void should_see_a_list_of_book_information_when_input_menu_option_1() {
        withInput(Arrays.asList(1, 4), Collections.emptyList());

        bibliotecaApp.run();

        int actualResult = spyPrinterService.getPrintCalls().get(3).size();
        assertEquals("Books count", 4, actualResult);
    }

    @Test
    public void should_see_a_notification_when_chose_an_invalid_option() {
        withInput(Arrays.asList(0, 4), Collections.emptyList());

        bibliotecaApp.run();

        String actualResult = spyPrinterService.getPrintCalls().get(2).get(1);

        assertEquals("Invalid menu options message", "*    (ఠ్ఠ ˓̭ ఠ్ఠ)    Please select a valid option!   *", actualResult);
    }

    @Test
    public void should_see_quit_option_after_book_list() {
        withInput(Collections.singletonList(4), Collections.emptyList());

        bibliotecaApp.run();

        assertEquals(
                "quit option followed after book list",
                "*    5. Quit             *",
                spyPrinterService.getPrintCalls().get(1).get(5));
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
                "*    (ఠ్ఠ ˓̭ ఠ్ఠ)    Please select a valid option!   *",
                spyPrinterService.getPrintCalls().get(2).get(1));

        assertEquals("book list count", 4, spyPrinterService.getPrintCalls().get(5).size());
    }

    @Test
    public void should_see_checkout_optional() {
        withInput(Collections.singletonList(4), Collections.emptyList());

        bibliotecaApp.run();

        assertEquals(
                "quit option followed after book list",
                "*    2. Checkout Book    *",
                spyPrinterService.getPrintCalls().get(1).get(2));
    }

    @Test
    public void should_checkout_the_book_if_chose_check_book_option() {
        withInput(Arrays.asList(2, 1, 4), Collections.singletonList("Journey to the West Wu"));

        bibliotecaApp.run();

        assertEquals(
                "books list count",
                3,
                spyPrinterService.getPrintCalls().get(5).size());
    }

    @Test
    public void should_show_success_message_when_checkout_book_successfully() {
        withInput(Arrays.asList(2, 1, 4), Collections.singletonList("Journey to the West Wu"));

        bibliotecaApp.run();

        assertEquals(
                "success checkout message",
                "*    （｡ò ∀ ó｡）  Thank you! Enjoy the book    *",
                spyPrinterService.getPrintCalls().get(2).get(1));

        assertEquals(
                "books list count",
                3,
                spyPrinterService.getPrintCalls().get(5).size());
    }

    @Test
    public void should_show_un_success_message_when_checkout_book_unsuccessfully() {
        withInput(Arrays.asList(2, 1, 4), Collections.singletonList("No that book"));

        bibliotecaApp.run();

        assertEquals(
                "success checkout message",
                "*    ( ‾̮‿͂‾̮ ꐦ)    Sorry, that book is not available    *",
                spyPrinterService.getPrintCalls().get(2).get(1));

        assertEquals(
                "books list count",
                4,
                spyPrinterService.getPrintCalls().get(5).size());
    }

    @Test
    public void should_show_return_book_message_right_beow_check_out_option() {
        withInput(Collections.singletonList(4), Collections.emptyList());

        bibliotecaApp.run();

        assertEquals(
                "return option",
                "*    3. Return Book      *",
                spyPrinterService.getPrintCalls().get(1).get(3));
    }

    @Test
    public void should_display_the_returned_book_when_return_success() {
        withInput(Arrays.asList(2, 3, 1, 4), Arrays.asList("Journey to the West Wu", "Journey to the West Wu"));

        bibliotecaApp.run();

        assertEquals(
                "books count",
                4,
                spyPrinterService.getPrintCalls().get(7).size());
    }

    @Test
    public void should_display_success_message_when_return_book_successfully() {
        withInput(Arrays.asList(2, 3, 4), Arrays.asList("Journey to the West Wu", "Journey to the West Wu"));

        bibliotecaApp.run();

        assertEquals(
                "books count",
                "*    （｡ò ∀ ó｡）    Thank you for returning the book    *",
                spyPrinterService.getPrintCalls().get(4).get(1));
    }

    @Test
    public void should_display_unsuccessful_message_when_return_book_unsuccessfully() {
        withInput(Arrays.asList(2, 3, 4), Arrays.asList("Journey to the West Wu", "Wrong BookTest"));

        bibliotecaApp.run();

        assertEquals(
                "books count",
                "*    ( ‾̮‿͂‾̮ ꐦ)    This is not a valid book to return    *",
                spyPrinterService.getPrintCalls().get(4).get(1));
    }

    private void withInput(List<Integer> options, List<String> bookNames) {
        Queue<Integer> optionsQueue = new ArrayDeque<>(options);
        Queue<String> bookNamesQueue = new ArrayDeque<>(bookNames);
        MockInputService mockInputService = new MockInputService(optionsQueue, bookNamesQueue);
        Injector.getInstance().setInputService(mockInputService);
        Injector.getInstance().setBorrowAbleService(new BookServiceMockImpl());

        spyPrinterService = new SpyPrinterService();
        Injector.getInstance().setPrinterService(spyPrinterService);

        Injector.getInstance().setUiService(new UIServiceCLIImpl());

        Injector.getInstance().injectDependencies(bibliotecaApp);
    }
}
