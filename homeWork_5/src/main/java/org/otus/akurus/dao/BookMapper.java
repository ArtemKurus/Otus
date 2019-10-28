package org.otus.akurus.dao;

import org.otus.akurus.domain.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        String title = resultSet.getString("title");
        long genreId = resultSet.getLong("genre");
        long authorId = resultSet.getLong("author");
        return new Book(id, title, genreId, authorId);
    }
}
