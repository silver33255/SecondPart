package com.panaskin.hibernapp.dao.impl;

import com.panaskin.hibernapp.dao.UserDao;
import com.panaskin.hibernapp.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionManager;

import java.util.UUID;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private TransactionManager transactionManager;

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Cacheable(value = "usersCache", key= "#user")
    public void addUser(User user) {
        System.out.println("Adding user into base");
        Session session = sessionFactory.getObject().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Add user was fault" + e);
        } finally {
            session.close();
        }
    }

    @Override
    @CacheEvict(value = "usersCache", key = "#user.id", beforeInvocation = true)
    public void deleteUser(User user) {
        Session session = sessionFactory.getObject().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(user);
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

    @CacheEvict(value = "usersCache", key = "#id", beforeInvocation = true)
    public void deleteById(String id){
        Session session = sessionFactory.getObject().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = session.get(User.class, UUID.fromString(id));
            session.delete(user);
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
    @Cacheable(value = "usersCache")
    public void updateUser(String username, User user) {
        Session session = sessionFactory.getObject().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user1 = session.get(User.class, user.getId());
            session.evict(user1);
            session.update(user);
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
    @Cacheable(value = "usersCache", key = "#id")
    public User getUser(UUID id) {
        System.out.println("Receive from Database by id: " + id);
        Session session = sessionFactory.getObject().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            transaction.commit();
            return user;
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
