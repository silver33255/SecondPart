package com.panaskin.hibernapp.dao;

import com.panaskin.hibernapp.AbstractIntegrationTest;
import com.panaskin.hibernapp.entity.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

public class TicketDaoIT extends AbstractIntegrationTest {
    @Autowired
    private TicketDao ticketDao;
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Test
    public void shouldReceiveTicketWithSeatsNumberTwoWhenAddTicket() {
        //GIVEN
        Ticket ticket = new Ticket();
        ticket.setSeatNumber("2");
        String ticketId = ticket.getId().toString();
        //WHEN
        ticketDao.addTicket(ticket);
        //THEN
        Assertions.assertEquals("2", ticketDao.getTicket(ticketId).getSeatNumber());
    }

    @Test
    public void shouldReceiveTicketWithNewSeatNumberThenUpdateTicket() {
        //GIVEN
        Ticket ticket = new Ticket();
        ticket.setSeatNumber("3");
        String expectedSeatNumber = "4";
        ticketDao.addTicket(ticket);
        ticket.setSeatNumber(expectedSeatNumber);
        //WHEN
        ticketDao.updateTicket(ticket.getId().toString(), ticket);
        //THEN
        Assertions.assertEquals(expectedSeatNumber, ticket.getSeatNumber());
    }

    @Test
    public void shouldReceiveNullWhenTicketDelete() {
        Ticket ticket = new Ticket();
        ticketDao.addTicket(ticket);
        ticketDao.deleteTicket(ticket);
        Assertions.assertNull(ticketDao.getTicket(ticket.getId().toString()));

    }
}
