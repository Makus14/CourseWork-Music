package music.dao.impl;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.AccountDaoImpl;
import by.nyurush.music.entity.Account;
import by.nyurush.music.entity.AccountRole;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountDaoImplTest {
    private final DaoHelperFactory daoHelperFactory = new DaoHelperFactory();
    private final Account testAccount = new Account(null, "test", "test", AccountRole.CLIENT);

    @Before
    @After
    public void deleteData() {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AccountDaoImpl accountDao = daoHelper.createAccountDao();
            accountDao.deleteAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AccountDaoImpl accountDao = daoHelper.createAccountDao();
            accountDao.save(testAccount);
            boolean actual = accountDao.findAll().size() == 1;
            assertTrue(actual);
        }
    }

    @Test
    public void findByIdPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AccountDaoImpl accountDao = daoHelper.createAccountDao();
            Account account = accountDao.save(testAccount);
            assertEquals(accountDao.findById(account.getId()).get(), account);
        }
    }

    @Test
    public void findByIdNegativeTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AccountDaoImpl accountDao = daoHelper.createAccountDao();
            boolean actual = accountDao.findById(-1).isPresent();
            assertFalse(actual);
        }
    }

    @Test
    public void saveAndDeletePositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AccountDaoImpl accountDao = daoHelper.createAccountDao();
            Account result = saveAccount(testAccount, accountDao);
            boolean actual = result.getId() != null;
            assertTrue(actual);
            assertTrue(deleteAccount(testAccount, accountDao));
        }
    }

    private Account saveAccount(Account account, AccountDaoImpl accountDao) throws DaoException {
        return accountDao.save(account);
    }

    private boolean deleteAccount(Account account, AccountDaoImpl accountDao) throws DaoException {
        return accountDao.delete(account);
    }

    @Test
    public void findByLoginPositiveTest() throws DaoException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AccountDaoImpl accountDao = daoHelper.createAccountDao();
            accountDao.save(testAccount);
            boolean actual = accountDao.findByLogin("test").isPresent();
            assertTrue(actual);
        }
    }
}
