package by.nyurush.music.dao.impl;

import by.nyurush.music.dao.AbstractDao;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.entity.User;
import by.nyurush.music.service.mapper.UserMapper;
import by.nyurush.music.service.exception.ServiceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> {
    private static final String FIND_ALL = "SELECT account_id, first_name, last_name, email, subscription, id, login, password, role " +
            "FROM user " +
            "JOIN account ON account_id = id ";
    private static final String FIND_BY_ID = "SELECT account_id, first_name, last_name, email, subscription, id, login, password, role " +
            "FROM user " +
            "JOIN account ON account_id = id " +
            "WHERE account_id = ?";
    private static final String FIND_BY_LOGIN = "SELECT account_id, first_name, last_name, email, subscription, id, login, password, role " +
            "FROM user " +
            "JOIN account ON account_id = id " +
            "WHERE login = ?";
    private static final String FIND_BY_EMAIL = "SELECT account_id, first_name, last_name, email, subscription, id, login, password, role " +
            "FROM user " +
            "JOIN account ON account_id = id " +
            "WHERE email = ?";
    private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT account_id, first_name, last_name, email, subscription, id, login, password, role " +
            "FROM user " +
            "JOIN account ON account_id = id " +
            "WHERE login = ? AND password = ?";
    private static final String CREATE_USERS_ACCOUNT = "INSERT INTO account (login, password, role) VALUES (?, ?, ?)";
    private static final String CREATE_USER = "INSERT INTO user (account_id, first_name, last_name, email, subscription) VALUES (?, ?, ?, ?, ?)";
    private static final String CREATE_FAVORITE_PLAYLIST = "INSERT INTO playlist (name, visible, account_id) VALUES ('favorite', 0, ?)";
    private static final String UPDATE = "UPDATE account A, user U SET A.login=?, A.password=?, A.role = ?, U.first_name = ?, U.last_name = ?, " +
            "U.email=?, U.subscription=? WHERE A.id = ? AND U.account_id = ?";
    private static final String DELETE = "DELETE FROM user WHERE account_id=?";
    private static final String DELETE_ALL = "DELETE FROM user";


    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> usersList = new ArrayList<>();
        User user;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                user = new UserMapper().map(resultSet);
                usersList.add(user);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return usersList;
    }

    @Override
    public Optional<User> findById(Integer id) throws DaoException {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new UserMapper().map(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public User save(User user) throws DaoException {
        int generatedId;
        try {
            PreparedStatement preparedStatement = null;
           try {
                if (user.getId() != null) {
                    preparedStatement = connection.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, user.getLogin());
                    preparedStatement.setString(2, user.getPassword());
                    preparedStatement.setString(3, user.getRole().toString());
                    preparedStatement.setString(4, user.getFirstName());
                    preparedStatement.setString(5, user.getLastName());
                    preparedStatement.setString(6, user.getEmail());
                    preparedStatement.setBoolean(7, user.getSubscription());
                    preparedStatement.setInt(8, user.getId());
                    preparedStatement.setInt(9, user.getId());
                    preparedStatement.executeUpdate();
                } else {
                    preparedStatement = connection.prepareStatement(CREATE_USERS_ACCOUNT, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, user.getLogin());
                    preparedStatement.setString(2, user.getPassword());
                    preparedStatement.setString(3, user.getRole().toString());
                    preparedStatement.execute();
                    ResultSet resultSet = preparedStatement.getGeneratedKeys();
                    if (resultSet.next()) {
                        generatedId = resultSet.getInt(1);
                        user.setId(generatedId);
                        preparedStatement = connection.prepareStatement(CREATE_USER);
                        preparedStatement.setInt(1, generatedId);
                        preparedStatement.setString(2, user.getFirstName());
                        preparedStatement.setString(3, user.getLastName());
                        preparedStatement.setString(4, user.getEmail());
                        preparedStatement.setBoolean(5, user.getSubscription());
                        preparedStatement.execute();
                        preparedStatement = connection.prepareStatement(CREATE_FAVORITE_PLAYLIST);
                        preparedStatement.setInt(1, generatedId);
                        preparedStatement.execute();
                    } else {
                        connection.rollback();
                    }
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            } finally {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            }
        } catch (SQLException e) {
            throw new DaoException();
        }
        return user;
    }

    @Override
    public boolean delete(User user) throws DaoException {
        return deleteObject(user, DELETE);
    }

    public void deleteAll() throws DaoException {
        deleteAll(DELETE_ALL);
    }

    public Optional<User> findByLogin(String login) throws DaoException {
        return findByParam(FIND_BY_LOGIN, login);
    }

    public Optional<User> findByEmail(String email) throws DaoException {
        return findByParam(FIND_BY_EMAIL, email);
    }

    public Optional<User> findByLoginAndPassword(String login, String password) throws DaoException {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_LOGIN_AND_PASSWORD)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new UserMapper().map(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(user);
    }

    private Optional<User> findByParam(String sqlQuery, String param) throws DaoException {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, param);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new UserMapper().map(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(user);
    }

}
