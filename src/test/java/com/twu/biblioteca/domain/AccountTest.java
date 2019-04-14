package com.twu.biblioteca.domain;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class AccountTest {

    @Test
    public void should_return_brief_self_description() {
        Describable describable = new Account("Test", "test@tw.com", "15982026694", "123-6666", "123");

        String brief = describable.briefSelfIntroduce();

        assertEquals("account self intro", "*    Test                test@tw.com                 15982026694         *", brief);
    }
}
