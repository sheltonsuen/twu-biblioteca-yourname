package com.twu.biblioteca.service.impl;

import com.twu.biblioteca.consts.ApplicationConstant;
import com.twu.biblioteca.domain.Describable;
import com.twu.biblioteca.service.PrinterService;
import com.twu.biblioteca.service.UIService;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class UIServiceCLIImpl implements UIService {

    private PrinterService printerService;

    @Override
    public void showWelcomeMessage() {
        printerService.print(ApplicationConstant.WELCOME_MESSAGE);
    }

    @Override
    public void showMenuOptions() {
        printerService.print(ApplicationConstant.MENU_OPTIONS);
    }

    @Override
    public void showInvalidOptionsMessage() {
        printerService.print(ApplicationConstant.INVALID_OPTION_MESSAGE);
    }

    @Override
    public void showBookList(List<Describable> bookList) {
        printerService.print(Arrays.asList(ApplicationConstant.BOOK_LIST_LACE, ApplicationConstant.BOOK_LIST_HEADER));
        printerService.print(bookList.stream().map(Describable::briefSelfIntroduce).collect(Collectors.toList()));
        printerService.print(Collections.singletonList(ApplicationConstant.BOOK_LIST_LACE));
    }

    @Override
    public void showCheckoutBookHint(Boolean result) {
        printerService.print(result
                ? ApplicationConstant.SUCCESSFULLY_CHECKOUT_BOOK
                : ApplicationConstant.UNSUCCESSFULLY_CHECKOUT_BOOK);
    }

    @Override
    public void showReturnBookHint(Boolean result) {
        printerService.print(result
                ? ApplicationConstant.SUCCESSFULLY_RETURN_BOOK
                : ApplicationConstant.UNSUCCESSFULLY_RETURN_BOOK);
    }

    @Override
    public void showMovieList(List<Describable> movieList) {
        printerService.print(Arrays.asList(ApplicationConstant.MOVIE_LIST_LACE, ApplicationConstant.MOVIE_LIST_HEADER));
        printerService.print(movieList.stream().map(Describable::briefSelfIntroduce).collect(Collectors.toList()));
        printerService.print(Collections.singletonList(ApplicationConstant.MOVIE_LIST_LACE));
    }
}
