package org.otus.akurus.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.otus.akurus.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DisplayName("Service tests....")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ServiceLibtatyTest {

    private static final String AUTHOR = "George Orwell";
    private static final String NEW_GENRE_NAME = "comedy";
    private static final String GENRE_NAME = "novel";
    private static final String NEW_TITLE = "1984";
    private static final String DEFAULT_TITLE = "It";
    private static final String DEFAULT_AUTHOR = "Stephen Edwin King";

    @Autowired
    private ServiceLibrary serviceLibrary;


    @Test
    @DisplayName("add and delete book in Library")
    void addBookTest() {
        Book book = serviceLibrary.addBook(NEW_TITLE, NEW_GENRE_NAME, AUTHOR);
        Assertions.assertThat(book).extracting(Book::getTitle).isEqualTo(NEW_TITLE);
        serviceLibrary.deleteBook(NEW_TITLE);
        Assertions.assertThat(serviceLibrary.findByTitle(NEW_TITLE)).isNull();
    }

    @Test
    @DisplayName("add book in Library")
    void findByTitleBookTest() {
        Assertions.assertThat(serviceLibrary.findByTitle(DEFAULT_TITLE)).extracting(Book::getTitle).isEqualTo(DEFAULT_TITLE);
    }

    @Test
    @DisplayName("Find by genre in Library")
    void findByGenreBookTest() {
        Assertions.assertThat(serviceLibrary.findByGenre(GENRE_NAME)).extracting(Book::getTitle).containsExactly(DEFAULT_TITLE);
    }

    @Test
    @DisplayName("find by Author in Library")
    void findByAuthorBookTest() {
        Assertions.assertThat(serviceLibrary.findByAuthor(DEFAULT_AUTHOR)).extracting(Book::getTitle).containsExactly(DEFAULT_TITLE);
    }
}
