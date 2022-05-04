package by.nyurush.music.service.impl;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.AccountDaoImpl;
import by.nyurush.music.entity.Account;
import by.nyurush.music.service.exception.ServiceException;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

public class AccountService {
    private final DaoHelperFactory daoHelperFactory = new DaoHelperFactory();

    public List<Account> findAll() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AccountDaoImpl accountDao = daoHelper.createAccountDao();
            return accountDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Account> findById(Integer id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AccountDaoImpl accountDao = daoHelper.createAccountDao();
            return accountDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Account save(Account account) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AccountDaoImpl accountDao = daoHelper.createAccountDao();
            account.setPassword(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt()));
            return accountDao.save(account);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean delete(Account account) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AccountDaoImpl accountDao = daoHelper.createAccountDao();
            return accountDao.delete(account);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Account> findByLogin(String login) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AccountDaoImpl accountDao = daoHelper.createAccountDao();
            return accountDao.findByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Account> isAccountExist(String login, String password) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AccountDaoImpl accountDao = daoHelper.createAccountDao();
            Optional<Account> account = accountDao.findByLogin(login);
            if (account.isPresent() && BCrypt.checkpw(password, account.get().getPassword())) {
                return account;
            } else {
                return Optional.empty();
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Account findAdmin() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AccountDaoImpl accountDao = daoHelper.createAccountDao();
            return accountDao.findAdmin();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

}
