package by.nyurush.music.service.mapper;

import by.nyurush.music.entity.Artist;
import by.nyurush.music.service.exception.ServiceException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArtistMapper implements Mapper<Artist> {
    @Override
    public Artist map(ResultSet resultSet) throws ServiceException {
        try {
            return new Artist(resultSet.getInt("artist.id"), resultSet.getString("artist.name"), resultSet.getString("artist.image_path"));
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
