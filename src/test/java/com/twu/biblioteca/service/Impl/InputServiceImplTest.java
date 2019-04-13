package com.twu.biblioteca.service.Impl;

import com.twu.biblioteca.service.impl.InputServiceImpl;
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

        String actualResult = new InputServiceImpl().inputBookName();

        assertEquals("book name", "Book Name", actualResult);
    }

    @After
    public void restore() {
        System.setIn(originalInputStream);
    }
}
