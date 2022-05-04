package by.nyurush.music.service.mapper;

import by.nyurush.music.entity.Account;
import by.nyurush.music.entity.AccountRole;
import by.nyurush.music.service.exception.ServiceException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements Mapper<Account> {

    @Override
    public Account map(ResultSet resultSet) throws ServiceException {
        try {
            Integer id = resultSet.getInt("id");
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            AccountRole role = AccountRole.valueOf(resultSet.getString("role"));
            return new Account(id, login, password, role);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
