package by.nyurush.music.dao;

import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.pool.ConnectionPool;

public class DaoHelperFactory {

    public DaoHelper create() throws DaoException {
        return new DaoHelper(ConnectionPool.getInstance());
    }
}
