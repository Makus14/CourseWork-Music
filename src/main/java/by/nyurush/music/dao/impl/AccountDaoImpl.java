package by.nyurush.music.dao.impl;

import by.nyurush.music.dao.AbstractDao;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.entity.Account;
import by.nyurush.music.service.mapper.AccountMapper;
import by.nyurush.music.service.exception.ServiceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDaoImpl extends AbstractDao<Account> {
    private static final String FIND_ALL = "SELECT id, login, password, role FROM account";
    private static final String FIND_BY_ID = "SELECT id, login, password, role FROM account WHERE id=?";
    private static final String FIND_BY_LOGIN = "SELECT id, login, password, role FROM account WHERE login=?";
    private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT id, login, password, role FROM account WHERE login=? AND password=?";
    private static final String FIND_ADMIN = "SELECT id, login, password, role FROM account WHERE role='ADMIN'";
    private static final String CREATE = "INSERT INTO account (login, password, role) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE account SET login=?, password=?, role=? WHERE id=?";
    private static final String DELETE = "DELETE FROM account WHERE id=?";
    private static final String DELETE_ALL = "DELETE FROM account";

    public AccountDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Account> findAll() throws DaoException {
        List<Account> accountsList = new ArrayList<>();
        Account account;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                account = new AccountMapper().map(resultSet);
                accountsList.add(account);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return accountsList;
    }

    @Override
    public Optional<Account> findById(Integer id) throws DaoException {
        Account account = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account = new AccountMapper().map(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(account);
    }

    @Override
    public Account save(Account account) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            try {
                if (account.getId() != null) {
                    preparedStatement = connection.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setInt(4, account.getId());
                } else {
                    preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
                }
                preparedStatement.setString(1, account.getLogin());
                preparedStatement.setString(2, account.getPassword());
                preparedStatement.setString(3, account.getRole().toString());
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    account.setId(resultSet.getInt(1));
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            } finally {
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
            }
        } catch (SQLException e) {
            throw new DaoException();
        }
        return account;
    }

    @Override
    public boolean delete(Account account) throws DaoException {
        return deleteObject(account, DELETE);
    }

    public void deleteAll() throws DaoException {
        deleteAll(DELETE_ALL);
    }

    public Optional<Account> findByLogin(String login) throws DaoException {
        Account account = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account = new AccountMapper().map(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(account);
    }

    public Optional<Account> findByLoginAndPassword(String login, String password) throws DaoException {
        Account account = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_LOGIN_AND_PASSWORD)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account = new AccountMapper().map(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(account);
    }

    public Account findAdmin() throws DaoException {
        Account account = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ADMIN)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account = new AccountMapper().map(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return account;
    }
}
