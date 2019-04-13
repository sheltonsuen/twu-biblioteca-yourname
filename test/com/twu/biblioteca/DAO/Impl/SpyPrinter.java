package com.twu.biblioteca.DAO.Impl;

import com.twu.biblioteca.service.Printer;

import java.util.ArrayList;
import java.util.List;

public class SpyPrinter implements Printer {

    private List<List<String>> printCalls = new ArrayList<>();

    @Override
    public void print(List<String> contents) {
        printCalls.add(contents);
    }

    public List<List<String>> getPrintCalls() {
        return printCalls;
    }
}
