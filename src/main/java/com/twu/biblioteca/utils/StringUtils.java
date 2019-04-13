package com.twu.biblioteca.utils;

public class StringUtils {

    private static final String DOTS = "...";
    private static final String SPACE = " ";

    public static String smooth(String str, int controlLength) {

        if (str.length() > controlLength) {
            return str.substring(0, controlLength - 3).concat(DOTS);
        }

        StringBuilder nameBuilder = new StringBuilder(str);
        for (int i = 0; i < controlLength - str.length(); i++) {
            nameBuilder.append(SPACE);
        }
        return nameBuilder.toString();
    }
}
