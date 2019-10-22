package org.otus.akurus.dao;

import org.otus.akurus.domain.Author;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AuthorDao {
    Author getByName(String authorName);

    Author createAuthor(String authorName);

    Author createAuthor(String authorName, String countryName, LocalDate dateOfBirth);

    Author getById(long id);

    List<Author> getAll();

    long getCount();
}
