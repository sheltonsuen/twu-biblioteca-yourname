package com.twu.biblioteca.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static junit.framework.TestCase.assertEquals;

public class InputServiceImplTest {
    private InputStream originalInputStream = System.in;

    @Before
    public void setup() {
        originalInputStream = System.in;
    }

    @Test
    public void should_input_a_option_number() {
        System.setIn(new ByteArrayInputStream("1".getBytes()));

        Integer actualResult = new InputServiceImpl().inputMenuOptionNumber();

        assertEquals("option number", Integer.valueOf(1), actualResult);
    }

    @Test
    public void should_input_a_book_name() {
        System.setIn(new ByteArrayInputStream("Book Name".getBytes()));

        String actualResult = new InputServiceImpl().inputNextLineAsString();

        assertEquals("book name", "Book Name", actualResult);
    }

    @Test
    public void should_input_to_next_line_when_current_line_has_nothing_but_line_break() {
        System.setIn(new ByteArrayInputStream("\nNext Line".getBytes()));

        String actualResult = new InputServiceImpl().inputNextLineAsString();

        assertEquals("include line break", "Next Line", actualResult);
    }

    @After
    public void restore() {
        System.setIn(originalInputStream);
    }
}
