package dao;

import models.User;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import utils.HibernateSessionFactoryUtil;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Properties;

@Testcontainers
public class UserDaoIntegrationTest {

    @Container
    public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    private static UserDao userDao;
    private static SessionFactory testSessionFactory;

    @BeforeAll
    static void setUpAll() {
        Properties hibernateTestProperties = new Properties();
        hibernateTestProperties.setProperty("hibernate.connection.url", postgresContainer.getJdbcUrl());
        hibernateTestProperties.setProperty("hibernate.connection.username", postgresContainer.getUsername());
        hibernateTestProperties.setProperty("hibernate.connection.password", postgresContainer.getPassword());
        hibernateTestProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        hibernateTestProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        hibernateTestProperties.setProperty("hibernate.show_sql", "true");

        testSessionFactory = HibernateSessionFactoryUtil.getTestSessionFactory(hibernateTestProperties);

        userDao = new UserDao();
    }

    @AfterAll
    static void tearDownAll() {
        HibernateSessionFactoryUtil.shutdown();
    }

    @Test
    void testSaveAndFind() {
        User user = new User("TestName", 10, "email");
        userDao.save(user);
        User foundUser = userDao.findById(user.getId());
        assertNotNull(foundUser);
        assertEquals("TestName", foundUser.getName());
    }

    @Test
    void testUpdate() {
        User user = new User("OldName", 12, "emailOldName");
        userDao.save(user);
        int userId = user.getId();
        user.setName("NewName");
        userDao.update(user);
        User updatedUser = userDao.findById(userId);
        assertEquals("NewName", updatedUser.getName());
    }

    @Test
    void testDelete() {
        User user = new User("UserToDelete", 12, "emailUserToDelete");
        userDao.save(user);
        int userId = user.getId();
        userDao.delete(user);
        User deletedUser = userDao.findById(userId);
        assertNull(deletedUser);
    }

    @Test
    void testFindAll() {
        userDao.save(new User("User1", 14, "emailUser1"));
        userDao.save(new User("User2", 15, "emailUser2"));
        List<User> users = userDao.findAll();
        assertNotNull(users);
        assertEquals(2, users.size());
    }
}

