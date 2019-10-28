package org.otus.akurus.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.otus.akurus.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@DisplayName("Jdbc tests....")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(AuthorDaoImpl.class)
public class AuthorDaoTest {

    private static final long DEFAULT_VALUE_ID = 1;
    private static final String DEFAULT_AUTHOR = "Stephen Edwin King";
    private static final String NEW_AUTHOR = "George Orwell";
    private static final String DEFAULT_COUNTRY_NAME = "NONE";
    private static final long EXPECTED_BOOK_COUNT = 1;

    @Autowired
    private AuthorDao authorDao;

    @Test
    @DisplayName("should return Author by id ")
    void shouldReturnCorrectAuthorById() {
        Assertions.assertThat(authorDao.getById(DEFAULT_VALUE_ID)).hasFieldOrPropertyWithValue("name", DEFAULT_AUTHOR);
    }

    @Test
    @DisplayName("should return Author by name ")
    void shouldReturnCorrectAuthorByTitle() {
        Assertions.assertThat(authorDao.getByName(DEFAULT_AUTHOR)).hasFieldOrPropertyWithValue("name", DEFAULT_AUTHOR);
    }

    @Test
    @DisplayName("should return count")
    void shouldReturnCountAuthor() {
        Assertions.assertThat(authorDao.getCount()).isEqualTo(EXPECTED_BOOK_COUNT);
    }

    @Test
    @DisplayName("should return count all Author")
    void shouldReturnALLBooks() {
        List<Author> authorList = authorDao.getAll();
        Assertions.assertThat(authorList).hasSize((int) EXPECTED_BOOK_COUNT);
        Assertions.assertThat(authorList).extracting(Author::getName).containsExactly(DEFAULT_AUTHOR);
    }

    @Test
    @DisplayName("insert new Author")
    void createNewAuthorDefault() {
        Author george_orwell = authorDao.createAuthor(NEW_AUTHOR);
        Assertions.assertThat(george_orwell).extracting(Author::getName).isEqualTo(NEW_AUTHOR);
        Assertions.assertThat(george_orwell).extracting(Author::getCountryName).isEqualTo(DEFAULT_COUNTRY_NAME);
        Assertions.assertThat(george_orwell).extracting(Author::getDateOfBirth).isEqualTo(LocalDate.MIN);
    }
}
