package com.twu.biblioteca.service.impl;

import com.twu.biblioteca.consts.ApplicationContant;
import com.twu.biblioteca.service.Printer;
import com.twu.biblioteca.service.UIService;
import lombok.Setter;

import java.util.Collections;

@Setter
public class UIServiceCLIImpl implements UIService {

    private Printer printer;

    @Override
    public void showWelcomeMessage() {
        printer.print(Collections.singletonList(ApplicationContant.WELCOME_MESSAGE));
    }
}
