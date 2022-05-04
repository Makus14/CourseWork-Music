package by.nyurush.music.dao;

import by.nyurush.music.dao.exception.ConnectionPoolException;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.*;
import by.nyurush.music.dao.pool.ConnectionPool;
import by.nyurush.music.dao.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {
    private static final Logger LOGGER = LogManager.getLogger(DaoHelper.class);
    private ProxyConnection connection;

    public DaoHelper(ConnectionPool connectionPool) throws DaoException {
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void close() {
        connection.close();
    }

    public AccountDaoImpl createAccountDao() {
        return new AccountDaoImpl(connection);
    }

    public AlbumDaoImpl createAlbumDao() {
        return new AlbumDaoImpl(connection);
    }

    public ArtistDaoImpl createArtistDao() {
        return new ArtistDaoImpl(connection);
    }

    public PlaylistDaoImpl createPlaylistDao() {
        return new PlaylistDaoImpl(connection);
    }

    public TrackDaoImpl createTrackDao() {
        return new TrackDaoImpl(connection);
    }

    public UserDaoImpl createUserDao() {
        return new UserDaoImpl(connection);
    }

    public CommentDaoImpl createCommentDao() {
        return new CommentDaoImpl(connection);
    }

    public void commit() throws DaoException {
        try {
            try {
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new DaoException(e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
    }

    public void rollback() throws DaoException {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
