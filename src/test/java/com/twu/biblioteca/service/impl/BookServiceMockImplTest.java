package com.twu.biblioteca.service.impl;

import com.twu.biblioteca.domain.Describable;
import com.twu.biblioteca.service.BorrowAbleService;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.*;

public class BookServiceMockImplTest {

    @Test
    public void should_list_all_books_as_list_when_call_list_all() {
        BorrowAbleService borrowAbleService = new BookServiceMockImpl();

        List<Describable> actualBookList = borrowAbleService.listAll();

        assertEquals("list all books", 4, actualBookList.size());
    }

    @Test
    public void should_checkout_the_book_that_match_the_name_and_return_true() {
        BorrowAbleService borrowAbleService = new BookServiceMockImpl();

        boolean result = borrowAbleService.checkout("Journey to the West Wu");

        int bookListSize = borrowAbleService.listAll().size();
        assertEquals("book list count", 3, bookListSize);
        assertTrue(result);
    }

    @Test
    public void should_do_nothing_when_no_name_match_and_return_false() {
        BorrowAbleService borrowAbleService = new BookServiceMockImpl();

        boolean result = borrowAbleService.checkout("No that books");

        int bookListSize = borrowAbleService.listAll().size();
        assertEquals("book list count", 4, bookListSize);
        assertFalse(result);
    }

    @Test
    public void should_add_book_to_book_list_when_return_book_success_and_return_true() {
        BorrowAbleService borrowAbleService = new BookServiceMockImpl();

        boolean checkoutResult = borrowAbleService.checkout("Journey to the West Wu");
        assertTrue(checkoutResult);

        boolean returnResult = borrowAbleService.returnBook("Journey to the West Wu");

        List<Describable> bookList = borrowAbleService.listAll();
        assertEquals("book list count", 4, bookList.size());
        assertEquals("book name", "Journey to the West Wu    Cheng'en    1503", bookList.get(1).briefSelfIntroduce());
        assertTrue(returnResult);
    }

    @Test
    public void should_not_add_book_to_book_list_when_return_book_unsuccess_and_return_false() {
        BorrowAbleService borrowAbleService = new BookServiceMockImpl();

        boolean checkoutResult = borrowAbleService.checkout("Journey to the West Wu");
        assertTrue(checkoutResult);

        boolean returnResult = borrowAbleService.returnBook("wrong book name");

        List<Describable> bookList = borrowAbleService.listAll();
        assertEquals("book list count", 3, bookList.size());
        assertFalse(returnResult);
    }
}
