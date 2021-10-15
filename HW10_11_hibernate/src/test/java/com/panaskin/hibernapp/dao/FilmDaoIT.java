package com.panaskin.hibernapp.dao;

import com.panaskin.hibernapp.AbstractIntegrationTest;
import com.panaskin.hibernapp.entity.Film;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class FilmDaoIT extends AbstractIntegrationTest {
    @Autowired
    private FilmDao filmDao;

    @Test
    public void shouldReceiveTheSameFilmNameWhenAddFilm(){
        //GIVEN
        Film film = new Film();
        film.setFilmName("Test film");
        //WHEN
        filmDao.addFilm(film);
        //THEN
        Assertions.assertEquals("Test film", filmDao.getFilm(film.getId().toString()).getFilmName());
    }

    @Test
    public void shouldReturnNullWhenDeleteFilm(){
        //GIVEN
        Film film = new Film();
        film.setFilmName("Test film");
        filmDao.addFilm(film);
        Assertions.assertNotNull(filmDao.getFilm(film.getId().toString()));
        //WHEN
        filmDao.deleteFilm(film);
        //THEN
        Assertions.assertNull(filmDao.getFilm(film.getId().toString()));
    }

    @Test
    public void shouldReceiveFilmWithNewNameWhenFilmUpdate(){
        //GIVEN
        Film film = new Film();
        film.setFilmName("Test film");
        filmDao.addFilm(film);
        Film givenValue = filmDao.getFilm(film.getId().toString());
        film.setFilmName("Updated film");
        //WHEN
        filmDao.updateFilm(givenValue.getId().toString(), film);
        Film updatedFilm = filmDao.getFilm(givenValue.getId().toString());
        //THEN
        Assertions.assertEquals(updatedFilm.getFilmName(), "Updated film");
    }
}
