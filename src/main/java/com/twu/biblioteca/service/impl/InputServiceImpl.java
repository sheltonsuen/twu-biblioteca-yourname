package com.twu.biblioteca.service.impl;

import com.twu.biblioteca.service.InputService;

import java.util.Scanner;

public class InputServiceImpl implements InputService {

    private static final String BLANK_LINE = "";
    private Scanner scanner = new Scanner(System.in);

    @Override
    public Integer inputMenuOptionNumber() {
        return scanner.nextInt();
    }

    @Override
    public String inputNextLineAsString() {
        String nextLine = scanner.nextLine();

        if (BLANK_LINE.equals(nextLine)) {
            nextLine = scanner.nextLine();
        }

        return nextLine;
    }
}
