package by.nyurush.music.dao;

import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.entity.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Entity> {

    protected Connection connection;

    public AbstractDao (Connection connection) {
        this.connection = connection;
    }

    public abstract List<T> findAll() throws DaoException;

    public abstract Optional<T> findById(Integer id) throws DaoException;

    public abstract T save(T entity) throws DaoException;

    public abstract boolean delete(T entity) throws DaoException;

    protected boolean deleteObject(T t, String query) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try {
                preparedStatement.setInt(1, t.getId());
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return true;
    }

    protected void deleteAll(String query) throws DaoException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
