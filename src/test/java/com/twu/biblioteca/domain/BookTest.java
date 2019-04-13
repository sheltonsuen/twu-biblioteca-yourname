package com.twu.biblioteca.domain;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class BookTest {

    @Test
    public void should_return_brief_self_description() {
        Describable describable = new Book("Book Name", "Book Author", 1999);

        String brief = describable.briefSelfIntroduce();

        assertEquals("book self intro", "*    Book Name                           Book Author         1999    *", brief);
    }
}
