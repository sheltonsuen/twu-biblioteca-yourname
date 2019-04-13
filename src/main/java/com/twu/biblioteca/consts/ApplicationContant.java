package com.twu.biblioteca.consts;

import java.util.Arrays;
import java.util.List;

public class ApplicationContant {
    public static final String BOOK_INFO_SLICER = "    ";
    public static final String INVALID_OPTION_NOTIFICATION = "Please select a valid option!";
    public static final String SUCCESSFULLY_CHECKOUT_BOOK = "Thank you! Enjoy the book";
    public static final String UNSUCCESSFULLY_CHECKOUT_BOOK = "Sorry, that book is not available";
    public static final String SUCCESSFULLY_RETURN_BOOK = "Thank you for returning the book";
    public static final String UNSUCCESSFULLY_RETURN_BOOK = "This is not a valid book to return";

    public static final String WELCOME_VERTICAL_LACE = "**************************************************************************************";
    public static final String WELCOME_MESSAGE = "*   Welcome to Biblioteca. Your one-stop-shop for great book titles in Biblioteca!   *";

    public static final List<String> MENU_OPTIONS = Arrays.asList(
            "1. List of books",
            "2. Checkout Book",
            "3. Return Book",
            "4. Quit");
}
