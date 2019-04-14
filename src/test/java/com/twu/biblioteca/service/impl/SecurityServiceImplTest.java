package com.twu.biblioteca.service.impl;

import com.twu.biblioteca.domain.Account;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.*;

public class SecurityServiceImplTest {

    private SpyPrinterService spyPrinterService;
    private SecurityServiceImpl securityService;

    @Test
    public void should_do_nothing_when_is_logged_in() {
        withNewSecurityService(Collections.emptyList(), Collections.emptyList());
        securityService.setLoggedInAccount(new Account("Test", "test@tw.com", "15982026694", "123-6666", "123"));

        securityService.guardLogin();

        assertEquals("print count", 0, spyPrinterService.getPrintCalls().size());
    }

    @Test
    public void should_see_login_hint_when_is_not_logged_in() {
        withNewSecurityService(Collections.emptyList(), Collections.emptyList());

        securityService.guardLogin();

        assertEquals("login hint", "*    （｡ò ∀ ó｡）  Please login !!!    *", spyPrinterService.getPrintCalls().get(0).get(1));
    }

    @Test
    public void should_see_success_login_hint_when_login_successfully() {
        withNewSecurityService(Collections.emptyList(), Arrays.asList("123-6666", "123"));

        securityService.guardLogin();

        assertEquals("login hint", "*    （｡ò ∀ ó｡）  Login Successfully!!!    *", spyPrinterService.getPrintCalls().get(3).get(1));
    }

    @Test
    public void should_return_true_when_login_successfully() {
        withNewSecurityService(Collections.emptyList(), Arrays.asList("123-6666", "123"));

        boolean loginResult = securityService.guardLogin();

        assertTrue(loginResult);
    }

    @Test
    public void should_record_logged_in_when_login_account_successfully() {
        withNewSecurityService(Collections.emptyList(), Arrays.asList("123-6666", "123"));

        securityService.guardLogin();

        assertNotNull(securityService.getLoggedInAccount());
    }

    @Test
    public void should_see_failed_login_hint_when_login_failed() {
        withNewSecurityService(Collections.emptyList(), Arrays.asList("123-6666", "111"));

        boolean loginResult = securityService.guardLogin();

        assertFalse(loginResult);
    }

    private void withNewSecurityService(List<Integer> inputNumber, List<String> inputStr) {
        Queue<Integer> numbers = new ArrayDeque<>(inputNumber);
        Queue<String> strings = new ArrayDeque<>(inputStr);

        securityService = new SecurityServiceImpl();
        spyPrinterService = new SpyPrinterService();
        securityService.setPrinterService(spyPrinterService);
        securityService.setInputService(new MockInputService(numbers, strings));
    }
}
