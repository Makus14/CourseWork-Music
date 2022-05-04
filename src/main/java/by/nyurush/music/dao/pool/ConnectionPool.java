package by.nyurush.music.dao.pool;

import by.nyurush.music.dao.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final int POOL_SIZE = Integer.parseInt(ResourceBundle.getBundle("additionalConfiguration").getString("poolSize"));
    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> usedConnections;
    private static final ReentrantLock lock = new ReentrantLock();
    private static ConnectionPool instance;

    public static ConnectionPool getInstance() {
        if (instance == null) {
            try {
                lock.lock();
                instance = new ConnectionPool();
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private ConnectionPool() {
        try {
            lock.lock();
            if (instance != null) {
                throw new UnsupportedOperationException();
            } else {
                init();
            }
        } finally {
            lock.unlock();
        }
    }

    private void init() {
        freeConnections = new ArrayBlockingQueue<>(POOL_SIZE);
        usedConnections = new ArrayBlockingQueue<>(POOL_SIZE);

        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                ProxyConnection connection = new ProxyConnection(ConnectionFactory.createConnection());
                freeConnections.offer(connection);
            } catch (MissingResourceException e) {
                LOGGER.fatal("Exception during database initialization", e);
                throw new RuntimeException("Exception during database initialization", e);
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException("Driver is not found" + e.getMessage(), e);
            } catch (SQLException e) {
                LOGGER.error("InterruptedException, creating constructor");
            }
        }
    }

    public ProxyConnection getConnection() throws ConnectionPoolException {
        ProxyConnection connection;
        try {
            connection = freeConnections.take();
            usedConnections.offer(connection);
        } catch (InterruptedException e) {
            LOGGER.error("Error getting connection ", e);
            throw new ConnectionPoolException("Problem with take connection from pool, e");
        }
        return connection;
    }

    public void closeConnection(ProxyConnection proxyConnection) {
        usedConnections.remove(proxyConnection);
        if (!freeConnections.offer(proxyConnection)) {
            LOGGER.error("Problem with connection returned: ", proxyConnection.toString());
        }
    }

    public void destroyConnectionPool() {
        for (ProxyConnection proxyConnection : freeConnections) {
            proxyConnection.realClose();
        }
        for (ProxyConnection proxyConnection : usedConnections) {
            proxyConnection.realClose();
        }
    }

}
