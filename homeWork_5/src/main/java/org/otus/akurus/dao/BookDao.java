package org.otus.akurus.dao;

import org.otus.akurus.domain.Author;
import org.otus.akurus.domain.Book;
import org.otus.akurus.domain.Genre;

import java.util.List;

public interface BookDao {
    Book createBook(String title, Genre genre, Author author);

    Book getById(long id);

    Book getByTitle(String name);

    long getCount();

    List<Book> getAll();

    void delete(String title);
}
