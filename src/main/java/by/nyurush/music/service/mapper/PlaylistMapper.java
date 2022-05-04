package by.nyurush.music.service.mapper;

import by.nyurush.music.entity.Account;
import by.nyurush.music.entity.Playlist;
import by.nyurush.music.service.exception.ServiceException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistMapper implements Mapper<Playlist> {
    @Override
    public Playlist map(ResultSet resultSet) throws ServiceException {
        try {
            Account account = new AccountMapper().map(resultSet);
            Integer id = resultSet.getInt("playlist.id");
            String name = resultSet.getString("playlist.name");
            Boolean visible = resultSet.getBoolean("playlist.visible");
            return new Playlist(id, name, visible, account);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
