package org.otus.akurus.dao;

import lombok.AllArgsConstructor;
import org.otus.akurus.domain.Genre;
import org.otus.akurus.utils.ThrowEmptyResultDataAccess;
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
public class GenreDaoImpl implements GenreDao {

    private final JdbcOperations jdbcOperations;
    private final NamedParameterJdbcOperations jdbcOperationsNamed;

    @Override
    public Genre createGenre(String genre) {
        MapSqlParameterSource ps = new MapSqlParameterSource();
        ps.addValue("name", genre);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperationsNamed.update("insert into genre (name) values (:name)", ps, keyHolder);
        return new Genre((Long) keyHolder.getKey(), genre);
    }

    @Override
    @ThrowEmptyResultDataAccess
    public Genre getByGenre(String genreName) {
        Map<String, Object> params = Collections.singletonMap("name", genreName);
        return jdbcOperationsNamed.queryForObject("select * from genre where name = :name", params, new GenreMapper());
    }

    @Override
    public long getCount() {
        return jdbcOperations.queryForObject("select count(*) from genre", Long.class);
    }

    @Override
    @ThrowEmptyResultDataAccess
    public Genre getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbcOperationsNamed.queryForObject("select * from genre where id = :id", params, new GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        return jdbcOperations.query("select * from genre", new GenreMapper());
    }
}
