package com.twu.biblioteca.service.impl;

import com.twu.biblioteca.consts.ApplicationContant;
import com.twu.biblioteca.service.Printer;
import com.twu.biblioteca.service.UIService;
import lombok.Getter;
import lombok.Setter;

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
}
