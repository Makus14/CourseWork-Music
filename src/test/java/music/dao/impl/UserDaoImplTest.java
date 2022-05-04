package music.dao.impl;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.AccountDaoImpl;
import by.nyurush.music.dao.impl.UserDaoImpl;
import by.nyurush.music.entity.AccountRole;
import by.nyurush.music.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UserDaoImplTest {
    private final DaoHelperFactory daoHelperFactory = new DaoHelperFactory();
    private final User testUser = new User(null, "test", "test", AccountRole.CLIENT, "test", "test", "test", true);


    @Before
    @After
    public void deleteData() {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDaoImpl userDao = daoHelper.createUserDao();
            AccountDaoImpl accountDao = daoHelper.createAccountDao();
            userDao.deleteAll();
            accountDao.deleteAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDaoImpl userDao = daoHelper.createUserDao();
            User user = userDao.save(testUser);
            assertEquals(1, userDao.findAll().size());
        }
    }

    @Test
    public void findByIdPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDaoImpl userDao = daoHelper.createUserDao();
            User user = userDao.save(testUser);
            assertTrue(userDao.findById(user.getId()).isPresent());
        }
    }

    @Test
    public void saveAndDeletePositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDaoImpl userDao = daoHelper.createUserDao();
            AccountDaoImpl accountDao = daoHelper.createAccountDao();
            User result = userDao.save(testUser);
            boolean actual = result.getId() != null;
            assertTrue(actual);
            assertTrue(userDao.delete(result));
            assertTrue(accountDao.delete(result));
        }
    }

    @Test
    public void updatePositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDaoImpl userDao = daoHelper.createUserDao();
            assertNotNull(userDao.save(testUser));
        }
    }

    @Test
    public void findByLoginPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDaoImpl userDao = daoHelper.createUserDao();
            userDao.save(testUser);
            assertTrue(userDao.findByLogin("test").isPresent());
        }
    }

    @Test
    public void findByEmailPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDaoImpl userDao = daoHelper.createUserDao();
            userDao.save(testUser);
            assertTrue(userDao.findByEmail("test").isPresent());
        }
    }
}
