package com.twu.biblioteca;

import com.twu.biblioteca.domain.Account;
import com.twu.biblioteca.service.SecurityService;
import com.twu.biblioteca.service.impl.*;
import com.twu.biblioteca.utils.Injector;
import org.junit.Test;
import sun.plugin2.os.windows.SECURITY_ATTRIBUTES;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

import static junit.framework.TestCase.assertEquals;

public class BibliotecaAppTest {

    private BibliotecaApp bibliotecaApp = new BibliotecaApp();
    private SpyPrinterService spyPrinterService;

    @Test
    public void make_sure_every_thing_is_ok() {
        InputStream originalInputStream = System.in;
        System.setIn(new ByteArrayInputStream("6".getBytes()));

        BibliotecaApp.main(null);

        System.setIn(originalInputStream);
    }

    @Test
    public void should_see_welcome_message_when_start_the_application() {
        withSetup(Collections.singletonList(6), Collections.emptyList(), true);

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
        withSetup(Collections.singletonList(6), Collections.emptyList(), true);

        bibliotecaApp.run();

        assertEquals(
                "book list followed after welcome message",
                "*    1. List of books    *",
                spyPrinterService.getPrintCalls().get(1).get(1));
    }

    @Test
    public void should_see_a_list_of_book_information_when_input_menu_option_1() {
        withSetup(Arrays.asList(1, 6), Collections.emptyList(), true);

        bibliotecaApp.run();

        int actualResult = spyPrinterService.getPrintCalls().get(3).size();
        assertEquals("Books count", 4, actualResult);
    }

    @Test
    public void should_see_a_notification_when_chose_an_invalid_option() {
        withSetup(Arrays.asList(0, 6), Collections.emptyList(), true);

        bibliotecaApp.run();

        String actualResult = spyPrinterService.getPrintCalls().get(2).get(1);

        assertEquals("Invalid menu options message", "*    (ఠ్ఠ ˓̭ ఠ్ఠ)    Please select a valid option!   *", actualResult);
    }

    @Test
    public void should_see_quit_option_after_book_list() {
        withSetup(Collections.singletonList(6), Collections.emptyList(), true);

        bibliotecaApp.run();

        assertEquals(
                "quit option followed after book list",
                "*    6. Quit             *",
                spyPrinterService.getPrintCalls().get(1).get(6));
    }

    @Test
    public void should_quit_after_chose_quit_option() {
        withSetup(Collections.singletonList(6), Collections.emptyList(), true);

        bibliotecaApp.run();
    }

    @Test
    public void should_can_select_again_when_not_quit() {
        withSetup(Arrays.asList(0, 1, 6), Collections.emptyList(), true);

        bibliotecaApp.run();

        assertEquals("invalid option",
                "*    (ఠ్ఠ ˓̭ ఠ్ఠ)    Please select a valid option!   *",
                spyPrinterService.getPrintCalls().get(2).get(1));

        assertEquals("book list count", 4, spyPrinterService.getPrintCalls().get(5).size());
    }

    @Test
    public void should_see_checkout_optional() {
        withSetup(Collections.singletonList(6), Collections.emptyList(), true);

        bibliotecaApp.run();

        assertEquals(
                "quit option followed after book list",
                "*    2. Checkout Book    *",
                spyPrinterService.getPrintCalls().get(1).get(2));
    }

    @Test
    public void should_checkout_the_book_if_chose_check_book_option() {
        withSetup(Arrays.asList(2, 1, 6), Collections.singletonList("Journey to the West Wu"), true);

        bibliotecaApp.run();

        assertEquals(
                "books list count",
                3,
                spyPrinterService.getPrintCalls().get(5).size());
    }

    @Test
    public void should_show_success_message_when_checkout_book_successfully() {
        withSetup(Arrays.asList(2, 1, 6), Collections.singletonList("Journey to the West Wu"), true);

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
        withSetup(Arrays.asList(2, 1, 6), Collections.singletonList("No that book"), true);

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
    public void should_show_return_book_message_right_below_check_out_option() {
        withSetup(Collections.singletonList(6), Collections.emptyList(), true);

        bibliotecaApp.run();

        assertEquals(
                "return option",
                "*    3. Return Book      *",
                spyPrinterService.getPrintCalls().get(1).get(3));
    }

    @Test
    public void should_display_the_returned_book_when_return_success() {
        withSetup(Arrays.asList(2, 3, 1, 6), Arrays.asList("Journey to the West Wu", "Journey to the West Wu"), true);

        bibliotecaApp.run();

        assertEquals(
                "books count",
                4,
                spyPrinterService.getPrintCalls().get(7).size());
    }

    @Test
    public void should_display_success_message_when_return_book_successfully() {
        withSetup(Arrays.asList(2, 3, 6), Arrays.asList("Journey to the West Wu", "Journey to the West Wu"), true);

        bibliotecaApp.run();

        assertEquals(
                "books count",
                "*    （｡ò ∀ ó｡）    Thank you for returning the book    *",
                spyPrinterService.getPrintCalls().get(4).get(1));
    }

    @Test
    public void should_display_unsuccessful_message_when_return_book_unsuccessfully() {
        withSetup(Arrays.asList(2, 3, 6), Arrays.asList("Journey to the West Wu", "Wrong BookTest"), true);

        bibliotecaApp.run();

        assertEquals(
                "books count",
                "*    ( ‾̮‿͂‾̮ ꐦ)    This is not a valid book to return    *",
                spyPrinterService.getPrintCalls().get(4).get(1));
    }

    @Test
    public void should_display_movie_list_when_select_movie_list_option() {
        withSetup(Arrays.asList(4, 6), Collections.emptyList(), true);

        bibliotecaApp.run();

        assertEquals(
                "movie list count",
                3,
                spyPrinterService.getPrintCalls().get(3).size());
    }

    @Test
    public void should_checkout_the_movie_if_chose_check_movie_option() {
        withSetup(Arrays.asList(5, 4, 6), Collections.singletonList("Forrest Gump"), true);

        bibliotecaApp.run();

        assertEquals(
                "books list count",
                2,
                spyPrinterService.getPrintCalls().get(4).size());
    }

    @Test
    public void should_guard_login_when_select_checkout_book() {
        withSetup(Arrays.asList(2, 6), Arrays.asList("123-6666", "1"), false);

        bibliotecaApp.run();

        assertEquals("login hint", "*    （｡ò ∀ ó｡）  Please login !!!    *", spyPrinterService.getPrintCalls().get(2).get(1));
    }

    @Test
    public void should_guard_login_when_select_return_book() {
        withSetup(Arrays.asList(3, 6), Arrays.asList("123-6666", "1"), false);

        bibliotecaApp.run();

        assertEquals("login hint", "*    （｡ò ∀ ó｡）  Please login !!!    *", spyPrinterService.getPrintCalls().get(2).get(1));
    }

    @Test
    public void should_guard_login_when_select_checkout_movie() {
        withSetup(Arrays.asList(5, 6), Arrays.asList("123-6666", "111"), false);

        bibliotecaApp.run();

        assertEquals("login hint", "*    （｡ò ∀ ó｡）  Please login !!!    *", spyPrinterService.getPrintCalls().get(2).get(1));
    }

    @Test
    public void should_back_to_menu_again_when_login_failed() {
        withSetup(Arrays.asList(5, 6), Arrays.asList("123-6666", "111"), false);

        bibliotecaApp.run();

        assertEquals("menu option count", 8, spyPrinterService.getPrintCalls().get(6).size());
    }

    @Test
    public void should_continue_process_previous_work_after_successfully_login() {
        withSetup(Arrays.asList(3, 6), Arrays.asList("123-6666", "123", "Journey to the West Wu"), false);

        bibliotecaApp.run();

        assertEquals("return book", "*    （｡ò ∀ ó｡）    Thank you for returning the book    *", spyPrinterService.getPrintCalls().get(6).get(1));
    }

    private void withSetup(List<Integer> inputOptions, List<String> inputStrings, Boolean preLogin) {
        Queue<Integer> optionsQueue = new ArrayDeque<>(inputOptions);
        Queue<String> bookNamesQueue = new ArrayDeque<>(inputStrings);
        MockInputService mockInputService = new MockInputService(optionsQueue, bookNamesQueue);
        Injector.getInstance().setInputService(mockInputService);
        Injector.getInstance().setBookBorrowService(new BookServiceMockImpl());
        Injector.getInstance().setMovieBorrowService(new MovieServiceMockImpl());

        SecurityServiceImpl securityService = new SecurityServiceImpl();
        if (preLogin) {
            securityService.setLoggedInAccount(new Account("Test", "test@tw.com", "15982026694", "123-6666", "123"));
        }
        Injector.getInstance().setSecurityService(securityService);

        spyPrinterService = new SpyPrinterService();
        Injector.getInstance().setPrinterService(spyPrinterService);

        Injector.getInstance().setUiService(new UIServiceCLIImpl());

        Injector.getInstance().injectDependencies(bibliotecaApp);
    }
}
