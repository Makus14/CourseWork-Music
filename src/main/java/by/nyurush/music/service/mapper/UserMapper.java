package by.nyurush.music.service.mapper;

import by.nyurush.music.entity.Account;
import by.nyurush.music.entity.User;
import by.nyurush.music.service.exception.ServiceException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {
    @Override
    public User map(ResultSet resultSet) throws ServiceException {
        Account account = new AccountMapper().map(resultSet);
        try {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            Boolean subscription = resultSet.getBoolean("subscription");
            return new User(account.getId(), account.getLogin(), account.getPassword(), account.getRole(), firstName, lastName,
                    email, subscription);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
