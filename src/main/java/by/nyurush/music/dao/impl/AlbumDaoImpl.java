package by.nyurush.music.dao.impl;

import by.nyurush.music.dao.AbstractDao;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.entity.Album;
import by.nyurush.music.service.mapper.AlbumMapper;
import by.nyurush.music.service.exception.ServiceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlbumDaoImpl extends AbstractDao<Album> {
    private static final String FIND_ALL = "SELECT album.id, album.name, album.year, artist.id, artist.name, artist.image_path " +
            "FROM album JOIN artist ON album.artist_id = artist.id";
    private static final String FIND_BY_ID = "SELECT album.id, album.name, album.year, artist.id, artist.name, artist.image_path  " +
            "FROM album JOIN artist ON album.artist_id = artist.id WHERE album.id = ?";
    private static final String FIND_BY_NAME = "SELECT album.id, album.name, album.year, artist.id, artist.name, artist.image_path  " +
            "FROM album JOIN artist ON album.artist_id = artist.id WHERE album.name LIKE ?";
    private static final String FIND_BY_YEAR = "SELECT album.id, album.name, album.year, artist.id, artist.name, artist.image_path  " +
            "FROM album JOIN artist ON album.artist_id = artist.id WHERE album.year = ?";
    private static final String FIND_BY_ARTIST_AND_ALBUM_NAME = "SELECT album.id, album.name, album.year, artist.id, artist.name, artist.image_path  " +
            "FROM album JOIN artist ON album.artist_id = artist.id WHERE artist.name = ? AND album.name = ?";
    private static final String FIND_BY_ARTIST_NAME = "SELECT album.id, album.name, album.year, artist.id, artist.name, artist.image_path  " +
            "FROM album JOIN artist ON album.artist_id = artist.id WHERE artist.name = ?";
    private static final String UPDATE = "UPDATE album SET name=?, year=?, artist_id=? WHERE id=?";
    private static final String CREATE = "INSERT INTO album (name, year, artist_id) VALUES (?, ?, ?)";
    private static final String DELETE = "DELETE FROM album WHERE id=?";
    private static final String DELETE_ALL = "DELETE FROM album";

    public AlbumDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Album> findAll() throws DaoException {
        List<Album> albumsList = new ArrayList<>();
        Album album;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                album = new AlbumMapper().map(resultSet);
                albumsList.add(album);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return albumsList;
    }

    @Override
    public Optional<Album> findById(Integer id) throws DaoException {
        Album album = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                album = new AlbumMapper().map(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(album);
    }

    @Override
    public Album save(Album album) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            try {
                if (album.getId() != null) {
                    preparedStatement = connection.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setInt(4, album.getId());
                } else {
                    preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
                }
                preparedStatement.setString(1, album.getAlbumName());
                if (album.getYear() != null)
                    preparedStatement.setInt(2, album.getYear());
                else
                    preparedStatement.setNull(2, Types.INTEGER);
                preparedStatement.setInt(3, album.getArtist().getId());
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    album.setId(resultSet.getInt(1));
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            } finally {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            }
        } catch (SQLException e) {
            throw new DaoException();
        }
        return album;
    }

    @Override
    public boolean delete(Album album) throws DaoException {
        return deleteObject(album, DELETE);
    }

    public void deleteAll() throws DaoException {
        deleteAll(DELETE_ALL);
    }

    public List<Album> findByName(String name) throws DaoException {
        List<Album> albumsList = new ArrayList<>();
        Album album;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME)) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                album = new AlbumMapper().map(resultSet);
                albumsList.add(album);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return albumsList;
    }

    public List<Album> findByYear(Integer year) throws DaoException {
        List<Album> albumsList = new ArrayList<>();
        Album album;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_YEAR)) {
            preparedStatement.setInt(1, year);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                album = new AlbumMapper().map(resultSet);
                albumsList.add(album);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return albumsList;
    }

    public Optional<Album> findByArtistAndAlbumName(String artist, String albumName) throws DaoException {
        Album album = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ARTIST_AND_ALBUM_NAME)) {
            preparedStatement.setString(1, artist);
            preparedStatement.setString(2, albumName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                album = new AlbumMapper().map(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(album);
    }

    public List<Album> findByArtistName(String artistName) throws DaoException {
        List<Album> albumsList = new ArrayList<>();
        Album album;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ARTIST_NAME)) {
            preparedStatement.setString(1, artistName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                album = new AlbumMapper().map(resultSet);
                albumsList.add(album);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return albumsList;
    }
}
