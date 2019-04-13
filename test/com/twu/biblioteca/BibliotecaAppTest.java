package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;

public class BibliotecaAppTest {

    private final ByteArrayOutputStream mockOutputStream = new ByteArrayOutputStream();

    private final PrintStream originalOutputStream = System.out;

    @Before
    public void setup() {
        System.setOut(new PrintStream(mockOutputStream));
    }

    @Test
    public void should_see_welcome_message_when_start_the_application() {
        BibliotecaApp.main(new String[0]);


        String actualResult = mockOutputStream.toString().split("\n")[0];
        assertEquals(
                "welcome message",
                "Welcome to Biblioteca. Your one-stop-shop for great book titles in Biblioteca!",
                actualResult);
    }

    @Test
    public void should_see_main_menu_of_options_after_welcome_message() {
        BibliotecaApp.main(new String[0]);

        assertEquals(
                "book list followed after welcome message",
                "Welcome to Biblioteca. Your one-stop-shop for great book titles in Biblioteca!\n" +
                        "List of books\n",
                mockOutputStream.toString());
    }

    @After
    public void restore() {
        System.setOut(originalOutputStream);
    }
}
