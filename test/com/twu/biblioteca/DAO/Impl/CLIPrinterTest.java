package com.twu.biblioteca.DAO.Impl;

import com.twu.biblioteca.service.Printer;
import com.twu.biblioteca.service.impl.CLIPrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class CLIPrinterTest {

    private final ByteArrayOutputStream mockOutputStream = new ByteArrayOutputStream();

    private final PrintStream originalOutputStream = System.out;

    private static Printer printer;

    @Before
    public void setup() {
        System.setOut(new PrintStream(mockOutputStream));

        printer = new CLIPrinter();
    }

    @Test
    public void should_print_all_strings_to_console() {
        List<String> mockContents = Arrays.asList("mock content1", "mock content2");

        printer.print(mockContents);

        assertEquals("print contents",
                "mock content1\nmock content2\n",
                mockOutputStream.toString());
    }

    @After
    public void restore() {
        System.setOut(originalOutputStream);
    }
}
