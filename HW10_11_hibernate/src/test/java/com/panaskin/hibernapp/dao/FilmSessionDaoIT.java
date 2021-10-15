package com.panaskin.hibernapp.dao;

import com.panaskin.hibernapp.AbstractIntegrationTest;
import com.panaskin.hibernapp.entity.Film;
import com.panaskin.hibernapp.entity.FilmSession;
import com.panaskin.hibernapp.entity.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class FilmSessionDaoIT extends AbstractIntegrationTest {

    @Autowired
    private FilmSessionDao filmSessionDao;

    @Test
    public void shouldReceiveTheSameFilmSessionIdWhenAddFilmSession(){
        //GIVEN
        FilmSession filmSession = new FilmSession();
        String filmSessionId = filmSession.getId().toString();
        addTicketsToFilmSessionPersist(filmSession, receiveTestTickets());
        //WHEN
        filmSessionDao.addFilmSession(filmSession);
        //THEN
         Assertions.assertEquals(filmSessionId, filmSessionDao.getFilmSession(filmSessionId).getId().toString());
    }


    @Test
    public void shouldReceiveNullWhenDeleteFilmSession(){
        //GIVEN
        FilmSession filmSession = new FilmSession();
        String filmSessionId = filmSession.getId().toString();
        addTicketsToFilmSessionPersist(filmSession, receiveTestTickets());
        filmSessionDao.addFilmSession(filmSession);
        //WHEN
        filmSessionDao.deleteFilmSession(filmSession);
        Assertions.assertNull(filmSessionDao.getFilmSession(filmSessionId));
    }

    @Test
    public void shouldUpdateMeeting(){
        //GIVEN
        FilmSession filmSession = new FilmSession();
        String filmSessionId = filmSession.getId().toString();
        filmSession.setDescription("Simple description");
        filmSessionDao.addFilmSession(filmSession);
        filmSession.setDescription("Updated description");
        filmSessionDao.updateFilmSession(filmSessionId, filmSession);
        Assertions.assertEquals("Updated description", filmSessionDao.getFilmSession(filmSessionId).getDescription());
    }


    private void addTicketsToFilmSessionPersist(FilmSession filmSession, List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            ticket.setFilmSession(filmSession);
        }
        filmSession.getTickets().addAll(tickets);
    }

    private List<Ticket> receiveTestTickets(){
        Ticket firstTicket = new Ticket();
        firstTicket.setId(UUID.randomUUID());
        firstTicket.setRowNumber("1");
        firstTicket.setSeatNumber("2");
        Ticket secondTicket = new Ticket();
        secondTicket.setId(UUID.randomUUID());
        secondTicket.setRowNumber("1");
        secondTicket.setSeatNumber("2");
        return Arrays.asList(firstTicket, secondTicket);
    }
}
