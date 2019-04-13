package com.twu.biblioteca.service.Impl;

import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.impl.BookServiceMockImpl;
import com.twu.biblioteca.domain.Book;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.*;

public class BookServiceMockImplTest {

    @Test
    public void should_list_all_books_as_list_when_call_list_all() {
        BookService bookService = new BookServiceMockImpl();

        List<Book> actualBookList = bookService.listAll();

        assertEquals("list all books", 4, actualBookList.size());
    }

    @Test
    public void should_checkout_the_book_that_match_the_name_and_return_true() {
        BookService bookService = new BookServiceMockImpl();

        boolean result = bookService.checkout("Journey to the West Wu");

        int bookListSize = bookService.listAll().size();
        assertEquals("book list count", 3, bookListSize);
        assertTrue(result);
    }

    @Test
    public void should_do_nothing_when_no_name_match_and_return_false() {
        BookService bookService = new BookServiceMockImpl();

        boolean result = bookService.checkout("No that books");

        int bookListSize = bookService.listAll().size();
        assertEquals("book list count", 4, bookListSize);
        assertFalse(result);
    }
}
