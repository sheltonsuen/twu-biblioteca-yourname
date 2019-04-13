package com.twu.biblioteca.service.impl;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class UIServiceCLIImplTest {

    private SpyPrinter spyPrinter;

    @Test
    public void should_print_welcome_message() {
        UIServiceCLIImpl uiServiceCLI = new UIServiceCLIImpl();
        spyPrinter = new SpyPrinter();
        uiServiceCLI.setPrinter(spyPrinter);

        uiServiceCLI.showWelcomeMessage();

        assertEquals("welcome message",
                "*   Welcome to Biblioteca. Your one-stop-shop for great book titles in Biblioteca!   *",
                spyPrinter.getPrintCalls().get(0).get(1));
    }
}
