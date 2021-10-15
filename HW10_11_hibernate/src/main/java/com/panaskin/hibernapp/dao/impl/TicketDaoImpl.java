package com.panaskin.hibernapp.dao.impl;

import com.panaskin.hibernapp.dao.TicketDao;
import com.panaskin.hibernapp.entity.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionManager;

import java.util.UUID;

@Repository
public class TicketDaoImpl implements TicketDao {
    @Autowired
    private TransactionManager transactionManager;

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public void addTicket(Ticket ticket) {
        Session session = sessionFactory.getObject().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Can't find ticket" + e);
        } finally {
            session.close();
        }
    }

    @Override
    public void updateTicket(String ticketId, Ticket ticket) {
        Session session = sessionFactory.getObject().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Ticket oldTicket = session.get(Ticket.class, ticket.getId());
            session.evict(oldTicket);
            session.update(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println(e);
            }
        } finally {
            session.close();
        }

    }

    @Override
    public void deleteTicket(Ticket ticket) {
        Session session = sessionFactory.getObject().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println(e);
            }
        } finally {
            session.close();
        }
    }

    @Override
    public Ticket getTicket(String ticketId) {
        Session session = sessionFactory.getObject().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, UUID.fromString(ticketId));
            transaction.commit();
            System.out.println();
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return null;
    }
}
