package com.panaskin.hibernapp.dao.impl;

import com.panaskin.hibernapp.dao.FilmSessionDao;
import com.panaskin.hibernapp.entity.FilmSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionManager;

import java.util.UUID;

@Repository
public class FilmSessionDaoImpl implements FilmSessionDao {
    @Autowired
    private TransactionManager transactionManager;

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public void addFilmSession(FilmSession filmSession) {
        Session session = sessionFactory.getObject().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(filmSession);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void updateFilmSession(String filmSessionId, FilmSession filmSession) {
        Session session = sessionFactory.getObject().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            FilmSession oldFilmSession = session.get(FilmSession.class, UUID.fromString(filmSessionId));
            session.evict(oldFilmSession);
            session.update(filmSession);
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
    public void deleteFilmSession(FilmSession filmSession) {
        Session session = sessionFactory.getObject().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(filmSession);
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
    public FilmSession getFilmSession(String filmSessionId) {
        Session session = sessionFactory.getObject().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            FilmSession filmSession = session.get(FilmSession.class, UUID.fromString(filmSessionId));
            transaction.commit();
            return filmSession;
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