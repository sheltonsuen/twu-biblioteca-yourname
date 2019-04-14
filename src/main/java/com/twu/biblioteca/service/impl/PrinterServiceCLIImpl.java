package com.twu.biblioteca.service.impl;

import com.twu.biblioteca.service.PrinterService;

import java.util.List;

public class PrinterServiceCLIImpl implements PrinterService {

    @Override
    public void print(List<String> contents) {
        contents.forEach(System.out::println);
    }
}
