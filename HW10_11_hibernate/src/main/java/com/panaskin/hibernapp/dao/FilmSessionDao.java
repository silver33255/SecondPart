package com.panaskin.hibernapp.dao;

import com.panaskin.hibernapp.entity.FilmSession;

public interface FilmSessionDao {
    void addFilmSession(FilmSession filmSession);
    void updateFilmSession(String sessionId, FilmSession filmSession);
    void deleteFilmSession(FilmSession filmSession);
    FilmSession getFilmSession(String sessionId);
}
