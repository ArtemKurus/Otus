package org.otus.akurus.dao;

import org.otus.akurus.domain.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String countryName = resultSet.getString("countryName");
        Date dateOfBirth = resultSet.getDate("dateOfBirth");
        return new Author(id, name, countryName, ((java.sql.Date) dateOfBirth).toLocalDate());
    }
}
