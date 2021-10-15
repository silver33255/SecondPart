package com.panaskin.hibernapp.service.impl;

import com.panaskin.hibernapp.dao.FilmDao;
import com.panaskin.hibernapp.dao.FilmSessionDao;
import com.panaskin.hibernapp.dao.TicketDao;
import com.panaskin.hibernapp.entity.Film;
import com.panaskin.hibernapp.entity.FilmSession;
import com.panaskin.hibernapp.entity.Ticket;
import com.panaskin.hibernapp.service.FilmSessionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class FilmSessionServiceImpl implements FilmSessionService {
    @Autowired
    private FilmSessionDao filmSessionDao;

    @Autowired
    private FilmDao filmDao;

    @Autowired
    private TicketDao ticketDao;


    @Override
    public void persistFilmSessionWithFilm(FilmSession filmSession, Film film) {
        filmSession.setFilm(film);
        filmSessionDao.addFilmSession(filmSession);
    }

    @Override
    public void addTicketsToFilmSessionPersist(FilmSession filmSession, Ticket... tickets) {
        List<Ticket> filmSessionTickets = filmSession.getTickets();
        filmSessionTickets.addAll(Arrays.asList(tickets));
        for (Ticket ticket : filmSessionTickets) {
            ticket.setFilmSession(filmSession);
        }
        filmSessionDao.addFilmSession(filmSession);
    }
}
