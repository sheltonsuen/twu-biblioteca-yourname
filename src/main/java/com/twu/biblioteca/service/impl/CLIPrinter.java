package com.twu.biblioteca.service.impl;

import com.twu.biblioteca.service.Printer;

import java.util.List;

public class CLIPrinter implements Printer {

    @Override
    public void print(List<String> contents) {
        contents.forEach(System.out::println);
    }
}
