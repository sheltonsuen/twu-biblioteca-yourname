package com.twu.biblioteca.service.Impl;

import com.twu.biblioteca.service.InputService;

import java.util.Queue;

public class MockInputService implements InputService {

    private Queue<Integer> inputOptionNumbers;
    private Queue<String> inputBookNames;

    public MockInputService(Queue<Integer> inputOptionNumbers, Queue<String> inputBookNames) {
        this.inputBookNames = inputBookNames;
        this.inputOptionNumbers = inputOptionNumbers;
    }

    @Override
    public Integer inputMenuOptionNumber() {
        return inputOptionNumbers.poll();
    }

    @Override
    public String inputBookName() {
        return inputBookNames.poll();
    }
}
