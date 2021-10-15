package com.panaskin.hibernapp.dao;

import com.panaskin.hibernapp.entity.Film;

public interface FilmDao {
    void addFilm(Film film);
    void updateFilm(String filmId, Film film);
    void deleteFilm(Film film);
    Film getFilm(String filmId);
}
