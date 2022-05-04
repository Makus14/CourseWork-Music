package by.nyurush.music.dao.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {
    private static final String DATABASE_PROPERTY = "database";
    private static final String DATABASE_URL = "db.url";
    private static final String DATABASE_USER = "db.user";
    private static final String DATABASE_PASSWORD = "db.password";
    private static final ResourceBundle resource = ResourceBundle.getBundle(DATABASE_PROPERTY);

    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        String url = resource.getString(DATABASE_URL);
        String user = resource.getString(DATABASE_USER);
        String password = resource.getString(DATABASE_PASSWORD);
        Class.forName(resource.getString("driver"));
        return DriverManager.getConnection(url, user, password);
    }

}
