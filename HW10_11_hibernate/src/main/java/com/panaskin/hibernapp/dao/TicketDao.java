package com.panaskin.hibernapp.dao;

import com.panaskin.hibernapp.entity.Ticket;

public interface TicketDao {
    void addTicket(Ticket ticket);
    void updateTicket(String ticketId, Ticket ticket);
    void deleteTicket(Ticket ticket);
    Ticket getTicket(String ticketId);
}
