package org.otus.akurus.service;

import org.otus.akurus.domain.Book;

import java.util.List;

public interface ServiceLibrary {
    Book addBook(String title, String genre, String authorName);

    void deleteBook(String title);

    Book findByTitle(String title);

    List<Book> findByGenre(String genre);

    List<Book> findByAuthor(String authorName);

    List<Book> getAllBooks();

    long bookCount();
}
