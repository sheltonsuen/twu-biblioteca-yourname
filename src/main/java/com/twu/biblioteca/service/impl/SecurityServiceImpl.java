package com.twu.biblioteca.service.impl;

import com.google.common.collect.ImmutableMap;
import com.twu.biblioteca.consts.ApplicationConstant;
import com.twu.biblioteca.domain.Account;
import com.twu.biblioteca.service.InputService;
import com.twu.biblioteca.service.PrinterService;
import com.twu.biblioteca.service.SecurityService;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.Map;

import static com.twu.biblioteca.consts.ApplicationConstant.*;

@Setter
@Getter
public class SecurityServiceImpl implements SecurityService {

    private PrinterService printerService;
    private Account loggedInAccount;
    private InputService inputService;

    private Map<String, Account> accountMap = ImmutableMap.of(
            "123-6666", new Account("Test", "test@tw.com", "15982026694", "123-6666", "123")
    );

    @Override
    public Boolean guardLogin() {
        if (loggedInAccount != null) {
            return true;
        }

        printerService.print(ApplicationConstant.LOGIN_HINE);

        printerService.print(Collections.singletonList(LIBRARY_NUMBER));
        String libraryNumber = inputService.inputNextLineAsString();

        printerService.print(Collections.singletonList(PASSWORD));
        String password = inputService.inputNextLineAsString();

        return processLogin(libraryNumber, password);
    }

    private Boolean processLogin(String libraryNumber, String password) {
        Account account = accountMap.get(libraryNumber);

        if (account == null || !account.getPassword().equals(password)) {
            printerService.print(LOGIN_FAILED);
            return false;
        }

        printerService.print(LOGIN_SUCCESSFULLY);
        this.loggedInAccount = account;
        return true;
    }
}
