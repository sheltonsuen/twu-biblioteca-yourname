package com.twu.biblioteca.domain;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MovieTest {

    @Test
    public void should_return_brief_self_description() {
        Describable describable = new Movie("Forrest Gump", 1994, "Robert Zemeckis", 2);

        String brief = describable.briefSelfIntroduce();

        assertEquals("*    Forrest Gump                        1994    Robert Zemeckis     2    *", brief);
    }
}
