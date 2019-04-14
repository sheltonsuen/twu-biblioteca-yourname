package com.twu.biblioteca.service.impl;

import com.twu.biblioteca.domain.Describable;
import com.twu.biblioteca.service.BorrowAbleService;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.*;

public class MovieServiceMockImplTest {

    @Test
    public void should_list_all_movies_that_available() {
        BorrowAbleService borrowAbleService = new MovieServiceMockImpl();

        List<Describable> movieList = borrowAbleService.listAll();

        assertEquals("all movies count", 3, movieList.size());
    }

    @Test
    public void should_ignore_movies_that_unavailable() {
        MovieServiceMockImpl movieServiceMock = new MovieServiceMockImpl();
        movieServiceMock.getMovieMap().get("The Shawshank Redemption").setAvailable(false);

        List<Describable> movieList = movieServiceMock.listAll();

        assertEquals("all movies count", 2, movieList.size());
    }

    @Test
    public void should_checkout_movie_when_checkout_success() {
        BorrowAbleService borrowAbleService = new MovieServiceMockImpl();

        borrowAbleService.checkout("The Shawshank Redemption");
        List<Describable> movieList = borrowAbleService.listAll();

        assertEquals("movies after checkout", 2, movieList.size());
    }

    @Test
    public void should_return_true_when_checkout_success() {
        BorrowAbleService borrowAbleService = new MovieServiceMockImpl();

        boolean checkoutResult = borrowAbleService.checkout("The Shawshank Redemption");

        assertTrue(checkoutResult);
    }

    @Test
    public void should_return_false_when_checkout_fails() {
        BorrowAbleService borrowAbleService = new MovieServiceMockImpl();

        boolean checkoutResult = borrowAbleService.checkout("Mock Movie");

        assertFalse(checkoutResult);
    }

    @Test
    public void should_return_back_the_movie_when_return_back_success() {
        MovieServiceMockImpl movieServiceMock = new MovieServiceMockImpl();
        movieServiceMock.getMovieMap().get("The Shawshank Redemption").setAvailable(false);

        movieServiceMock.returnBack("The Shawshank Redemption");
        List<Describable> movieList = movieServiceMock.listAll();

        assertEquals("movies count", 3, movieList.size());
    }

    @Test
    public void should_return_true_when_return_back_success() {
        BorrowAbleService borrowAbleService = new MovieServiceMockImpl();

        boolean returnResult = borrowAbleService.returnBack("The Shawshank Redemption");

        assertTrue(returnResult);
    }

    @Test
    public void should_return_false_when_return_back_fails() {
        BorrowAbleService borrowAbleService = new MovieServiceMockImpl();

        boolean returnResult = borrowAbleService.returnBack("Mock Movie");

        assertFalse(returnResult);
    }
}
