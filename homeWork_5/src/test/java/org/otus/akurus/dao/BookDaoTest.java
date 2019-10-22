package org.otus.akurus.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.otus.akurus.domain.Author;
import org.otus.akurus.domain.Book;
import org.otus.akurus.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@DisplayName("Jdbc tests....")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(BookDaoImpl.class)
public class BookDaoTest {

    private static final long DEFAULT_VALUE_ID = 1;
    private static final String DEFAULT_TITLE = "It";
    private static final long EXPECTED_BOOK_COUNT = 1;
    private static final String NEW_AUTHOR = "George Orwell";
    private static final String DEFAULT_GENRE_NAME = "novel";
    private static final String DEFAULT_COUNTRY_NAME = "Motihari";
    private static final String NEW_TITLE = "1984";

    @Autowired
    private BookDao bookDao;

    @Test
    @DisplayName("should return book by author id ")
    void shouldReturnCorrectAuthorById() {
        Assertions.assertThat(bookDao.getById(DEFAULT_VALUE_ID)).hasFieldOrPropertyWithValue("title", DEFAULT_TITLE);
    }

    @Test
    @DisplayName("should return book by title ")
    void shouldReturnCorrectAuthorByTitle() {
        Assertions.assertThat(bookDao.getByTitle(DEFAULT_TITLE)).hasFieldOrPropertyWithValue("title", DEFAULT_TITLE);
    }

    @Test
    @DisplayName("should return count books")
    void shouldReturnCountBooks() {
        Assertions.assertThat(bookDao.getCount()).isEqualTo(EXPECTED_BOOK_COUNT);
    }

    @Test
    @DisplayName("should return all books")
    void shouldReturnAllBooks() {
        List<Book> bookList = bookDao.getAll();
        Assertions.assertThat(bookList).hasSize((int) EXPECTED_BOOK_COUNT);
        Assertions.assertThat(bookList).extracting(Book::getTitle).containsExactly(DEFAULT_TITLE);
    }


    @Test
    @DisplayName("insert new book")
    void createNewBook() {
        Book book = bookDao.createBook(NEW_TITLE, new Genre(DEFAULT_VALUE_ID, DEFAULT_GENRE_NAME), new Author(DEFAULT_VALUE_ID, NEW_AUTHOR, DEFAULT_COUNTRY_NAME, LocalDate.parse("1903-06-25")));
        Assertions.assertThat(book).extracting(Book::getTitle).isEqualTo(NEW_TITLE);
    }

}
