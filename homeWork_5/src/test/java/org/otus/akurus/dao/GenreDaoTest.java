package org.otus.akurus.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.otus.akurus.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@DisplayName("Jdbc tests....")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(GenreDaoImpl.class)
public class GenreDaoTest {

    private static final long EXPECTED_GENRE_COUNT = 1;
    private static final long DEFAULT_VALUE_ID = 1;
    private static final String DEFAULT_GENRE_NAME = "novel";

    @Autowired
    private GenreDao genreDao;


    @Test
    @DisplayName("insert new genre")
    void createNewGenre() {
        Assertions.assertThat(genreDao.createGenre("notebook").getName()).isEqualTo("notebook");
    }

    @Test
    @DisplayName("should return genre by Id")
    void shouldReturnGenreById() {
        Assertions.assertThat(genreDao.getById(DEFAULT_VALUE_ID)).hasFieldOrPropertyWithValue("name", DEFAULT_GENRE_NAME);
    }

    @Test
    @DisplayName("should return id by genre")
    void shouldReturnIdByGenre() {
        Assertions.assertThat(genreDao.getByGenre(DEFAULT_GENRE_NAME)).hasFieldOrPropertyWithValue("id", DEFAULT_VALUE_ID);
    }

    @Test
    @DisplayName("should return all genre")
    void shouldReturnAllGenre() {
        List<Genre> genreList = genreDao.getAll();
        Assertions.assertThat(genreList).hasSize((int) EXPECTED_GENRE_COUNT);
        Assertions.assertThat(genreList).extracting(Genre::getName).containsExactly(DEFAULT_GENRE_NAME);
    }

    @Test
    @DisplayName("should return all genre")
    void shouldReturnCountGenre() {
        Assertions.assertThat(genreDao.getCount()).isEqualTo(EXPECTED_GENRE_COUNT);
    }

}

