package com.twu.biblioteca.service.impl;

import com.twu.biblioteca.consts.ApplicationContant;
import com.twu.biblioteca.domain.Describable;
import com.twu.biblioteca.service.Printer;
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

    private Printer printer;

    @Override
    public void showWelcomeMessage() {
        printer.print(ApplicationContant.WELCOME_MESSAGE);
    }

    @Override
    public void showMenuOptions() {
        printer.print(ApplicationContant.MENU_OPTIONS);
    }

    @Override
    public void showInvalidOptionsMessage() {
        printer.print(ApplicationContant.INVALID_OPTION_MESSAGE);
    }

    @Override
    public void showBookList(List<Describable> bookList) {
        printer.print(Arrays.asList(ApplicationContant.BOOK_LIST_LACE, ApplicationContant.BOOK_LIST_HEADER));
        printer.print(bookList.stream().map(Describable::briefSelfIntroduce).collect(Collectors.toList()));
        printer.print(Collections.singletonList(ApplicationContant.BOOK_LIST_LACE));
    }

    @Override
    public void showCheckoutBookHint(Boolean result) {
        printer.print(result
                ? ApplicationContant.SUCCESSFULLY_CHECKOUT_BOOK
                : ApplicationContant.UNSUCCESSFULLY_CHECKOUT_BOOK);
    }

    @Override
    public void showReturnBookHint(Boolean result) {
        printer.print(result
                ? ApplicationContant.SUCCESSFULLY_RETURN_BOOK
                : ApplicationContant.UNSUCCESSFULLY_RETURN_BOOK);
    }
}
