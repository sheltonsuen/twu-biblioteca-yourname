package com.twu.biblioteca.service.impl;

import com.twu.biblioteca.service.PrinterService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class PrinterServiceCLIImplTest {

    private final ByteArrayOutputStream mockOutputStream = new ByteArrayOutputStream();

    private final PrintStream originalOutputStream = System.out;

    private static PrinterService printerService;

    @Before
    public void setup() {
        System.setOut(new PrintStream(mockOutputStream));

        printerService = new PrinterServiceCLIImpl();
    }

    @Test
    public void should_print_all_strings_to_console() {
        List<String> mockContents = Arrays.asList("mock content1", "mock content2");

        printerService.print(mockContents);

        assertEquals("print contents",
                "mock content1\nmock content2\n",
                mockOutputStream.toString());
    }

    @After
    public void restore() {
        System.setOut(originalOutputStream);
    }
}
