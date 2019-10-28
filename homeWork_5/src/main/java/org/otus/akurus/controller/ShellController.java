package org.otus.akurus.controller;


import lombok.RequiredArgsConstructor;
import org.otus.akurus.domain.Book;
import org.otus.akurus.service.ServiceLibrary;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@RequiredArgsConstructor
@ShellComponent
public class ShellController {
    private final ServiceLibrary serviceLibrary;

    @ShellMethod(value = "get book by title", key = {"getbookbytitle", "gt"})
    public String getBookByTitle(String title) {
        Book book = serviceLibrary.findByTitle(title);
        String result = "Book not found";
        if (book != null)
            result = "title: " + book.getTitle() + " author:" + book.getAuthor() + " genre:" + book.getGenre();

        return result;
    }

    @ShellMethod(value = "get all books", key = {"allbook", "all"})
    public void getAllBooks() {
        List<Book> books = serviceLibrary.getAllBooks();
        books.forEach(book -> {
            System.out.println("title: " + book.getTitle() + " author:" + book.getAuthor() + " genre:" + book.getGenre());
        });
    }

    @ShellMethod(value = "get books by author", key = {"getbookbyauthor", "ga"})
    public void getBookByAuthor(String author) {
        List<Book> books = serviceLibrary.findByAuthor(author);
        books.forEach(book -> {
            System.out.println("title: " + book.getTitle() + " author:" + book.getAuthor() + " genre:" + book.getGenre());
        });
    }

    @ShellMethod(value = "add book", key = {"addbook", "add"})
    public void addBook(String title, String genre, String authorName) {
        Book book = serviceLibrary.addBook(title, genre, authorName);
        System.out.println(" Book is add. title: " + book.getTitle() + " author:" + book.getAuthor() + " genre:" + book.getGenre());
    }

    @ShellMethod(value = "delete book by title", key = {"deletebook", "del"})
    public void deleteBookByTitle(String title) {
        System.out.println(" Book was add. title: " + title);
    }
}
