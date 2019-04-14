package com.twu.biblioteca.consts;

import java.util.Arrays;
import java.util.List;

public class ApplicationConstant {
    public static final String INFO_SLICER = "    ";
    public static final String LANDSCAPE_LACE = "*";

    public static final String BOOK_LIST_LACE = "**********************************************************************";
    public static final String BOOK_LIST_HEADER = "*    Book Name                        |  Book Author      |  Year    *";

    public static final String MOVIE_LIST_LACE = "***************************************************************************";
    public static final String MOVIE_LIST_HEADER = "*    Movie Name                      |   Year |  Director        | Rating *";

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
            "*    4. List Movies      *",
            "*    5. Checkout Movie   *",
            "*    6. Quit             *",
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

    public static final List<String> SUCCESSFULLY_CHECKOUT_MOVIE = Arrays.asList(
            "************************************************",
            "*    （｡ò ∀ ó｡）  Thank you! Enjoy the movie    *",
            "************************************************"
    );

    public static final List<String> UNSUCCESSFULLY_CHECKOUT_MOVIE = Arrays.asList(
            "********************************************************",
            "*    ( ‾̮‿͂‾̮ ꐦ)    Sorry, that movie is not available    *",
            "********************************************************"
    );
}
