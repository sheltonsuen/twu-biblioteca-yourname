package com.twu.biblioteca.service.impl;

import com.twu.biblioteca.service.PrinterService;

import java.util.ArrayList;
import java.util.List;

public class SpyPrinterService implements PrinterService {

    private List<List<String>> printCalls = new ArrayList<>();

    @Override
    public void print(List<String> contents) {
        printCalls.add(contents);
    }

    public List<List<String>> getPrintCalls() {
        return printCalls;
    }
}
