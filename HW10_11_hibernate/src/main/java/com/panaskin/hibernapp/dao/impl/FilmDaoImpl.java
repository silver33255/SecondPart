package com.panaskin.hibernapp.dao.impl;

import com.panaskin.hibernapp.dao.FilmDao;
import com.panaskin.hibernapp.entity.Film;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionManager;

import java.util.UUID;

@Repository
public class FilmDaoImpl implements FilmDao {
    @Autowired
    private TransactionManager transactionManager;

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public void addFilm(Film film) {
        Session session = sessionFactory.getObject().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(film);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Can't find film" + e);
        } finally {
            session.close();
        }
    }

    @Override
    public void updateFilm(String filmId, Film film) {
        Session session = sessionFactory.getObject().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Film oldFilm = session.get(Film.class, film.getId());
            session.evict(oldFilm);
            session.update(film);
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
    public void deleteFilm(Film location) {
        Session session = sessionFactory.getObject().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(location);
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
    public Film getFilm(String filmId) {
        Session session = sessionFactory.getObject().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Film film = session.get(Film.class, UUID.fromString(filmId));
            transaction.commit();
            System.out.println();
            return film;
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