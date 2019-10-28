package org.otus.akurus.service;

import jdk.nashorn.internal.ir.ReturnNode;
import lombok.AllArgsConstructor;
import org.otus.akurus.dao.AuthorDao;
import org.otus.akurus.dao.BookDao;
import org.otus.akurus.dao.GenreDao;
import org.otus.akurus.domain.Author;
import org.otus.akurus.domain.Book;
import org.otus.akurus.domain.Genre;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ServiceLibraryImpl implements ServiceLibrary {

    private final GenreDao genreDao;
    private final BookDao bookDao;
    private final AuthorDao authorDao;

    @Override
    public Book addBook(String title, String genre, String authorName) {
        Genre actualGenre = genreDao.getByGenre(genre);

        if (actualGenre == null)
            actualGenre = genreDao.createGenre(genre);

        Author author = authorDao.getByName(authorName);

        if (author == null)
            author = authorDao.createAuthor(authorName);

        Book actualBook = bookDao.getByTitle(title);

        if (actualBook == null)
            actualBook = bookDao.createBook(title, actualGenre, author);

        return actualBook;

    }

    @Override
    public void deleteBook(String title) {
        bookDao.delete(title);
    }

    @Override
    public Book findByTitle(String title) {
        return bookDao.getByTitle(title);
    }

    @Override
    public List<Book> findByGenre(String genre) {
        Genre actualGenre = genreDao.getByGenre(genre);
        if (actualGenre == null)
            return Collections.emptyList();
        return bookDao.getAll().stream().filter(book -> book.getGenre() == actualGenre.getId()).collect(Collectors.toList());
    }

    @Override
    public List<Book> findByAuthor(String authorName) {
        Author author = authorDao.getByName(authorName);
        if (author == null)
            return Collections.emptyList();
        return bookDao.getAll().stream().filter(book -> book.getAuthor() == author.getId()).collect(Collectors.toList());
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    @Override
    public long bookCount() {
        return bookDao.getCount();
    }

}
