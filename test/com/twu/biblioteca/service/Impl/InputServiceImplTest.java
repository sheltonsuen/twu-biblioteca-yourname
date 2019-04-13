package com.twu.biblioteca.service.Impl;

import com.twu.biblioteca.service.InputService;
import com.twu.biblioteca.service.impl.InputServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static junit.framework.TestCase.assertEquals;

public class InputServiceImplTest {

    private final ByteArrayInputStream mockInputStream = new ByteArrayInputStream("1".getBytes());

    private final InputStream originalInputStream = System.in;

    private InputService inputService;

    @Before
    public void setup() {
        System.setIn(mockInputStream);

        inputService = new InputServiceImpl();
    }

    @Test
    public void should_input_a_option_number() {

        Integer actualResult = inputService.inputMenuOptionNumber();

        assertEquals("", Integer.valueOf(1), actualResult);
    }

    @After
    public void restore() {
        System.setIn(originalInputStream);
    }
}
