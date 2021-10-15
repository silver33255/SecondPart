package com.panaskin.hibernapp.dao;
import com.panaskin.hibernapp.AbstractIntegrationTest;
import com.panaskin.hibernapp.entity.User;
import com.panaskin.hibernapp.entity.enums.Role;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

public class UserDaoIT extends AbstractIntegrationTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Test
    public void shouldCreateUser() {
        User expectedUser = fillTestDataUser(new User());
        userDao.addUser(expectedUser);
        User receivedUser = userDao.getUser(expectedUser.getId());
        Assertions.assertEquals(expectedUser, receivedUser);
    }

    @Test
    public void shouldDeleteUser() {
        Session session = sessionFactory.getObject().openSession();
        User user = fillTestDataUser(new User());
        userDao.addUser(user);
        User receivedUser = userDao.getUser(user.getId());
        Assertions.assertEquals(user, receivedUser);
        userDao.deleteUser(receivedUser);
        Assertions.assertNull(userDao.getUser(user.getId()));
    }

    @Test
    public void shouldUpdateUser() {
        Session session = sessionFactory.getObject().openSession();
        User user = fillTestDataUser(new User());
        userDao.addUser(user);
        User receivedUser = userDao.getUser(user.getId());
        user.setLastName("updatedLastName");
        userDao.updateUser(receivedUser.getId().toString(), user);
        Query<User> query = session.createQuery("From User where id = :userId", User.class);
        query.setParameter("userId", receivedUser.getId());
        Assertions.assertEquals(query.getSingleResult().getLastName(), "updatedLastName");
    }

    @Test
    public void shouldCheckUserInCacheAfterAddMethod() {
        User user = new User();
        userDao.addUser(user);
        userDao.getUser(user.getId());
        Cache usersCache = CacheManager.ALL_CACHE_MANAGERS.get(0)
                .getCache("usersCache");
        Element element = usersCache.get(user.getId());
        Assertions.assertEquals(user, element.getObjectValue());
    }

    @Test
    public void shouldCheckUserInCacheAfterGetMethod() {
        User user = new User();
        userDao.addUser(user);
        userDao.getUser(user.getId());
        Cache usersCache = CacheManager.ALL_CACHE_MANAGERS.get(0)
                .getCache("usersCache");
        Element element = usersCache.get(user.getId());
        Assertions.assertEquals(user, element.getObjectValue());
    }

    @Test
    public void shouldCheckNoUserInCacheAfterDeleteMethod() {
        User user = new User();
        userDao.addUser(user);
        Cache usersCache = CacheManager.ALL_CACHE_MANAGERS.get(0)
                .getCache("usersCache");
        System.out.println("=========================");
        System.out.println(userDao.getUser(user.getId()));
        System.out.println(usersCache.get(user.getId()));
        System.out.println("=========================");
        userDao.deleteUser(user);
        Element element1 = usersCache.get(user.getId());
        Assertions.assertNull(element1);
    }

    @Test
    public void shouldUpdateUserName() {
        User user = fillTestDataUser(new User());
        userDao.addUser(user);
        User firstUser = userDao.getUser(user.getId());
        firstUser.setFirstName("userName");
        userDao.updateUser(user.getId().toString(), user);
        User secondUser = userDao.getUser(user.getId());
        Assertions.assertEquals(firstUser.getFirstName(), secondUser.getFirstName());
    }

    @Test
    public void createTwoUsersSecondShouldExist() {
        User user = new User();
        userDao.addUser(user);
        User user1 = userDao.getUser(user.getId());
        User user2 = new User();
        userDao.addUser(user2);
        System.out.println(user2.getId());
        User persistentUser = userDao.getUser(user2.getId());
        System.out.println(persistentUser.getId());
        System.out.println(persistentUser);
        Assertions.assertNotNull(persistentUser);
        Assertions.assertEquals(user2, persistentUser);
    }

    private static User fillTestDataUser(User user) {
        user.setFirstName("nameTest");
        user.setPassword("passTest");
        user.setRole(Role.User);
        user.setEmail("test@mail.ru");
        user.setLastName("lastTest");
        return user;
    }
}
