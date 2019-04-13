package com.twu.biblioteca.service.impl;

import com.twu.biblioteca.service.InputService;

import java.util.Scanner;

public class InputServiceImpl implements InputService {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public Integer inputMenuOptionNumber() {
        return scanner.nextInt();
    }
}
