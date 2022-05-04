package by.nyurush.music.service.mapper;

import by.nyurush.music.entity.*;
import by.nyurush.music.service.exception.ServiceException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumMapper implements Mapper<Album> {
    @Override
    public Album map(ResultSet resultSet) throws ServiceException {
        try {
            Artist artist = new ArtistMapper().map(resultSet);
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Integer year = resultSet.getInt("year");
            return new Album(id, name, year, artist);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
