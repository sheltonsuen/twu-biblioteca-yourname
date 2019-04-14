package com.twu.biblioteca.service;

import com.twu.biblioteca.domain.Describable;

import java.util.List;

public interface UIService {

    void showWelcomeMessage();

    void showMenuOptions();

    void showInvalidOptionsMessage();

    void showBookList(List<Describable> bookList);

    void showCheckoutBookHint(Boolean result);

    void showReturnBookHint(Boolean result);

    void showMovieList(List<Describable> movieList);
}
