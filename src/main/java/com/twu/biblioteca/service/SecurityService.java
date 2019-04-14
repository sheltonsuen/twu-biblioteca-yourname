package com.twu.biblioteca.service;

import com.twu.biblioteca.domain.Account;

public interface SecurityService {

    Boolean guardLogin();

    Account retrieveLoggedInAccount();
}
