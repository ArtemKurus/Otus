package org.otus.akurus.dao;

import lombok.AllArgsConstructor;
import org.otus.akurus.domain.Author;
import org.otus.akurus.utils.ThrowEmptyResultDataAccess;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class AuthorDaoImpl implements AuthorDao {

    private final JdbcOperations jdbcOperations;
    private final NamedParameterJdbcOperations jdbcOperationsNamed;

    @Override
    @ThrowEmptyResultDataAccess
    public Author getByName(String authorName) {
        Map<String, Object> params = Collections.singletonMap("name", authorName);
        return jdbcOperationsNamed.queryForObject("select * from author where name = :name", params, new AuthorMapper());
    }

    @Override
    public Author createAuthor(String authorName) {
        return createAuthor(authorName, "NONE", LocalDate.MIN);
    }

    @Override
    public Author createAuthor(String authorName, String countryName, LocalDate dateOfBirth) {
        MapSqlParameterSource ps = new MapSqlParameterSource();
        ps.addValue("name", authorName);
        ps.addValue("countryName", countryName);
        ps.addValue("dateOfBirth", dateOfBirth);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperationsNamed.update("insert into author (name, countryName, dateOfBirth) values(:name, :countryName, :dateOfBirth) ", ps, keyHolder);
        return new Author((Long) keyHolder.getKey(), authorName, countryName, dateOfBirth);
    }


    @Override
    @ThrowEmptyResultDataAccess
    public Author getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbcOperationsNamed.queryForObject("select * from author where id = :id", params, new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return jdbcOperations.query("select * from author", new AuthorMapper());
    }

    @Override
    public long getCount() {
        return jdbcOperations.queryForObject("select count(*) from author", Long.class);
    }

}
