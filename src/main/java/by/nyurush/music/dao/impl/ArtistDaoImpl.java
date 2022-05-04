package by.nyurush.music.dao.impl;

import by.nyurush.music.dao.AbstractDao;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.entity.Artist;
import by.nyurush.music.service.mapper.ArtistMapper;
import by.nyurush.music.service.exception.ServiceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArtistDaoImpl extends AbstractDao<Artist> {
    private static final String FIND_ALL = "SELECT id, name, image_path FROM artist";
    private static final String FIND_BY_ID = "SELECT id, name, image_path FROM artist WHERE id = ?";
    private static final String FIND_BY_NAME = "SELECT id, name, image_path FROM artist WHERE name LIKE ?";
    private static final String CREATE = "INSERT INTO artist (name, image_path) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE artist SET name = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM artist WHERE id = ?";
    private static final String DELETE_ALL = "DELETE FROM artist";

    public ArtistDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Artist> findAll() throws DaoException {
        List<Artist> artistsList = new ArrayList<>();
        Artist artist;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                artist = new ArtistMapper().map(resultSet);
                artistsList.add(artist);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return artistsList;
    }

    @Override
    public Optional<Artist> findById(Integer id) throws DaoException {
        Artist artist = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                artist = new ArtistMapper().map(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(artist);
    }

    @Override
    public Artist save(Artist artist) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            try {
                if (artist.getId() != null) {
                    preparedStatement = connection.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setInt(2, artist.getId());
                } else {
                    preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
                }
                preparedStatement.setString(1, artist.getArtistName());
                preparedStatement.setString(2, artist.getImagePath());
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    artist.setId(resultSet.getInt(1));
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            } finally {
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
            }
        } catch (SQLException e) {
            throw new DaoException();
        }
        return artist;
    }

    @Override
    public boolean delete(Artist artist) throws DaoException {
        return deleteObject(artist, DELETE);
    }

    public void deleteAll() throws DaoException {
        deleteAll(DELETE_ALL);
    }

    public List<Artist> findByName(String name) throws DaoException {
        List<Artist> artistsList = new ArrayList<>();
        Artist artist;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME)) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                artist = new ArtistMapper().map(resultSet);
                artistsList.add(artist);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return artistsList;
    }
}
