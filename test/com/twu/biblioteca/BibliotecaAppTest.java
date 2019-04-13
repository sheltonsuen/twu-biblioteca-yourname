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
    public void should_see_all_books_after_welcome_message() {
        BibliotecaApp.main(new String[0]);

        assertEquals(
                "book list followed after welcome message",
                "Welcome to Biblioteca. Your one-stop-shop for great book titles in Biblioteca!\n" +
                        "Romance of the Three Kingdoms    Luo Guanzhong    1400\n" +
                        "Journey to the West Wu    Cheng'en    1503\n" +
                        "A Dream in Red Mansions    Cao Xueqin    1763\n" +
                        "Water Margin    Shi Nai'an    1370\n",
                mockOutputStream.toString());
    }

    @After
    public void restore() {
        System.setOut(originalOutputStream);
    }
}
