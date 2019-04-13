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

        assertEquals("welcome message",
                "*   Welcome to Biblioteca. Your one-stop-shop for great book titles in Biblioteca!   *",
                spyPrinter.getPrintCalls().get(0).get(1));
    }

    @Test
    public void should_print_menu_options_list() {
        withServices();

        uiServiceCLI.showMenuOptions();

        assertEquals("menu options",
                6,
                spyPrinter.getPrintCalls().get(0).size());
    }

    void withServices() {
        uiServiceCLI = new UIServiceCLIImpl();
        spyPrinter = new SpyPrinter();
        uiServiceCLI.setPrinter(spyPrinter);
    }
}
