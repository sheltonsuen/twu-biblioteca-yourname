package com.twu.biblioteca.service.Impl;

import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.impl.BookServiceMockImpl;
import com.twu.biblioteca.domain.Book;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class BookServiceMockImplTest {

    @Test
    public void should_list_all_books_as_list_when_call_list_all() {
        BookService bookService = new BookServiceMockImpl();

        List<Book> actualBookList = bookService.listAll();

        assertEquals("list all books", 4, actualBookList.size());
    }
}
