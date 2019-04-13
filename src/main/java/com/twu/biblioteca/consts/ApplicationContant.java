package com.twu.biblioteca.consts;

import java.util.Arrays;
import java.util.List;

public class ApplicationContant {
    public static final String BOOK_INFO_SLICER = "    ";
    public static final String BOOK_LACE = "*";
    public static final String BOOK_LIST_LACE = "**********************************************************************";
    public static final String BOOK_LIST_HEADER = "*    Book Name                        |  Book Author      |  Year    *";

    public static final List<String> WELCOME_MESSAGE = Arrays.asList(
            "****************************************************************",
            "*                    Welcome to Biblioteca                     *",
            "*    Your one-stop-shop for great book titles in Biblioteca!   *",
            "****************************************************************"
    );

    public static final List<String> MENU_OPTIONS = Arrays.asList(
            "**************************",
            "*    1. List of books    *",
            "*    2. Checkout Book    *",
            "*    3. Return Book      *",
            "*    4. Quit             *",
            "**************************");

    public static final List<String> INVALID_OPTION_MESSAGE = Arrays.asList(
            "**************************************************",
            "*    (ఠ్ఠ ˓̭ ఠ్ఠ)    Please select a valid option!   *",
            "**************************************************"
    );

    public static final List<String> SUCCESSFULLY_CHECKOUT_BOOK = Arrays.asList(
            "***********************************************",
            "*    （｡ò ∀ ó｡）  Thank you! Enjoy the book    *",
            "***********************************************"
    );

    public static final List<String> UNSUCCESSFULLY_CHECKOUT_BOOK = Arrays.asList(
            "*******************************************************",
            "*    ( ‾̮‿͂‾̮ ꐦ)    Sorry, that book is not available    *",
            "*******************************************************"
    );

    public static final List<String> SUCCESSFULLY_RETURN_BOOK = Arrays.asList(
            "********************************************************",
            "*    （｡ò ∀ ó｡）    Thank you for returning the book    *",
            "********************************************************"
    );
    public static final List<String> UNSUCCESSFULLY_RETURN_BOOK = Arrays.asList(
            "********************************************************",
            "*    ( ‾̮‿͂‾̮ ꐦ)    This is not a valid book to return    *",
            "********************************************************"
    );
}
