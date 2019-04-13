package com.twu.biblioteca.utils;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StringUtilsTest {

    @Test
    public void should_display_with_three_dots_if_name_length_greater_than_length() {
        String testName = "test name test name test name test name";

        String smoothedName = StringUtils.smooth(testName, 12);

        assertEquals("name with dots",
                "test name...",
                smoothedName);
    }

    @Test
    public void should_make_up_with_space_when_length_is_not_enough() {
        String testName = "test name";

        String smoothedName = StringUtils.smooth(testName, 12);

        assertEquals("name with dots",
                "test name   ",
                smoothedName);
    }
}
