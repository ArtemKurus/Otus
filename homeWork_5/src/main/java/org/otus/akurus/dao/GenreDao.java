package org.otus.akurus.dao;

import org.otus.akurus.domain.Genre;

import java.util.List;

public interface GenreDao {
    Genre createGenre(String genre);

    Genre getByGenre(String genre);

    Genre getById(long id);

    List<Genre> getAll();

    long getCount();
}
