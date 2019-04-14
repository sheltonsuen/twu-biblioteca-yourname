package com.twu.biblioteca.service.impl;

import com.google.common.collect.ImmutableMap;
import com.twu.biblioteca.domain.Describable;
import com.twu.biblioteca.domain.Movie;
import com.twu.biblioteca.service.BorrowAbleService;
import lombok.Getter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class MovieServiceMockImpl implements BorrowAbleService {

    private Map<String, Movie> movieMap = ImmutableMap.of(
            "A Beautiful Mind", new Movie("A Beautiful Mind", 2001, "Ron Howard", 3),
            "Forrest Gump", new Movie("Forrest Gump", 1994, "Robert Zemeckis", 2),
            "The Shawshank Redemption", new Movie("The Shawshank Redemption", 1994, "Stephen Edwin King", 1));

    @Override
    public List<Describable> listAll() {
        return new ArrayList<>(movieMap.values()).stream().filter(Movie::getAvailable).collect(Collectors.toList());
    }

    @Override
    public boolean checkout(String name) {
        Movie movie = movieMap.get(name);

        if (movie == null) {
            return false;
        }

        movie.setAvailable(false);
        return true;
    }

    @Override
    public boolean returnBack(String name) {
        Movie movie = movieMap.get(name);

        if (movie == null) {
            return false;
        }

        movie.setAvailable(true);
        return true;
    }
}
