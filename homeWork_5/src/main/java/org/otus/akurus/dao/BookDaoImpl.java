package org.otus.akurus.dao;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.otus.akurus.domain.Author;
import org.otus.akurus.domain.Book;
import org.otus.akurus.domain.Genre;
import org.otus.akurus.utils.ThrowEmptyResultDataAccess;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class BookDaoImpl implements BookDao {

    private final JdbcOperations jdbcOperations;
    private final NamedParameterJdbcOperations jdbcOperationsNamed;

    @Override
    public Book createBook(String title, Genre genre, Author author) {
        MapSqlParameterSource ps = new MapSqlParameterSource();
        ps.addValue("title", title);
        ps.addValue("genreId", genre.getId());
        ps.addValue("authorId", author.getId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperationsNamed.update("insert into book (title,genre, author) values (:title, :genreId, :authorId)", ps, keyHolder);
        return new Book((Long)keyHolder.getKey(), title, genre.getId(), author.getId());
    }

    @Override
    @ThrowEmptyResultDataAccess
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbcOperationsNamed.queryForObject("select * from book where id = :id", params, new BookMapper());
    }

    @Override
    @ThrowEmptyResultDataAccess
    public Book getByTitle(String title) {
        Map<String, Object> params = Collections.singletonMap("title", title);
        return jdbcOperationsNamed.queryForObject("select * from book where title = :title", params, new BookMapper());
    }

    @Override
    public long getCount() {
        return jdbcOperations.queryForObject("select count(*) from book", Long.class);
    }

    @Override
    public List<Book> getAll() {
        return jdbcOperations.query("select * from book", new BookMapper());
    }

    @Override
    public void delete(String title) {
        Book bookByTitle = getByTitle(title);
        if(bookByTitle != null){
            MapSqlParameterSource ps = new MapSqlParameterSource();
            ps.addValue("id", bookByTitle.getId());
            jdbcOperationsNamed.update("delete from book where id = :id", ps);
        }
    }

}
