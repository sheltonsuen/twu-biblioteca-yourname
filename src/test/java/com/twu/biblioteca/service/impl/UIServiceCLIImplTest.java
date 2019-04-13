package com.twu.biblioteca.service.impl;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class UIServiceCLIImplTest {

    private SpyPrinter spyPrinter;
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
                6,
                spyPrinter.getPrintCalls().get(0).size());
    }

    @Test
    public void should_print_invalid_options_message() {
        withServices();

        uiServiceCLI.showInvalidOptionsMessage();

        assertEquals("invalid option message",
                "*    (ఠ్ఠ ˓̭ ఠ్ఠ)    Please select a valid option!   *",
                spyPrinter.getPrintCalls().get(0).get(1));
    }

    void withServices() {
        uiServiceCLI = new UIServiceCLIImpl();
        spyPrinter = new SpyPrinter();
        uiServiceCLI.setPrinter(spyPrinter);
    }
}
