package com.twu.biblioteca.service.impl;

import com.twu.biblioteca.consts.ApplicationContant;
import com.twu.biblioteca.service.Printer;
import com.twu.biblioteca.service.UIService;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Setter
@Getter
public class UIServiceCLIImpl implements UIService {

    private Printer printer;

    @Override
    public void showWelcomeMessage() {
        printer.print(Arrays.asList(
                ApplicationContant.WELCOME_VERTICAL_LACE,
                ApplicationContant.WELCOME_MESSAGE,
                ApplicationContant.WELCOME_VERTICAL_LACE));
    }

    @Override
    public void showMenuOptions() {
        printer.print(ApplicationContant.MENU_OPTIONS);
    }

    @Override
    public void showInvalidOptionsMessage() {
        printer.print(Arrays.asList(ApplicationContant.INVALID_OPTION_NOTIFICATION));
    }
}
