package com.twu.biblioteca.service;

import com.twu.biblioteca.domain.Describable;

import java.util.List;

public interface BorrowAbleService {

    List<Describable> listAll();

    boolean checkout(String name);

    boolean returnBook(String name);
}
