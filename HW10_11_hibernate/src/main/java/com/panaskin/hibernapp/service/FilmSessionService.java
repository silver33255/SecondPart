package com.panaskin.hibernapp.service;

import com.panaskin.hibernapp.entity.Film;
import com.panaskin.hibernapp.entity.FilmSession;
import com.panaskin.hibernapp.entity.Ticket;

public interface FilmSessionService {
    void persistFilmSessionWithFilm(FilmSession filmSession, Film film);
    void addTicketsToFilmSessionPersist(FilmSession filmSession, Ticket... ticket);
}
